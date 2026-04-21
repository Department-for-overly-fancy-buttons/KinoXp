const BASE_URL = "/api/users"

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

        let csrfToken = getCsrfToken();
        try {
            const response = await fetch(`${BASE_URL}/log_in`, {
                method: "POST",
                headers: {"Content-Type": "application/x-www-form-urlencoded", "X-XSRF-TOKEN": csrfToken},
                body: new URLSearchParams(loginData)

            });

            if (response.ok) {
                //const user = await response.json();


                //localStorage.setItem("user", JSON.stringify(user));

                //alert(`Welcome, ${loginData.username}!`);


                window.location.href = "/index.html";

            } else {
                alert("Wrong username or password");
            }

        } catch (error) {
            console.error("Login error:", error);
            alert("Something went wrong with login.");
        }
    });

    function getCsrfToken() {
        const match = document.cookie.match(/XSRF-TOKEN=([^;]+)/);
        return match ? decodeURIComponent(match[1]) : null;
    }

});