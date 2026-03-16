document.addEventListener('DOMContentLoaded', initApp);

const BASE_URL = "http://localhost:8080/api";

let roleData = [];

async function initApp() {
    roleData = await fetchRoles();
    requireLogIn();
    requireAdmin();
    await display()

    const form = document.getElementById("addUserForm");
    if (form) {
        form.addEventListener("submit", handleAddUser);
    }
}

async function handleAddUser(event) {
    event.preventDefault();

    const username = document.getElementById("username").value;
    const password = document.getElementById("password").value;
    const role = document.getElementById("role").value;

    const user = {
        username: username,
        password: password,
        role: role
    };

    try {
        const newUser = await fetchAddUser(user);
        console.log("User created:", newUser);

        display();
        event.target.reset();
    } catch (error) {
        console.error(error);
        alert("Could not create user");
    }
}

async function fetchAddUser(user) {
    const response = await fetch(`${BASE_URL}/users`, {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(user)
    });

    if (!response.ok) {
        throw new Error("Failed to add user");
    }

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

function display() {
    container = document.getElementById("userContainer");
    for (let i = 0; i < roleData.length; i++) {
        console.log(typeof roleData[i])
        const checkBox = document.createElement("input")
        checkBox.setAttribute("type", "checkbox");
        checkBox.setAttribute("value", roleData[i]);
        checkBox.setAttribute("name", "role");
        const label = document.createElement("label")
        label.textContent = `${roleData[i]}`;
        label.appendChild(checkBox);
        container.appendChild(label);
    }
}

async function handleDeleteUser(event) {
    event.preventDefault();
    const username = document.getElementById("username").value;
    try {
        await fetchDeleteUser(username);
        console.log("User deleted");
        display();

    } catch (error) {
        console.error(error);
        alert("Could not delete user");
    }
}

async function fetchDeleteUser(user) {
    const response = await fetch(`${BASE_URL}/users/${user.id}`, {
        method: "DELETE",
    });

    if (!response.ok) {
        throw new Error("Failed to delete user user");
    }

    return await response.json();
}
