import {createHtmlElement, createSvgElement} from "./htmlTagFactory.js";

export function displayAllShowingReel(showings){
    let tapeContainerEl = document.querySelector("#tape-container");
    let movieContainerEl = createHtmlElement({tagName: "div", htmlAttributes: {id: "movie-container"}});
    movieContainerEl.innerHTML = "";

    //Tape holes
    //svg tag
    let topTapeHolesSVGEl = createTapeHoles();
    tapeContainerEl.appendChild(topTapeHolesSVGEl);

    for (let showing of showings) {
        let movieBoxEl = createHtmlElement({tagName: "div", htmlClass: "movie-box"})
        movieBoxEl.setAttribute("data-showingID", showing.id);

        let titleElement = createHtmlElement({tagName: "h2", htmlAttributes: {textContent: `${showing.movie.title}`}});
        movieBoxEl.appendChild(titleElement);


        //let svgCoverImageElement = document.createElement("svg");
        //let backgroundRectElement = document.createElement("rect");
        //svgCoverImageElement.width = 100;


        if(showing.movie.poster !== null) {
            let coverImageElement = createHtmlElement({
                tagName: "img",
                htmlClass: "cover-image",
                htmlAttributes: {alt: "Missing cover image", src: `data:image/png;base64,${showing.movie.poster}`}
            });
            movieBoxEl.appendChild(coverImageElement);
            console.log(showing.movie.poster);
        } else{
            let coverImageElement = createHtmlElement({
                tagName: "img",
                htmlClass: "cover-image",
                htmlAttributes: {alt: "Missing cover image", src: "/images/Missing_Cover_Image.png"}
            });
            movieBoxEl.appendChild(coverImageElement);
        }
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

        /*
        let descriptionElement = createHtmlElement({tagName: "p", htmlAttributes: {textContent: `${showing.movie.description}`}});
        movieBoxEl.appendChild(descriptionElement);
         */

        let getTicketButton = createHtmlElement({tagName: "button", htmlAttributes: {textContent: "Get tickets!"}});
        movieBoxEl.appendChild(getTicketButton);

        movieContainerEl.addEventListener("click", handleGetTickets);
        movieContainerEl.appendChild(movieBoxEl);
        tapeContainerEl.appendChild(movieContainerEl);

    }
    let bottomTapeHolesSVGEl = createTapeHoles();
    tapeContainerEl.appendChild(bottomTapeHolesSVGEl);
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

function createTapeHoles(){
    let tapeHolesSVGEl = createSvgElement({tagName: "svg", svgAttributes: {width: "100%", height: "20"}});
    //defs tag
    let defsEl = createSvgElement({tagName: "defs"});
    tapeHolesSVGEl.appendChild(defsEl);
    //holes pattern (blueprint)
    let patternEl = createSvgElement({tagName: "pattern", svgAttributes: {id: "tapeTopHoles", x: "0", y: "0", width: "20", height: "20", patternUnits: "userSpaceOnUse"}});
    defsEl.appendChild(patternEl);

    let tapeHoleRectEl = createSvgElement({tagName: "rect", svgAttributes: {width: "10", height: "10", x: "10", y: "10", rx: "2", ry: "2", fill: `${getComputedStyle(document.documentElement).getPropertyValue("--bgColor")}`}});

    patternEl.appendChild(tapeHoleRectEl);

    let svgContainerEl = createSvgElement({tagName: "rect", svgAttributes: {width: "100%", height: "20", x: "0", y: "0",  fill: "url(#tapeTopHoles)"}});
    tapeHolesSVGEl.appendChild(svgContainerEl);

    return tapeHolesSVGEl;
}