import {displayNavigationBar} from "./navigationBars.js";
import {displayAllShowingReel} from "./showingReel.js";
import {displayAdminMenu} from "./adminMenu.js";
document.addEventListener("DOMContentLoaded", initApp);

const BASE_URL = "http://localhost:8080/api";
let showingsData = [];


async function initApp() {
    displayNavigationBar();
    showingsData = await fetchShowings();
    console.log(showingsData);
    displayAllShowingReel(showingsData);
    if(isLoggedIn() && (getLoggedInUser().roles.includes("ROLE_EMPLOYEE") || getLoggedInUser().roles.includes("ROLE_ADMIN"))) {
        displayAdminMenu(getLoggedInUser().roles);
    }
    console.log(getLoggedInUser());
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

