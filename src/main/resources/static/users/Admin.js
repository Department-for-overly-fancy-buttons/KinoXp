import {createHtmlElement} from "../htmlTagFactory.js";

document.addEventListener('DOMContentLoaded', initApp);

const BASE_URL = "http://localhost:8080/api";

let users = [];
let roleData = [];

async function initApp() {
    requireLogIn();
    requireAdmin();
    await display();

    /*const form = document.getElementById("userForm");
    if (form) {
        console.log("added event listener")
        form.addEventListener("submit", handleAddUser);
    }*/
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

    console.log("adding user")
    const formEl = event.target.closest("form");
    const formData = new FormData(formEl);
    const username = formData.get("username");
    const password = formData.get("password");
    const chosenRole = document.getElementById("roleDropDown");


    const user = {
        username: username,
        password: password,
        role: chosenRole.options[chosenRole.selectedIndex].value
    };

    try {
        const newUser = await fetchAddUser(user);
        console.log("User created:", newUser);

        formEl.reset();
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
        htmlAttribute: {type: "hidden", name: "userId", id: "userId"}
    })
    userFormEl.appendChild(userIdEl);

    const usernameInputEl = createHtmlElement({
        tagName: "input",
        htmlAttributes: {type: "text", name: "username", placeholder: "username", id: "username"}
    });
    usernameInputEl.required = true;
    userFormEl.appendChild(usernameInputEl);

    const passwordInputEL = createHtmlElement({
        tagName: "input",
        htmlAttributes: {type: "password", name: "password", placeholder: "password", id: "password"}
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
        htmlAttributes: {name: "roles", id: "roleDropDown roleInput",}
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
    }
    for (let user of users) {
        let userEl = document.createElement("div");
        userEl.classList.add("user-list");
        userEl.setAttribute("data-userID", user.id);

        let titleElement = document.createElement("h2");
        titleElement.textContent = `${user.username}`;
        userEl.appendChild(titleElement);


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

async function handleDeleteUser(event) {
    event.preventDefault();
    const userEl = event.target.closest("div");
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
    });

    if (!response.ok) {
        throw new Error("Failed to delete user");
    }

    return response;
}

async function handleUpdateUser(event) {
    event.preventDefault()

    const userEl = event.target.closest("div");
    //const id = Number(userEl.getAttribute("data-id"));
    console.log(userEl.children)
    const username = userEl.closest("h2").textContent;
    //const userId = userEl.children[1].textContent;
    //const completed = userEl.children[2].textContent === "Yes";

    //document.querySelector("#cancelEditBtn").classList.remove("hidden");
    //document.querySelector("#userId").value = user.id;
    document.querySelector("#usernameInput").value = username;
    //document.querySelector("#passwordInput").value = user.password;
    //document.querySelector("#roleInput").value = role;
    //document.getElementById(`${role}`).setAttribute("selected", "selected");

    //document.querySelector("#addbtn").textContent = "Edit todo";
    try {
    } catch (error) {
        console.error(error)
        alert("could not update user")
    }

}

async function fetchUpdateUser(userId) {
    const response = await fetch(`${BASE_URL}/users/${userId}`, {
        method: "PUT",
    });

    if (!response.ok) {
        throw new Error("Failed to update user");
    }

    return response;
}

function ief() {
    document.addEventListener("DOMContentLoaded", initApp);

    const BASE_URL_TODOS = "http://localhost:8080/api/todos";
// Sort state management
    let sortState = {
        key: "title",      // Default sort by title
        isAsc: true        // Default ascending order
    };

// Global todos array to avoid refetching
    let todosData = [];


    async function initApp() {
        // Fetch and display todos
        todosData = await fetchTodos();
        displayTodos(todosData);

        // Add event listener for form submission
        document.querySelector("#todoForm").addEventListener("submit", handleFormSubmit);

        // Add event listener for table clicks (delete, edit)
        document.querySelector("#todoTableBody").addEventListener("click", handleTableClick);

        // Add event listener for table header clicks (sorting)
        document.querySelector("thead").addEventListener("click", handleHeaderClick);

        document.querySelector("#cancelEditBtn").addEventListener("click", () => {
            document.querySelector("#todoForm").reset();
            document.querySelector("#todoId").value = "";
            document.querySelector("#cancelEditBtn").classList.add("hidden");
            document.querySelector("#addbtn").textContent = "Add todo";
        });
        // Initialize sort indicators
        updateSortIndicators();
    }

    async function fetchTodos() {
        try {
            const response = await fetch(BASE_URL_TODOS);
            if (!response.ok) {
                throw new Error("HTTP error!");
            }
            const todoData = await response.json();
            todosData = todoData;
            return todoData;
        } catch (error) {
            console.error('Fetch error:', error);
        }
    }

    function displayTodos(todos) {
        console.log(todos);
        const tableBody = document.getElementById("todoTableBody");
        tableBody.innerHTML = ""; // Clear existing rows
        for (const todo of todos) {
            renderTodoRow(todo);
        }
    }

    function renderTodoRow(todo) {
        const tableBody = document.getElementById("todoTableBody");
        const row = document.createElement("tr");
        row.setAttribute("data-id", todo.id);
        const title = document.createElement("td");
        const userId = document.createElement("td");
        const completed = document.createElement("td");
        const btns = document.createElement("td");
        const editbtn = document.createElement("button");
        const deletebtn = document.createElement("button");

        title.textContent = todo.title;
        userId.textContent = todo.userId;
        completed.textContent = todo.completed ? "Yes" : "No";

        editbtn.classList.add("btn", "btn-warning");
        deletebtn.classList.add("btn", "btn-danger");
        editbtn.textContent = "Edit";
        deletebtn.textContent = "Delete";

        btns.appendChild(editbtn);
        btns.appendChild(deletebtn);

        editbtn.setAttribute("data-action", "edit");
        deletebtn.setAttribute("data-action", "delete");

        row.appendChild(title);
        row.appendChild(userId);
        row.appendChild(completed);
        row.appendChild(btns);
        tableBody.appendChild(row);
    }

    async function addTodo(todo) {
        try {
            const response = await fetch(BASE_URL_TODOS, {
                method: "POST",
                headers: {
                    "Content-Type": "application/json"
                },
                body: JSON.stringify(todo)
            });
            if (!response.ok) {
                throw new Error("HTTP error!");
            }
            return await response.json();
        } catch (error) {
            console.error('Fetch error:', error);
        }
    }

    async function handleFormSubmit(event) {
        event.preventDefault();
        const form = new FormData(event.target);
        const id = form.get("id");
        const title = form.get("title");
        const userId = Number(form.get("userId"));
        const completed = form.get("completed") === "on";

        const todoData = {title, userId, completed};

        if (id) {
            // Update existing todo
            const updatedTodo = await updateTodo(id, todoData);
            // Find and update the todo in todosData
            const index = todosData.findIndex(todo => todo.id === Number(id));
            if (index !== -1) {
                todosData[index] = updatedTodo;
            }
            document.querySelector("#addbtn").textContent = "Add todo";
        } else {
            // Add new todo
            const createdTodo = await addTodo(todoData);
            // Add to todosData
            todosData.push(createdTodo);
        }

        // Re-sort and display with current sort settings
        sortAndDisplayTodos();

        event.target.reset();
        document.querySelector("#todoId").value = "";
        document.querySelector("#cancelEditBtn").classList.add("hidden");
    }

    async function deleteTodo(id) {

        try {
            const response = await fetch(`${BASE_URL_TODOS}/${id}`, {
                method: "DELETE"
            });
            if (!response.ok) {
                throw new Error("HTTP error!");
            }
            return true;
        } catch (error) {
            console.error('Fetch error:', error);
        }

    }

    async function updateTodo(id, updatedTodo) {
        try {
            const response = await fetch(`${BASE_URL_TODOS}/${id}`, {
                method: "PUT",
                headers: {
                    "Content-Type": "application/json"
                },
                body: JSON.stringify(updatedTodo)
            });
            return await response.json();
        } catch (error) {
            console.error('Fetch error:', error);
        }
    }

    async function handleTableClick(event) {
        const action = event.target.getAttribute("data-action");
        const row = event.target.closest("tr");
        const id = Number(row.getAttribute("data-id"));

        if (action === "delete") {
            await deleteTodo(id);
            // Remove from todosData
            todosData = todosData.filter(todo => todo.id !== id);
            // Re-sort and display
            sortAndDisplayTodos();
        } else if (action === "edit") {
            // Populate form with existing todo data
            const title = row.children[0].textContent;
            const userId = row.children[1].textContent;
            const completed = row.children[2].textContent === "Yes";

            document.querySelector("#cancelEditBtn").classList.remove("hidden");
            document.querySelector("#todoId").value = id;
            document.querySelector("#todoTitle").value = title;
            document.querySelector("#userId").value = userId;
            document.querySelector("#completed").checked = completed;
            document.querySelector("#addbtn").textContent = "Edit todo";
        }
    }

    function sortBy(key, isAsc) {
        return (a, b) => {
            const valA = a[key];
            const valB = b[key];

            if (valA === undefined || valB === undefined) {
                return 0;
            }

            // Sort by string
            if (typeof valA === "string" && typeof valB === "string") {
                return isAsc ? valA.localeCompare(valB) : valB.localeCompare(valA);
            }

            // Sort by number
            if (typeof valA === "number" && typeof valB === "number") {
                return isAsc ? valA - valB : valB - valA;
            }

            return 0;
        };
    }

    function handleHeaderClick(event) {
        console.log("click");
        const clickedHeader = event.target.closest("th");

        // Check if the clicked element is a sortable header
        if (!clickedHeader || !clickedHeader.hasAttribute("data-sort-key")) {
            return; // Not a sortable header, do nothing
        }

        const clickedKey = clickedHeader.getAttribute("data-sort-key");

        // If clicking the same column, toggle sort direction
        if (sortState.key === clickedKey) {
            sortState.isAsc = !sortState.isAsc;
        } else {
            // If clicking a different column, sort by that column in ascending order
            sortState.key = clickedKey;
            sortState.isAsc = true;
        }

        // Sort and display the todos with the new sort state
        sortAndDisplayTodos();
    }

    function sortAndDisplayTodos() {
        // Sort the todosData array using our sortBy function
        todosData.sort(sortBy(sortState.key, sortState.isAsc));

        // Re-display the sorted todos
        displayTodos(todosData);

        // Update the visual indicators
        updateSortIndicators();
    }

    function updateSortIndicators() {
        // Clear all existing indicators
        document.querySelectorAll("th[data-sort-key] span").forEach(span => {
            span.textContent = "";
        });

        // Add indicator to the active sort column
        const activeIndicator = document.querySelector(`#sort-${sortState.key}`);
        if (activeIndicator) {
            activeIndicator.textContent = sortState.isAsc ? " ▲" : " ▼";
        }
    }
}