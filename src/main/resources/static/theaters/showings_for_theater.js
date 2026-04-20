import {displayNavigationBar} from "../navigationBars.js";
import {displayAllShowingReel} from "../showingReel.js";

document.addEventListener("DOMContentLoaded", initApp);

const BASE_URL = "http://localhost:8090/api";
const params = new URLSearchParams(window.location.search);
const theaterId = params.get("theaterId");
let showingsData = [];

async function initApp() {
    displayNavigationBar();
    showingsData = await fetchShowings();
    displayAllShowingReel(showingsData);
}

async function fetchShowings() {
    try {
        const response = await fetch(`${BASE_URL}/theaters/showings/${theaterId}`);
        if (!response.ok) {
            throw new Error("HTTP error!");
        }
        const showings = await response.json();
        return showings;

    } catch (error) {
        console.log("An error occurred:   " + error)
    }

}
