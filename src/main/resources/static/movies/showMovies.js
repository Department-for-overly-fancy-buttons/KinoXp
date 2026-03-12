document.addEventListener("DOMContentLoaded", initApp);

const BASE_URL = "http://localhost:8080/api";
let movieContainerEl = document.querySelector("#movie-container");
let moviesData = [];


async function initApp() {
    moviesData = await fetchMovies();
    movieContainerEl.addEventListener("click", handleGetMovie);
    displayMovies(moviesData);
}

async function fetchMovies() {
    try {
        const response = await fetch(`${BASE_URL}/movies`);
        if (!response.ok) {
            throw new Error("HTTP error!");
        }
        return await response.json();

    } catch (error) {
        console.log("An error occurred:   " + error)
    }
}

async function handleGetMovie(event) {
    event.preventDefault();

    const movieBox = event.target.closest("div");
    const movieId = movieBox.getAttribute("data-movieId");
    if (movieId !== null) {
        console.log("clicked movie with id = " + movieId);
        window.location.href = `movie_info.html?movieId=${movieId}`;
    } else {
        console.log("box clicked");
    }
}

function displayMovies(movies) {
    console.log(movies);
}