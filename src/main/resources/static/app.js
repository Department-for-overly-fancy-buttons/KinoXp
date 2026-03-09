document.addEventListener("DOMContentLoaded", initApp);

const BASE_URL = "http://localhost:8080/api";
let movieContainerEl = document.querySelector("#movie-container");
let showingsData = [];


async function initApp() {
    showingsData = await fetchShowings();
    console.log(showingsData);
    displayShowings(showingsData);
    movieContainerEl.addEventListener("click", handleGetTickets);
}

async function fetchShowings() {
    try {
        const response = await fetch(`${BASE_URL}/showings`);
        if(!response.ok){
            throw new Error("HTTP error!");
        }
        const showings = await response.json();
        showingsData = showings;
        return showings;

    } catch (error) {
        console.log("An error occurred:   " + error)
    }

}

async function handleGetTickets(event){
    event.preventDefault();

    const movieBox = event.target.closest("div");
    const movieId = movieBox.getAttribute("data-movieId");
    if(movieId !== null){
        console.log("clicked movie with id = " + movieId);
        window.location.href = `Reservation.html/${movieId}`;
    }else{
        console.log("box clicked");
    }
}

function displayShowings(showings){
    movieContainerEl.innerHTML = "";
    for(let showing of showings){
        let movieBoxEl = document.createElement("div");
        movieBoxEl.classList.add("movie-box");
        //movieBoxEl.dataset.movieId = `${showing.movie.id}`;
        movieBoxEl.setAttribute("data-movieID",showing.movie.id);

        let titleElement = document.createElement("h2");
        titleElement.textContent = `${showing.movie.title}`;
        movieBoxEl.appendChild(titleElement);

        //let svgCoverImageElement = document.createElement("svg");
        //let backgroundRectElement = document.createElement("rect");
        //svgCoverImageElement.width = 100;


        //let coverImageElement = document.createElement("img");
        //coverImageElement.alt = "Missing cover image";
        //coverImageElement.src = "/images/Missing_Cover_Image.png";
        //movieBoxEl.appendChild(coverImageElement);

        let infoBarElement = document.createElement("h4");
        infoBarElement.textContent = `${showing.movie.duration} min, PG: ${showing.movie.pgRating}`;
        movieBoxEl.appendChild(infoBarElement);

        let categoryElement = document.createElement("h3");
        let categorySentencce = ``;
        let categories = showing.movie.categories;
        console.log(categories)
        for(category of categories){
            categorySentencce += `|${category.genre}|`;
            console.log(categorySentencce)
        }
        categoryElement.textContent = categorySentencce;
        movieBoxEl.appendChild(categoryElement);

        let descriptionElement = document.createElement("p");
        descriptionElement.textContent = `${showing.movie.description}`;
        movieBoxEl.appendChild(descriptionElement);

        let getTicketButton = document.createElement("button");
        getTicketButton.textContent = "Get tickets!";
        movieBoxEl.appendChild(getTicketButton);

        let linkUrlThing = document.createElement("a");
        linkUrlThing.textContent = "test";
        linkUrlThing.href = `http://localhost:8080/1`;
        movieBoxEl.appendChild(linkUrlThing);

        movieContainerEl.appendChild(movieBoxEl);
    }
}
