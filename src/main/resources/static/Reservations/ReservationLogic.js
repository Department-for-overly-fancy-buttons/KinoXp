document.addEventListener('DOMContentLoaded', initApp);

const BASE_URL = "http://localhost:8080/api/reservations";
let selectedSeats = [];

async function initApp() {

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
    const response = await fetch(BASE_URL, {
        method: "POST",
        headers: {"Content-Type": "application/json"},
        body: JSON.stringify(reservation)
    });
    return await response.json();
}


const numberOfRows = 5;
const seatsPerRow = 8;
const container = document.getElementById("seatsContainer");


for (let row = 1; row <= numberOfRows; row++) {
    const rowDiv = document.createElement("div");
    rowDiv.classList.add("seat-row");

    for (let seat = 1; seat <= seatsPerRow; seat++) {
        const seatBtn = document.createElement("button");
        seatBtn.textContent = seat;
        seatBtn.classList.add("seat");

        seatBtn.addEventListener("click", () => {
            const index = selectedSeats.findIndex(s => s.row === row && s.seatNumber === seat);
            if (index === -1) {
                selectedSeats.push({row, seatNumber: seat});
                seatBtn.classList.add("selected");
            } else {
                selectedSeats.splice(index, 1);
                seatBtn.classList.remove("selected");
            }
        });

        rowDiv.appendChild(seatBtn);
    }
    container.appendChild(rowDiv);
}


document.getElementById("submitSeats").addEventListener("click", async () => {
    const reservationId = document.getElementById("reservationId").value;
    if (!reservationId) return alert("Please create a reservation first!");

    const response = await fetch(`${BASE_URL}/${reservationId}/seats`, {
        method: "POST",
        headers: {"Content-Type": "application/json"},
        body: JSON.stringify(selectedSeats)
    });

    const result = await response.json();
    console.log("Seats added:", result);
    alert("Seats successfully added!");
});