import {createHtmlElement} from "./htmlTagFactory.js";

export function displayAllShowingReel(showings){
    let movieContainerEl = document.querySelector("#movie-container");
    movieContainerEl.innerHTML = "";
    for (let showing of showings) {
        let movieBoxEl = createHtmlElement({tagName: "div", htmlClass: "movie-box"})
        movieBoxEl.setAttribute("data-showingID", showing.id);

        let titleElement = createHtmlElement({tagName: "h2", htmlAttributes: {textContent: `${showing.movie.title}`}});
        movieBoxEl.appendChild(titleElement);

        //let svgCoverImageElement = document.createElement("svg");
        //let backgroundRectElement = document.createElement("rect");
        //svgCoverImageElement.width = 100;


        let coverImageElement = createHtmlElement({tagName: "img", htmlClass: "cover-image", htmlAttributes: {alt: "Missing cover image", src: "/images/Missing_Cover_Image.png"}});
        movieBoxEl.appendChild(coverImageElement);

        let infoBarElement = createHtmlElement({tagName: "h4", htmlAttributes: {textContent: `${showing.movie.duration} min, PG: ${showing.movie.pgRating}, Start Time: ${showing.startTime}`}});
        movieBoxEl.appendChild(infoBarElement);

        let categorySentencce = ``;
        let categories = showing.movie.categories;
        console.log(categories)
        for (let category of categories) {
            categorySentencce += `|${category.genre}|`;
            console.log(categorySentencce)
        }
        let categoryElement = createHtmlElement({tagName: "h3", htmlAttributes: {textContent: categorySentencce}});
        movieBoxEl.appendChild(categoryElement);

        let descriptionElement = createHtmlElement({tagName: "p", htmlAttributes: {textContent: `${showing.movie.description}`}});
        movieBoxEl.appendChild(descriptionElement);

        let getTicketButton = createHtmlElement({tagName: "button", htmlAttributes: {textContent: "Get tickets!"}});
        movieBoxEl.appendChild(getTicketButton);

        movieContainerEl.addEventListener("click", handleGetTickets);
        movieContainerEl.appendChild(movieBoxEl);

    }
}

async function handleGetTickets(event) {
    event.preventDefault();

    const movieBox = event.target.closest("div");
    const showingId = movieBox.getAttribute("data-showingId");
    if (showingId !== null) {
        console.log("clicked showing with id = " + showingId);
        window.location.href = `Reservations/Reservation.html?showingId=${showingId}`;
    } else {
        console.log("box clicked");
    }
}