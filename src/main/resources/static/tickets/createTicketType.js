document.addEventListener('DOMContentLoaded', initApp);
import {displayNavigationBar} from "../navigationBars.js";

const BASE_URL = "http://localhost:8080/api";

async function initApp() {
    displayNavigationBar();
    document.getElementById("submitTicketType").addEventListener("click", handleSubmit);
}

async function handleSubmit(event) {
    event.preventDefault();
    const formEl = event.target.closest("form");
    const formData = new FormData(formEl);
    const ticketType = {
        ticketType: formData.get("ticket-type"),
        price: formData.get("price")
    }

    const response = await fetch(`${BASE_URL}/tickets/new/type`, {
        method: "POST",
        headers: {"Content-Type": "application/json"},
        body: JSON.stringify(ticketType)
    });

    const result = await response.json();
    console.log("Category added:", result);
    alert(`${ticketType.ticketType}` + " was added");
}