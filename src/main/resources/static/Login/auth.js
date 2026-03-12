const user = getLoggedInUser();

//if (user) {
//    document.getElementById("welcomeUser").textContent = `Welcome ${user.username}`;
//}

function getLoggedInUser() {
    const user = localStorage.getItem("user");
    return user ? JSON.parse(user) : null;

}

function isLoggedIn() {
    return getLoggedInUser() !== null;
}

function requireLogIn() {
    if(!isLoggedIn()) {
        return location.href = "Login/LoginForm.html";
    }
}
function login() {
    return location.href = "Login/LoginForm.html"
}

function logout() {
    localStorage.removeItem("user")
    return location.href = "index.html"
}

function displayUser() {

    const user = getLoggedInUser();

    if (user) {
        const welcomeEl = document.getElementById("welcomeUser");

        if (welcomeEl) {
            welcomeEl.textContent = `Welcome ${user.username}`;
        }
    }
}
function requireAdmin() {
    const user = getLoggedInUser();
    if (!user || user.role !== "ADMIN") {
        alert("You must be an admin to access this page!");
        window.location.href = "index.html";
    }
}
