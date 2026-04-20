import {displayNavigationBar} from "../navigationBars.js";

document.addEventListener("DOMContentLoaded", initApp);

const BASE_URL = "http://localhost:8090/api";
const params = new URLSearchParams(window.location.search);
const movieId = params.get("movieId");
let movieData;

async function initApp() {
    displayNavigationBar();
    movieData = await fetchMovie();
    console.log(movieData);
    displayMovie(movieData);
}

async function fetchMovie() {
    try {
        const response = await fetch(`${BASE_URL}/movies/${movieId}`);
        if (!response.ok) {
            throw new Error("HTTP error!");
        }
        return await response.json();

    } catch (error) {
        console.log("An error occurred:   " + error)
    }

}

function displayMovie(movie) {
    let image = new Image();
    image.src = `data:image/png;base64,${movie.poster}`;
    document.body.appendChild(image);
    console.log(movie);
}