document.addEventListener("DOMContentLoaded", initApp);

const BASE_URL = "http://localhost:8080/api";
let listEl = document.querySelector("#testList");
let showingsData = [];


async function initApp() {
    showingsData = await fetchShowings();
    console.log(showingsData);
    displayShowings(showingsData);
}

async function fetchShowings() {
    try {
        const response = await fetch(`${BASE_URL}/showings`);
        if(!response.ok){
            throw new Error("HTTP error!");
        }
        console.log("Got respone: " + response.body)
        const showings = await response.json();
        showingsData = showings;
        return showings;

    } catch (error) {
        console.log("An error occurred:   " + error)
    }

}

function displayShowings(showings){
    listEl.innerHTML = "";
    for(let showing of showings){
        let listElement = document.createElement("li");
        listElement.textContent = `${showing.movie.title}: ${showing.movie.description} - `;
        listEl.appendChild(listElement);
    }
}
