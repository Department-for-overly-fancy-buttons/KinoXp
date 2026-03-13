

document.addEventListener('DOMContentLoaded', initApp)

const BASE_URL = "http://localhost:8080/api";

async function initApp() {
    requireLogIn()
    requireAdmin()
    await DisplayUsers()

}
async function fetchUsers() {
    const response = await fetch(`${BASE_URL}/user`)
    if (!response.ok) {
        throw new Error("Failed to fetch user")
    }
    return await response.json()
}

async function DisplayUsers() {
    const users = await fetchUsers()
    console.log(users)

    const container = document.getElementById("usersContainer")
    container.innerHTML = "";
    if (users.length < 1) {
        container.innerHTML = "no users!"
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
        deleteButton.addEventListener("click",() => handleDeleteUser(user.id))

        userEl.appendChild(deleteButton)
        container.appendChild(userEl)
    }
}