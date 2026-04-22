import {displayNavigationBar} from "../navigationBars.js";

document.addEventListener("DOMContentLoaded", initApp);

const BASE_URL = "/api";
let movieContainerEl = document.querySelector("#movie-container");
let moviesData = [];


async function initApp() {
    displayNavigationBar();
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
    for(let i = 0 ; i < movies.length;i++){
        let image = new Image();
        image.src = `data:image/png;base64,${movies[i].poster}`;
        document.body.appendChild(image);
    }
    console.log(movies);
}