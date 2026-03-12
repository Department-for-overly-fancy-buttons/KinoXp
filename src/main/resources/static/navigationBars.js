import {createHtmlElement} from "./htmlTagFactory.js";

export function display(){
    console.log("displaying");

    const navEl = document.querySelector("nav");

    const listEl = createHtmlElement({tagName: "ul",htmlAttributes: {id: "navigationList"}});

    let listItemTitleEl = createHtmlElement({tagName: "li"});

    let homeLinkEl = createHtmlElement({tagName: "a", htmlClass: "navigationLink", htmlAttributes: {id: "logoTitle", href: "/", textContent: "KinoXP", title: "Home"}});

    listItemTitleEl.appendChild(homeLinkEl);

    listEl.appendChild(listItemTitleEl);

    let listItemTheatersEl = createHtmlElement({tagName: "li", htmlClass: "navigationListItem",htmlAttributes: {id: "navigationListStart"}});

    let theaterLinkEl = createHtmlElement({tagName: "a",htmlClass: "navigationLink", htmlAttributes: {href: "/theaters/theaters.html", title: "Theaters", textContent: "Theaters"}});
    listItemTheatersEl.appendChild(theaterLinkEl);

    listEl.appendChild(listItemTheatersEl);

    let listItemLoginEl = createHtmlElementTemp("li", "navigationListItem", null);

    let loginLinkEl = createHtmlElementTemp("a", "navigationLink", null);
    loginLinkEl.href = "/Login/LoginForm.html";
    loginLinkEl.title = "Login";
    loginLinkEl.textContent = "Login";
    listItemLoginEl.appendChild(loginLinkEl);

    listEl.appendChild(listItemLoginEl);



    navEl.appendChild(listEl);

}

function createHtmlElementTemp(tagName, htmlClass, ids){
    console.log(`tag: ${tagName}, htmlClass: ${htmlClass}, ids: ${ids}`)
    let element;

    if(typeof tagName === "string"){
        element = document.createElement(tagName);
        console.log("tag is valid string. Attempted to create element:");
        console.log(element);
    }else{
        console.log("tag is clearly an invalid string. returning element: null");
        return null;
    }

    let htmlClassString = null;
    if(Array.isArray(htmlClass)){
        htmlClassString = htmlClass.reduce((previousClass, currentClass) => previousClass += " " + currentClass);
        console.log(`htmlClass has multiple htmlClass ${htmlClassString}`);
    }else if(typeof htmlClass === "string"){
        htmlClassString = htmlClass;
        console.log(`htmlClass has one string ${htmlClassString}`);
    }
    if(htmlClassString !== null){
        element.classList.add(htmlClassString);
        console.log(`htmlClassString is not null (${htmlClassString}) element:`);
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

