document.addEventListener('DOMContentLoaded', initApp);
import {displayNavigationBar} from "./navigationBars.js";

const BASE_URL = "http://localhost:8080/api";

let numberOfRows;
let seatsPerRow;
let ticketTypeData = [];

async function initApp() {
    displayNavigationBar();
    requireLogIn()
    displayUser()
    ticketTypeData = await fetchTicketTypes();
    console.log(ticketTypeData);
    document.getElementById("submitTheater").addEventListener("click", handleSubmit);
    document.getElementById("rows").addEventListener("change", displayTheater);
    document.getElementById("seats").addEventListener("change", displayTheater);
}

async function fetchTicketTypes() {
    try {
        const response = await fetch(`${BASE_URL}/tickets/types`);
        if (!response.ok) {
            throw new Error("HTTP error!");
        }
        const ticketTypes = await response.json();
        return ticketTypes;

    } catch (error) {
        console.log("An error occurred:   " + error)
    }

}

async function handleSubmit(event) {
    event.preventDefault();

    const formEl = event.target.closest("form");
    const formData = new FormData(formEl);
    const ticketTypesForRows = getSelectedTicketTypes();
    console.log(ticketTypesForRows);
    const theater = {
        theaterName: formData.get("theaterName"),
        location: formData.get("location"),
        numberOfRows: formData.get("numberOfRows"),
        seatsPerRow: formData.get("seatsPerRow"),
        rows: ticketTypesForRows
    }

    const response = await fetch(`${BASE_URL}/theaters`, {
        method: "POST",
        headers: {"Content-Type": "application/json"},
        body: JSON.stringify(theater)
    });

    const result = await response.json();
    console.log("Theater added:", result);
    window.location.href = `theaters.html`;
}

function getSelectedTicketTypes() {
    let selectedTicketTypes = [];
    for (let i = 0; i < numberOfRows; i++) {
        const row = document.getElementById(`row${i + 1}`);
        const ticketTypeForRow = {
            rowNumber: i + 1,
            ticketType: {
                ticketType: row.options[row.selectedIndex].label,
                price: row.options[row.selectedIndex].value
            }
        }
        selectedTicketTypes.push(ticketTypeForRow)
    }
    return selectedTicketTypes;
}

function displayTheater() {
    numberOfRows = document.getElementById("rows").value;
    seatsPerRow = document.getElementById("seats").value;
    container = document.getElementById("rowContainer");
    container.innerHTML = "";
    for (let row = 1; row <= numberOfRows; row++) {
        const rowDiv = document.createElement("div");
        rowDiv.classList.add("seat-row");
        const select = document.createElement("select");
        select.setAttribute("id", `row${row}`);

        for (let seat = 1; seat <= seatsPerRow; seat++) {
            const seatBtn = document.createElement("button");
            seatBtn.textContent = seat;
            rowDiv.appendChild(seatBtn);
            seatBtn.classList.add("seat");
        }
        for (let i = 0; i < ticketTypeData.length; i++) {
            const option = document.createElement("option")
            option.setAttribute("label", `${ticketTypeData[i].ticketType}`);
            option.setAttribute("value", ticketTypeData[i].price);

            if (ticketTypeData[i].ticketType === "Basic") {
                option.setAttribute("selected", "selected");
            }
            select.appendChild(option);
        }
        rowDiv.appendChild(select);
        container.appendChild(rowDiv);
    }
}