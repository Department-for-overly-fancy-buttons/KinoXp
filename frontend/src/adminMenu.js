import {createHtmlElement} from "./htmlTagFactory.js";

export function displayAdminMenu(roles){

    const bodyEL = document.body;

    const panelEl = createHtmlElement({tagName: "div", htmlAttributes: {id: "admin-panel"}})

    const listEl = createHtmlElement({tagName: "ul"});
    if(roles.includes("ROLE_ADMIN")) {
        let listItemUsersEl = createHtmlElement({tagName: "li"});

        let showUserLinkEl = createHtmlElement({
            tagName: "a",
            htmlClass: "navigationLink",
            htmlAttributes: {href: "/users/ShowUsers.html", textContent: "User operations", title: "Edit users"}
        });

        listItemUsersEl.appendChild(showUserLinkEl);
        listEl.appendChild(listItemUsersEl);

        let listItemTicketEl = createHtmlElement({tagName: "li"});

        let createTicketLinkEl = createHtmlElement({
            tagName: "a",
            htmlClass: "navigationLink",
            htmlAttributes: {href: "/tickets/add_ticket_type.html", textContent: "Ticket operations", title: "Edit users"}
        });

        listItemTicketEl.appendChild(createTicketLinkEl);
        listEl.appendChild(listItemTicketEl);

        let listItemTheaterEl = createHtmlElement({tagName: "li"});

        let createTeaterLinkEl = createHtmlElement({
            tagName: "a",
            htmlClass: "navigationLink",
            htmlAttributes: {href: "/theaters/theater_create_form.html", textContent: "Theater operations", title: "Edit theaters"}
        });

        listItemTheaterEl.appendChild(createTeaterLinkEl);
        listEl.appendChild(listItemTheaterEl);

        let listItemCategoryEl = createHtmlElement({tagName: "li"});

        let createCategoryLinkEl = createHtmlElement({
            tagName: "a",
            htmlClass: "navigationLink",
            htmlAttributes: {href: "/movies/add_category.html", textContent: "create a category", title: "Add category"}
        });

        listItemCategoryEl.appendChild(createCategoryLinkEl);
        listEl.appendChild(listItemCategoryEl);
    }

    let listItemShowingsEl = createHtmlElement({tagName: "li"});

    let createShowingLinkEl = createHtmlElement({
        tagName: "a",
        htmlClass: "navigationLink",
        htmlAttributes: {href: "/showings/showing_create_form.html", textContent: "create a showing", title: "Add showing"}
    });

    listItemShowingsEl.appendChild(createShowingLinkEl);
    listEl.appendChild(listItemShowingsEl);

    let listItemMoviesEl = createHtmlElement({tagName: "li"});

    let createMovieLinkEl = createHtmlElement({
        tagName: "a",
        htmlClass: "navigationLink",
        htmlAttributes: {href: "/movies/movie_create_form.html", textContent: "create a movie", title: "Add movie"}
    });

    listItemMoviesEl.appendChild(createMovieLinkEl);
    listEl.appendChild(listItemMoviesEl);

    /*let listItemCreateReservationEl = createHtmlElement({tagName: "li"});

    let createReservationLinkEl = createHtmlElement({
        tagName: "a",
        htmlClass: "navigationLink",
        htmlAttributes: {href: "http://localhost:8080/Reservations/Reservations.html", textContent: "create a reservation", title: "Add reservation"}
    });

    listItemCreateReservationEl.appendChild(createReservationLinkEl);
    listEl.appendChild(listItemCreateReservationEl);*/

    panelEl.appendChild(listEl);

    bodyEL.appendChild(panelEl);

}