import {displayNavigationBar} from "../navigationBars.js";
document.addEventListener("DOMContentLoaded", initApp);

const BASE_URL = "/api";
let theaterContainerEl = document.querySelector("#theater-container");
let theaterData = [];

async function initApp() {
    displayNavigationBar();
    theaterData = await fetchTheaters();
    console.log(theaterData);
    displayTheaters(theaterData);
    theaterContainerEl.addEventListener("click", handleGetTheater);
}

async function fetchTheaters() {
    try {
        const response = await fetch(`${BASE_URL}/theaters`);
        if (!response.ok) {
            throw new Error("HTTP error!");
        }
        return await response.json();

    } catch (error) {
        console.log("An error occurred:   " + error)
    }

}

async function handleGetTheater(event) {
    event.preventDefault();

    const theaterBox = event.target.closest("div");
    const theaterId = theaterBox.getAttribute("data-theaterId");
    if (theaterId !== null) {
        console.log("clicked theater with id = " + theaterId);
        window.location.pathname = `theaters/showings_for_theater.html?theaterId=${theaterId}`
    } else {
        console.log("box clicked");
    }
}

function displayTheaters(theaters) {
    theaterContainerEl.innerHTML = "";
    for (let theater of theaters) {
        let theaterBoxEl = document.createElement("div");
        theaterBoxEl.classList.add("theater-box");
        theaterBoxEl.setAttribute("data-theaterID", theater.id);

        let titleElement = document.createElement("h2");
        titleElement.textContent = `${theater.theaterName}`;
        theaterBoxEl.appendChild(titleElement);

        let coverImageElement = document.createElement("img");
        coverImageElement.alt = "Missing cover image";
        coverImageElement.src = "/images/Missing_Cover_Image.png";
        coverImageElement.classList.add("cover-image");
        theaterBoxEl.appendChild(coverImageElement);

        let descriptionElement = document.createElement("h4");
        descriptionElement.textContent = `${theater.location}`;
        theaterBoxEl.appendChild(descriptionElement);

        let getTheaterButton = document.createElement("button");
        getTheaterButton.textContent = "View showings!";
        theaterBoxEl.appendChild(getTheaterButton);

        theaterContainerEl.appendChild(theaterBoxEl);
    }
}