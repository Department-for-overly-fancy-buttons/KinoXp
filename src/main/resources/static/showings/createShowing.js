document.addEventListener('DOMContentLoaded', initApp);
import {displayNavigationBar} from "./navigationBars.js";

const BASE_URL = "http://localhost:8080/api";

let moviesData = [];
let theatersData = [];

async function initApp() {
    displayNavigationBar();
    requireLogIn()
    displayUser()

    moviesData = await fetchMovies();
    theatersData = await fetchTheaters();
    console.log(moviesData);
    console.log(theatersData);
    document.getElementById("submitMovie").addEventListener("click", handleSubmit);
    display();
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

async function handleSubmit(event) {
    event.preventDefault();
    const formEl = event.target.closest("form");
    const formData = new FormData(formEl);
    const movieId = getMovieId();
    const theaterId = getTheaterId();
    let is3d;
    if (formData.get("is3d") === `on`) {
        is3d = true;
    } else {
        is3d = false;
    }
    const showingRequest = {
        movieId: movieId,
        theaterId: theaterId,
        startTime: formData.get("date"),
        is3d: is3d
    }
    console.log(showingRequest);
    const response = await fetch(`${BASE_URL}/showings`, {
        method: "POST",
        headers: {"Content-Type": "application/json"},
        body: JSON.stringify(showingRequest)
    });

    const result = await response.json();
    console.log("Showing added:", result);
    window.location.href = `../index.html`;
}

function getMovieId() {
    const movie = document.getElementById("movieSelect");
    return movie.options[movie.selectedIndex].value
}

function getTheaterId() {
    const theater = document.getElementById("theaterSelect");
    return theater.options[theater.selectedIndex].value
}

function display() {
    let movieContainer = document.getElementById("movieContainer");
    let movieSelect = document.createElement("select");
    movieSelect.setAttribute("id", "movieSelect");

    for (let i = 0; i < moviesData.length; i++) {
        const option = document.createElement("option")
        option.setAttribute("label", `${moviesData[i].title}`);
        option.setAttribute("value", moviesData[i].id);
        movieSelect.appendChild(option);
    }
    movieContainer.appendChild(movieSelect);

    let theaterContainer = document.getElementById("theaterContainer");
    let theaterSelect = document.createElement("select");
    theaterSelect.setAttribute("id", "theaterSelect");
    for (let i = 0; i < theatersData.length; i++) {
        const option = document.createElement("option")
        option.setAttribute("label", `${theatersData[i].theaterName}`);
        option.setAttribute("value", theatersData[i].id);
        theaterSelect.appendChild(option);
    }
    theaterContainer.appendChild(theaterSelect);

}