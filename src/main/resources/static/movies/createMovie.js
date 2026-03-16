import {displayNavigationBar} from "../navigationBars.js";

document.addEventListener('DOMContentLoaded', initApp);

const BASE_URL = "http://localhost:8080/api";

let categoryData = [];
let imageData;
let preview;

async function initApp() {
    displayNavigationBar();
    categoryData = await fetchCategories();
    requireEmployee();
    displayUser()

    console.log(categoryData);
    document.getElementById("submitMovie").addEventListener("click", handleSubmit);
    document.getElementById("poster").addEventListener('change', getImageAsByte64);
    document.getElementById("update-image").addEventListener('click', updateImage);
    display();
    preview = document.getElementById("movie-image");
}

async function fetchCategories() {
    try {
        const response = await fetch(`${BASE_URL}/movies/categories`);
        if (!response.ok) {
            throw new Error("HTTP error!");
        }
        const categories = await response.json();
        return categories;

    } catch (error) {
        console.log("An error occurred:   " + error)
    }

}

async function handleSubmit(event) {
    event.preventDefault();
    const formEl = event.target.closest("form");
    const formData = new FormData(formEl);
    const checkedCategories = getCheckedCategories();
    console.log(checkedCategories);
    const movie = {
        title: formData.get("title"),
        description: formData.get("description"),
        pgRating: formData.get("pgRating"),
        duration: formData.get("duration"),
        categories: checkedCategories,
        posterData: imageData
    }

    const response = await fetch(`${BASE_URL}/movies`, {
        method: "POST",
        headers: {"Content-Type": "application/json"},
        body: JSON.stringify(movie)
    });

    const result = await response.json();
    console.log("Movie added:", result);
    window.location.href = `movies.html`;
}

function getCheckedCategories() {
    let checkedCategories = [];
    const checkBoxes = document.querySelectorAll('input[name="category"]:checked');
    checkBoxes.forEach((checkbox) => {
        console.log(checkbox.value);
        checkedCategories.push({genre: checkbox.value})
    })
    console.log(checkedCategories);
    return checkedCategories;
}

function display() {
    container = document.getElementById("categoryContainer");
    for (let i = 0; i < categoryData.length; i++) {
        const checkBox = document.createElement("input")
        checkBox.setAttribute("type", "checkbox");
        checkBox.setAttribute("value", categoryData[i].genre);
        checkBox.setAttribute("name", "category");
        const label = document.createElement("label")
        label.textContent = `${categoryData[i].genre}`;
        label.appendChild(checkBox);
        container.appendChild(label);
    }
}

function getImageAsByte64(event) {
    const file = event.target.files[0];

    const reader = new FileReader();
    reader.onloadend = () => {
        imageData = reader.result
            .replace('data:', '')
            .replace(/^.+,/, '');
    };
    reader.readAsDataURL(file);
}

function updateImage() {
    preview.innerHTML = "";
    let image = new Image();
    image.src = `data:image/png;base64,${imageData}`;
    preview.appendChild(image);
}