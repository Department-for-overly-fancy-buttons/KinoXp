document.addEventListener("DOMContentLoaded", initApp);

const BASE_URL = "http://localhost:8080/api";
let movieContainerEl = document.querySelector("#movie-container");
let showingsData = [];


async function initApp() {
    display();
    showingsData = await fetchShowings();
    console.log(showingsData);
    displayShowings(showingsData);
    movieContainerEl.addEventListener("click", handleGetTickets);
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

function displayShowings(showings) {
    movieContainerEl.innerHTML = "";
    for (let showing of showings) {
        let movieBoxEl = document.createElement("div");
        movieBoxEl.classList.add("movie-box");
        movieBoxEl.setAttribute("data-showingID", showing.id);

        let titleElement = document.createElement("h2");
        titleElement.textContent = `${showing.movie.title}`;
        movieBoxEl.appendChild(titleElement);

        //let svgCoverImageElement = document.createElement("svg");
        //let backgroundRectElement = document.createElement("rect");
        //svgCoverImageElement.width = 100;


        let coverImageElement = document.createElement("img");
        coverImageElement.alt = "Missing cover image";
        coverImageElement.src = "/images/Missing_Cover_Image.png";
        coverImageElement.classList.add("cover-image");
        movieBoxEl.appendChild(coverImageElement);

        let infoBarElement = document.createElement("h4");
        infoBarElement.textContent = `${showing.movie.duration} min, PG: ${showing.movie.pgRating}, Start Time: ${showing.startTime}`;
        movieBoxEl.appendChild(infoBarElement);

        let categoryElement = document.createElement("h3");
        let categorySentencce = ``;
        let categories = showing.movie.categories;
        console.log(categories)
        for (let category of categories) {
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

        movieContainerEl.appendChild(movieBoxEl);
    }



}

function display(){
    console.log("displaying");

    const navEl = document.querySelector("nav");

    const listEl = TcreateHtmlElement({tagName: "ul", ids: "navigationList"});

    let listItemTitleEl = createHtmlElement("li", null, "logoTitle");
    listItemTitleEl.textContent = "KinoXP";
    listItemTitleEl.href = "/";
    listItemTitleEl.title = "Home";

    listEl.appendChild(listItemTitleEl);

    let listItemTheatersEl = createHtmlElement("li", "navigationListItem", "navigationListStart");

    let theaterLinkEl = createHtmlElement("a", "navigationLink", null);
    theaterLinkEl.href = "/theaters/theaters.html";
    theaterLinkEl.title = "Theaters";
    theaterLinkEl.textContent = "Theaters";
    listItemTheatersEl.appendChild(theaterLinkEl);

    listEl.appendChild(listItemTheatersEl);

    let listItemLoginEl = createHtmlElement("li", "navigationListItem", null);

    let loginLinkEl = createHtmlElement("a", "navigationLink", null);
    loginLinkEl.href = "/Login/LoginForm.html";
    loginLinkEl.title = "Login";
    loginLinkEl.textContent = "Login";
    listItemLoginEl.appendChild(loginLinkEl);

    listEl.appendChild(listItemLoginEl);



    navEl.appendChild(listEl);

}

function createHtmlElement(tagName, classes, ids){
    console.log(`tag: ${tagName}, classes: ${classes}, ids: ${ids}`)
    let element;

    if(typeof tagName === "string"){
        element = document.createElement(tagName);
        console.log("tag is valid string. Attempted to create element:");
        console.log(element);
    }else{
        console.log("tag is clearly an invalid string. returning element: null");
        return null;
    }

    let classesString = null;
    if(Array.isArray(classes)){
        classesString = classes.reduce((previousClass, currentClass) => previousClass += " " + currentClass);
        console.log(`classes has multiple classes ${classesString}`);
    }else if(typeof classes === "string"){
        classesString = classes;
        console.log(`classes has one string ${classesString}`);
    }
    if(classesString !== null){
        element.classList.add(classesString);
        console.log(`classesString is not null (${classesString}) element:`);
        console.log(element);
    }

    let idString = null;
    if(Array.isArray(ids)){
        idString = ids.reduce((previousClass, currentClass) => previousClass += " " + currentClass);
        console.log(`ids has multiple ids ${idString}`);
    }else if(typeof ids === "string"){
        idString = ids;
        console.log(`ids has one string ${idString}`);
    }
    if(idString !== null){
        element.id = idString;
        console.log(`idString is not null (${idString}) element:`);
        console.log(element);
    }
    console.log("returning");
    console.log(element);

    return element;

}

function TcreateHtmlElement(elementInfo){

    if(typeof elementInfo !== "object" || !("tagName" in elementInfo)){
        return null;
    }

    console.log(`tag: ${elementInfo.tagName}, classes: ${elementInfo.classes}, ids: ${elementInfo.ids}`)
    let element;

    if(typeof elementInfo.tagName === "string"){
        element = document.createElement(elementInfo.tagName);
        console.log("tag is valid string. Attempted to create element:");
        console.log(element);
    }else{
        console.log("tag is clearly an invalid string. returning element: null");
        return null;
    }

    if("classes" in elementInfo) {
        let classesString = null;
        if (Array.isArray(elementInfo.classes)) {
            classesString = elementInfo.classes.reduce((previousClass, currentClass) => previousClass += " " + currentClass);
            console.log(`classes has multiple classes ${classesString}`);
        } else if (typeof elementInfo.classes === "string") {
            classesString = elementInfo.classes;
            console.log(`classes has one string ${classesString}`);
        }
        if (classesString !== null) {
            element.classList.add(classesString);
            console.log(`classesString is not null (${classesString}) element:`);
            console.log(element);
        }
    }

    if("ids" in elementInfo) {
        let idString = null;
        if (Array.isArray(elementInfo.ids)) {
            idString = elementInfo.ids.reduce((previousClass, currentClass) => previousClass += " " + currentClass);
            console.log(`ids has multiple ids ${idString}`);
        } else if (typeof elementInfo.ids === "string") {
            idString = elementInfo.ids;
            console.log(`ids has one string ${idString}`);
        }
        if (idString !== null) {
            element.id = idString;
            console.log(`idString is not null (${idString}) element:`);
            console.log(element);
        }
    }
    console.log("returning");
    console.log(element);

    return element;

}
