export function display(){
    const navEl = document.querySelector("nav");

    const listEl = document.createElement("ul");
    listEl.id = "navigationList";

    let listItemTitleEl = document.createElement("a");
    listItemTitleEl.id = "logoTitle";
    listItemTitleEl.textContent = "KinoXP";
    listItemTitleEl.href = "/";
    listItemTitleEl.title = "Home";

    listEl.appendChild(listItemTitleEl);

    let listItemTheatersEl = document.createElement("li");
    listItemTheatersEl.id = "navigationListStart";
    listItemTheatersEl.classList.add("navigationListItem");

    let theaterLinkEl = document.createElement("a");
    theaterLinkEl.classList.add("navigationLink");
    theaterLinkEl.href = "/theaters/theaters.html";
    theaterLinkEl.title = "Theaters";
    theaterLinkEl.textContent = "Theaters";
    listItemTheatersEl.appendChild(theaterLinkEl);

    listEl.appendChild(listItemTheatersEl);

    let listItemLoginEl = document.createElement("li");
    listItemLoginEl.classList.add("navigationListItem");

    let loginLinkEl = document.createElement("a");
    loginLinkEl.classList.add("navigationLink");
    loginLinkEl.href = "/Login/LoginForm.html";
    loginLinkEl.title = "Login";
    loginLinkEl.textContent = "Login";
    listItemLoginEl.appendChild(loginLinkEl);

    listEl.appendChild(listItemLoginEl);



    navEl.appendChild(listEl);

}