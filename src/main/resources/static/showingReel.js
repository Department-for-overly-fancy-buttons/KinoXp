import {createHtmlElement, createSvgElement} from "./htmlTagFactory.js";

export function displayAllShowingReel(showings) {
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


        let titleElement = createHtmlElement({
            tagName: "h4",
            htmlClass: "movie-title",
            htmlAttributes: {textContent: `${showing.movie.title}`, title: `${showing.movie.title}`}
        });
        movieBoxEl.appendChild(titleElement);


        //let svgCoverImageElement = document.createElement("svg");
        //let backgroundRectElement = document.createElement("rect");
        //svgCoverImageElement.width = 100;


        if (showing.movie.poster !== null) {
            let coverImageElement = createHtmlElement({
                tagName: "img",
                htmlClass: "cover-image",
                htmlAttributes: {alt: "Missing cover image", src: `data:image/png;base64,${showing.movie.poster}`}
            });
            movieBoxEl.appendChild(coverImageElement);
            console.log(showing.movie.poster);
        } else {
            let coverImageElement = createHtmlElement({
                tagName: "img",
                htmlClass: "cover-image",
                htmlAttributes: {alt: "Missing cover image", src: "/images/Missing_Cover_Image.png"}
            });
            movieBoxEl.appendChild(coverImageElement);
        }

        const monthNames = ["Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"];
        let showingDate = Temporal.PlainDateTime.from(showing.startTime);
        let hour = String(showingDate.hour).padStart(2, "0");
        let minute = String(showingDate.minute).padStart(2, "0");
        let infoBarElement = createHtmlElement({
            tagName: "h4",
            htmlClass: "movie-date",
            htmlAttributes: {textContent: `${monthNames[showingDate.month - 1]}: ${showingDate.day} \n${hour}:${minute}`}
        });
        infoBarElement.setAttribute('style', 'white-space: pre;');
        movieBoxEl.appendChild(infoBarElement);

        /*let categorySentencce = ``;
        let categories = showing.movie.categories;
        console.log(categories)
        for (let category of categories) {
            categorySentencce += `|${category.genre}|`;
            console.log(categorySentencce)
        }
        let categoryElement = createHtmlElement({tagName: "h3", htmlAttributes: {textContent: categorySentencce}});
        movieBoxEl.appendChild(categoryElement);*/

        /*
        let descriptionElement = createHtmlElement({tagName: "p", htmlAttributes: {textContent: `${showing.movie.description}`}});
        movieBoxEl.appendChild(descriptionElement);
         */

        let getTicketButton = createHtmlElement({
            tagName: "button",
            htmlClass: "get-tickets-button",
            htmlAttributes: {textContent: "Get tickets!"}
        });
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

function createTapeHoles() {
    let tapeHoleHeight = 15;
    let verticalOffset = 20;
    let svgHeight = tapeHoleHeight + 2 * verticalOffset;
    let tapeHolesSVGEl = createSvgElement({tagName: "svg", svgAttributes: {width: "100%", height: `${svgHeight}`}});
    //defs tag
    let defsEl = createSvgElement({tagName: "defs"});
    tapeHolesSVGEl.appendChild(defsEl);
    //holes pattern (blueprint)
    let patternEl = createSvgElement({
        tagName: "pattern",
        svgAttributes: {
            id: "tapeTopHoles",
            x: "0",
            y: "0",
            width: "40",
            height: `${svgHeight}`,
            patternUnits: "userSpaceOnUse"
        }
    });
    defsEl.appendChild(patternEl);

    let tapeHoleRectEl = createSvgElement({
        tagName: "rect",
        svgAttributes: {
            width: "25",
            height: `${tapeHoleHeight}`,
            x: "10",
            y: `${verticalOffset}`,
            rx: "2",
            ry: "2",
            fill: `${getComputedStyle(document.documentElement).getPropertyValue("--bgColor")}`
        }
    });

    patternEl.appendChild(tapeHoleRectEl);

    let svgContainerEl = createSvgElement({
        tagName: "rect",
        svgAttributes: {width: "100%", height: `${svgHeight}`, x: "0", y: "0", fill: "url(#tapeTopHoles)"}
    });
    tapeHolesSVGEl.appendChild(svgContainerEl);

    return tapeHolesSVGEl;
}