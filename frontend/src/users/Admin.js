import {createHtmlElement} from "../htmlTagFactory.js";
import {displayNavigationBar} from "../navigationBars.js";

document.addEventListener('DOMContentLoaded', initApp);

const BASE_URL = "/api";

let users = [];
let roleData = [];

async function initApp() {
    displayNavigationBar();
    requireLogIn();
    requireAdmin();
    await display();
}

async function fetchUsers() {
    const response = await fetch(`${BASE_URL}/users`)
    if (!response.ok) {
        throw new Error("Failed to fetch user")
    }
    return await response.json()
}

async function handleAddUser(event) {
    event.preventDefault();
    requireAdmin();
    console.log("adding user")
    const formEl = event.target.closest("form");
    const formData = new FormData(formEl);
    const username = formData.get("username");
    const password = formData.get("password");
    const chosenRole = document.getElementById("roleDropDown");
    const role = chosenRole.options[chosenRole.selectedIndex].value;

    const user = {
        username: username,
        password: password,
        role: role
    };
console.log(typeof formData.get("userId"))
    if (formData.get("userId") !== "") {
        const userId = formData.get("userId");

        try {
            const updatedUser = await fetchUpdateUser(userId, user);
            console.log("User updated:", updatedUser);
            await display();
            formEl.reset();
        } catch (error) {
            console.error(error);
            alert("Could not update user");
        }

    } else {

        try {
            const newUser = await fetchAddUser(user);
            console.log("User created:", newUser);

            formEl.reset();
        } catch (error) {
            console.error(error);
            alert("Could not create user");
        }
    }
}

async function fetchAddUser(user) {
    const csrfToken = getCsrfToken()
    const response = await fetch(`${BASE_URL}/users`, {
        method: "POST",
        headers: {
            "Content-Type": "application/json", "X-XSRF-TOKEN": csrfToken
        },
        body: JSON.stringify(user)
    });

    if (!response.ok) {
        throw new Error("Failed to add user");
    }
    await display()
    return await response.json();
}

async function fetchRoles() {
    try {
        const response = await fetch(`${BASE_URL}/users/roles`);
        if (!response.ok) {
            throw new Error("HTTP error!");
        }
        const roles = await response.json();
        return roles;

    } catch (error) {
        console.log("An error occurred:   " + error)
    }

}

async function display() {
    users = await fetchUsers()
    roleData = await fetchRoles();
    const userFormEl = document.getElementById("userForm");
    userFormEl.innerHTML = "";

    const userIdEl = createHtmlElement({
        tagName: "input",
        htmlAttributes: {type: "hidden", name: "userId", id: "userId"}
    })
    userFormEl.appendChild(userIdEl);

    const usernameInputEl = createHtmlElement({
        tagName: "input",
        htmlAttributes: {type: "text", name: "username", placeholder: "username", id: "usernameInput"}
    });
    usernameInputEl.required = true;
    userFormEl.appendChild(usernameInputEl);

    const passwordInputEL = createHtmlElement({
        tagName: "input",
        htmlAttributes: {type: "password", name: "password", placeholder: "password", id: "passwordInput"}
    })
    passwordInputEL.required = true;
    userFormEl.appendChild(passwordInputEL);

    const roleDropDownTitleEl = createHtmlElement({
        tagName: "h2",
        htmlAttributes: {textContent: "Select a role fo the user"}
    })
    userFormEl.appendChild(roleDropDownTitleEl);

    let defaultOption = createHtmlElement({
        tagName: "option",
        htmlAttribute: {value: "", textContent: "--Choose role--", id: "default"}
    });
    const roleDropDown = createHtmlElement({
        tagName: "select",
        htmlAttributes: {name: "roles", id: "roleDropDown"}
    });
    roleDropDown.appendChild(defaultOption);
    for (let i = 0; i < roleData.length; i++) {
        const roleDropDownOption = createHtmlElement({
            tagName: "option",
            htmlAttributes: {value: `${roleData[i]}`, textContent: `${roleData[i]}`, id: `${roleData[i]}Input`}
        })
        console.log(roleDropDownOption);
        roleDropDown.appendChild(roleDropDownOption);
    }

    userFormEl.appendChild(roleDropDown);
    let submitButton = document.createElement("button")
    submitButton.textContent = `Submit`;
    submitButton.type = `button`;
    submitButton.addEventListener("click", handleAddUser)
    userFormEl.appendChild(submitButton);

    let cancelButton = document.createElement("button")
    cancelButton.textContent = `cancel`;
    cancelButton.addEventListener("click", () => {
        userFormEl.reset();
        document.getElementById("default").setAttribute("selected", "selected")
    });

    userFormEl.appendChild(cancelButton);

    const userContainer = document.getElementById("userContainer");
    userContainer.innerHTML = "";
    if (users.length < 1) {
        userContainer.innerHTML = "no users!"
    } else {
        const headerRow = createHtmlElement({tagName: "tr"});
        const headerUsername = createHtmlElement({tagName: "th", htmlAttribute: {textContent: "Username"}});
        const headerRole = createHtmlElement({tagName: "th", htmlAttribute: {textContent: "Role"}});
        headerRow.appendChild(headerUsername);
        headerRow.appendChild(headerRole);
        userContainer.appendChild(headerRow);

        for (let user of users) {
            let userEl = document.createElement("tr");
            userEl.classList.add("user-list");
            userEl.setAttribute("data-userID", user.id);
            userEl.setAttribute("data-userPassword", user.password);

            let titleElement = document.createElement("td");
            titleElement.textContent = `${user.username}`;
            userEl.appendChild(titleElement);

            let roleElement = document.createElement("td");
            roleElement.textContent = `${user.role}`;
            userEl.appendChild(roleElement);

            let deleteButton = document.createElement("button")
            deleteButton.textContent = `Delete`;
            deleteButton.type = "button";
            deleteButton.addEventListener("click", handleDeleteUser)

            let editButton = document.createElement("button")
            editButton.textContent = `Edit`;
            editButton.type = "button";
            editButton.addEventListener("click", handleUpdateUser)

            userEl.appendChild(deleteButton)
            userEl.appendChild(editButton);
            userContainer.appendChild(userEl)
        }
    }
}

async function handleDeleteUser(event) {
    event.preventDefault();
    requireAdmin();
    const userEl = event.target.closest("tr");
    const userId = userEl.getAttribute("data-userID");
    console.log("tets: " + typeof userId);
    if (userId == getLoggedInUser().id) {
        alert("You cannot delete your current user");
        return;
    }
    try {
        await fetchDeleteUser(userId);
        console.log("User deleted");
        await display();

    } catch (error) {
        console.error(error);
        alert("Could not delete user");
    }
}

async function fetchDeleteUser(userId) {
    const response = await fetch(`${BASE_URL}/users/${userId}`, {
        method: "DELETE",
        headers: {"X-XSRF-TOKEN": getCsrfToken()}
    });

    if (!response.ok) {
        throw new Error("Failed to delete user");
    }

    return response;
}

async function handleUpdateUser(event) {
    event.preventDefault()
    requireAdmin();
    const userEl = event.target.closest("tr");
    const userId = Number(userEl.getAttribute("data-userID"));
    const password = userEl.getAttribute("data-userPassword");
    console.log(userEl.children)
    console.log(userEl.children[0])
    console.log(userEl.children[0].textContent)
    const username = userEl.children[0].textContent;
    const role = userEl.children[1].textContent;

    //document.querySelector("#cancelEditBtn").classList.remove("hidden");
    document.querySelector("#userId").value = userId;
    document.querySelector("#usernameInput").value = username;
    document.querySelector("#passwordInput").value = password;
    document.getElementById(`${role}Input`).selected = true;
    //select.options[select.selectedIndex].value = role;
    //document.getElementById(`${role}Input`).setAttribute("selected", "selected");

    //document.querySelector("#addbtn").textContent = "Edit todo";

}

async function fetchUpdateUser(userId, user) {
    try {
        const response = await fetch(`${BASE_URL}/users/update/${userId}`, {
            method: "PUT",
            headers: {
                "Content-Type": "application/json",
                "X-XSRF-TOKEN": getCsrfToken()
            },
            body: JSON.stringify(user)
        });

        if (!response.ok) {
            throw new Error("Failed to update user");
        }

        return response;
    } catch (error) {
        console.error("error:", error);
    }
}