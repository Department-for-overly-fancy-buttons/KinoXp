import {displayNavigationBar} from "./navigationBars.js";
import {displayAllShowingReel} from "./showingReel.js";
document.addEventListener("DOMContentLoaded", initApp);

const BASE_URL = "http://localhost:8080/api";
let showingsData = [];


async function initApp() {
    displayNavigationBar();
    showingsData = await fetchShowings();
    console.log(showingsData);
    displayAllShowingReel(showingsData);

}

async function fetchShowings() {
    try {
        const response = await fetch(`${BASE_URL}/showings`);
        if (!response.ok) {
            throw new Error("HTTP error!");
        }
        const showings = await response.json();
        showingsData = showings;
        return showings;

    } catch (error) {
        console.log("An error occurred:   " + error)
    }

}

