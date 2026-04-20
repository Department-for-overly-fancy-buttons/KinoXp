const BASE_URL = "http://localhost:8090/api/users"

document.addEventListener('DOMContentLoaded', () => {
    const loginForm = document.getElementById("loginForm");
    if (!loginForm) return;

    loginForm.addEventListener("submit", async (event) => {
        event.preventDefault();

        const formEl = event.target.closest("form");
        const formData = new FormData(formEl);
        const loginData = {
            username: formData.get("username"),
            password: formData.get("password"),

        };
        try {
            const response = await fetch(`${BASE_URL}/log_in`, {
                method: "POST",
                headers: {"Content-Type": "application/json"},
                body: JSON.stringify(loginData)

            });

            if (response.ok) {
                const user = await response.json();


                localStorage.setItem("user", JSON.stringify(user));

                alert(`Welcome, ${loginData.username}!`);


                window.location.href = "http://localhost:8090/index.html";

            } else {
                alert("Wrong username or password");
            }

        } catch (error) {
            console.error("Login error:", error);
            alert("Something went wrong with login.");
        }
    });

});