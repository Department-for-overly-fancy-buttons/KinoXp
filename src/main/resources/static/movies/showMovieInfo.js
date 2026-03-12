document.addEventListener("DOMContentLoaded", initApp);

const BASE_URL = "http://localhost:8080/api";
const params = new URLSearchParams(window.location.search);
const movieId = params.get("movieId");
let movieData;

async function initApp() {
    movieData = await fetchMovie();
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

function displayMovie(movie){
    console.log(movie);
}