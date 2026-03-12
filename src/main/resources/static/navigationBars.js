import {createHtmlElement} from "./htmlTagFactory.js";

export function display(){
    console.log("displaying");

    const navEl = document.querySelector("nav");

    const listEl = createHtmlElement({tagName: "ul", ids: "navigationList"});

    let listItemTitleEl = createHtmlElement({tagName: "li"});

    let homeLinkEl = createHtmlElement({tagName: "a", classes: "navigationLink", ids: "logoTitle",htmlAttributes: {href: "/", textContent: "KinoXP", title: "Home"}});

    listItemTitleEl.appendChild(homeLinkEl);

    listEl.appendChild(listItemTitleEl);

    let listItemTheatersEl = createHtmlElement({tagName: "li", classes: "navigationListItem",ids: "navigationListStart"});

    let theaterLinkEl = createHtmlElement({tagName: "a",classes: "navigationLink", htmlAttributes: {href: "/theaters/theaters.html", title: "Theaters", textContent: "Theaters"}});
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

function createHtmlElementTemp(tagName, classes, ids){
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

