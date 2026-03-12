document.addEventListener('DOMContentLoaded', initApp);

const BASE_URL = "http://localhost:8080/api";

async function initApp() {
    requireLogIn()
    displayUser()
    document.getElementById("submitCategory").addEventListener("click", handleSubmit);
}

async function handleSubmit(event) {
    event.preventDefault();
    const formEl = event.target.closest("form");
    const formData = new FormData(formEl);
    const category = {
        genre: formData.get("genre")
    }

    const response = await fetch(`${BASE_URL}/movies/category`, {
        method: "POST",
        headers: {"Content-Type": "application/json"},
        body: JSON.stringify(category)
    });

    const result = await response.json();
    console.log("Category added:", result);
    alert(`${category.genre}` + " was added");
}
