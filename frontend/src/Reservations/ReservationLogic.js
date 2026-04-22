import {displayNavigationBar} from "../navigationBars.js";
document.addEventListener('DOMContentLoaded', initApp);

const BASE_URL = "/api";
let reservedSeats = [];
let selectedSeats = [];
const params = new URLSearchParams(window.location.search);
const showingId = params.get("showingId");
let showingData;

let numberOfRows;
let seatsPerRow;
let container;

async function initApp() {
    displayNavigationBar();
    showingData = await fetchShowing();
    reservedSeats = await fetchReservationsForShowing();
    console.log("reserved seats:");
    console.log(reservedSeats);
    console.log("showings:");
    console.log(showingData);
    await displayTheater();
    document.getElementById("submitSeats").addEventListener("click", handleSubmit);

}

async function handleSubmit(event) {
    //const reservationId = document.getElementById("reservationId").value;
    //if (!reservationId) return alert("Please create a reservation first!");
    event.preventDefault();
    const formEl = event.target.closest("form");
    const formData = new FormData(formEl);
    const reservation = {
        firstName: formData.get("firstName"),
        lastName: formData.get("lastName"),
        email: formData.get("email"),
        phoneNumber: formData.get("phoneNumber"),
        tickets: selectedSeats,
        showingId: showingId
    };

    const response = await fetch(`${BASE_URL}/reservations`, {
        method: "POST",
        headers: {"Content-Type": "application/json"},
        body: JSON.stringify(reservation)
    });

    const result = await response.json();
    console.log("Seats added:", result);
    alert("Seats successfully added! and sent to your Email or Number");
    selectedSeats = [];
    await initApp();
}

async function fetchShowing() {
    try {
        console.log(showingId)
        const response = await fetch(`${BASE_URL}/showings/${showingId}`);
        if (!response.ok) {
            throw new Error("HTTP error!");
        }
        return await response.json();

    } catch (error) {
        console.log("An error occurred:   " + error)
    }

}

async function fetchReservationsForShowing() {
    try {
        const response = await fetch(`${BASE_URL}/showings/tickets/${showingId}`);
        if (!response.ok) {
            throw new Error("HTTP error!");
        }
        return await response.json();

    } catch (error) {
        console.log("An error occurred:   " + error)
    }
}

async function displayTheater() {
    const title = document.getElementById("reservation-title")
    const monthNames = ["Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"];
    let showingDate = Temporal.PlainDateTime.from(showingData.startTime);
    title.textContent = `Reservation for ${showingData.movie.title} \nDate: ${monthNames[showingDate.month - 1]}: ${showingDate.day} \nTime: ${showingDate.hour}:${showingDate.minute}`
    title.setAttribute('style', 'white-space: pre;');

    numberOfRows = showingData.theater.numberOfRows;
    seatsPerRow = showingData.theater.seatsPerRow;
    container = document.getElementById("seatsContainer");
    container.innerHTML = "";
    const theaterRowTypes = showingData.theater.theaterRows;
    console.log(theaterRowTypes)


    for (let row = 1; row <= numberOfRows; row++) {
        const rowDiv = document.createElement("div");
        rowDiv.classList.add("seat-row");
        let ticketType = theaterRowTypes[row - 1].ticketType;
        console.log(`ticketType for row ${row}:`);
        console.log(ticketType)

        for (let seat = 1; seat <= seatsPerRow; seat++) {
            const seatBtn = document.createElement("button");
            seatBtn.textContent = seat;
            seatBtn.classList.add("seat");
            const isReserved = reservedSeats.some(currentSeat => currentSeat.rowNumber === row && currentSeat.seatNumber === seat);

            console.log(isReserved);

            if (isReserved) {
                seatBtn.classList.add("reserved");
                seatBtn.disabled = true;
            }

            seatBtn.addEventListener("click", (event) => {
                event.preventDefault();

                const index = selectedSeats.findIndex(
                    ticket => ticket.rowNumber === row && ticket.seatNumber === seat
                );

                if (index === -1) {
                    selectedSeats.push({rowNumber: row, seatNumber: seat, ticketType: ticketType});
                    seatBtn.classList.add("selected");
                } else {
                    selectedSeats.splice(index, 1);
                    seatBtn.classList.remove("selected");
                }

                console.log(selectedSeats);
            });

            rowDiv.appendChild(seatBtn);
        }
        container.appendChild(rowDiv);
    }
}

