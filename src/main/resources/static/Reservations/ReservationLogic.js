document.addEventListener('DOMContentLoaded', initApp);

const BASE_URL = "http://localhost:8080/api";

let selectedSeats = [];
const params = new URLSearchParams(window.location.search);
const showingId = params.get("showingId");
let showingData;

let numberOfRows;
let seatsPerRow;
let container;

async function initApp() {
    showingData = await fetchShowing();
    console.log(showingData);
    displayTheater();
    document.getElementById("submitSeats").addEventListener("click", handleSubmit);

}

async function handleSubmit (event){
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
        showing: showingData
    };

    const response = await fetch(`${BASE_URL}/reservations`, {
        method: "POST",
        headers: {"Content-Type": "application/json"},
        body: JSON.stringify(reservation)
    });

    const result = await response.json();
    console.log("Seats added:", result);
    alert("Seats successfully added! and sent to your Email or Number");
}

async function fetchShowing() {
    try {
        const response = await fetch(`${BASE_URL}/showings/${showingId}`);
        if (!response.ok) {
            throw new Error("HTTP error!");
        }
        const showing = await response.json();
        return showing;

    } catch (error) {
        console.log("An error occurred:   " + error)
    }

}


document.getElementById("ReservationForm").addEventListener("submit", async (event) => {
    event.preventDefault();
    const formData = new FormData(event.target);

    const reservation = {
        firstName: formData.get("firstName"),
        lastName: formData.get("lastName"),
        email: formData.get("email"),
        phoneNumber: formData.get("phoneNumber")
    };

    const createdReservation = await addReservation(reservation);
    console.log("Created Reservation:", createdReservation);

    document.getElementById("reservationId").value = createdReservation.id;

    alert("Reservation created! Now select your seats.");
    event.target.reset();
});

async function addReservation(reservation) {
    const response = await fetch(`${BASE_URL}/reservations`, {
        method: "POST",
        headers: {"Content-Type": "application/json"},
        body: JSON.stringify(reservation)
    });
    return await response.json();
}

function displayTheater() {
    numberOfRows = showingData.theater.numberOfRows;
    seatsPerRow = showingData.theater.seatsPerRow;
    container = document.getElementById("seatsContainer");


    for (let row = 1; row <= numberOfRows; row++) {
        const rowDiv = document.createElement("div");
        rowDiv.classList.add("seat-row");

        for (let seat = 1; seat <= seatsPerRow; seat++) {
            const seatBtn = document.createElement("button");
            seatBtn.textContent = seat;
            seatBtn.classList.add("seat");

            seatBtn.addEventListener("click", (event) => {
                event.preventDefault();
                const index = selectedSeats.findIndex(ticket => ticket.row === row && ticket.seatNumber === seat);
                if (index === -1) {
                    selectedSeats.push({rowNumber: row, seatNumber: seat});
                    seatBtn.classList.add("selected");
                    console.log(selectedSeats);
                } else {
                    selectedSeats.splice(index, 1);
                    seatBtn.classList.remove("selected");
                }
            });

            rowDiv.appendChild(seatBtn);
        }
        container.appendChild(rowDiv);
    }
}

