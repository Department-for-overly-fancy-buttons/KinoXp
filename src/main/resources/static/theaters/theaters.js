document.addEventListener("DOMContentLoaded", initApp);

import {displayShowings, handleGetTickets,BASE_URL} from "../app";

const theaterId = params.get("theaterId");
let movieContainerEl = document.querySelector("#movie-container");
let showingsData = [];

async function initApp() {
    showingsData = await fetchShowings();
    displayShowings(showingsData);
    movieContainerEl.addEventListener("click", handleGetTickets);
}

async function fetchShowings() {
    try {
        const response = await fetch(`${BASE_URL}/theaters/showings${theaterId}`);
        if (!response.ok) {
            throw new Error("HTTP error!");
        }
        const showings = await response.json();
        return showings;

    } catch (error) {
        console.log("An error occurred:   " + error)
    }

}
