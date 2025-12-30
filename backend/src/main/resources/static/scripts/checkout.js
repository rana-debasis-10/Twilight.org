import { initNavbar } from "./navbar.js";
import { initFooter } from "./footer.js";
import { showAlert } from "./modal.js";

initNavbar();
initFooter();

const CART_KEY = "cartProducts";

const addressForm = document.getElementById("addressForm");
const showDetails = document.getElementById("showDetails");
const confirmBtn = document.getElementById("confirmAddress");

let cart = JSON.parse(localStorage.getItem(CART_KEY)) || [];

if (cart.length === 0) {
    location.href = "cart.html";
}

// Save address
addressForm.addEventListener("submit", (e) => {
    e.preventDefault();

    const city = document.getElementById("cityName").value;
    const landmark = document.getElementById("landmark").value;
    const pincode = document.getElementById("pincode").value;

    if (!city || !pincode) {
        showAlert("Address details missing", "error");
        return;
    }

    const address = { city, landmark, pincode };
    localStorage.setItem("deliveryAddress", JSON.stringify(address));

    document.getElementById("userCityName").innerText = city;
    document.getElementById("userLandmark").innerText = landmark;
    document.getElementById("userPincode").innerText = pincode;

    showDetails.style.visibility = "visible";
    showAlert("Address saved", "success");
});

// Confirm address
confirmBtn.addEventListener("click", () => {
    const address = JSON.parse(localStorage.getItem("deliveryAddress"));
    if (!address) {
        showAlert("Please add delivery address", "error");
        return;
    }
    location.href = "payments.html";
});

// Order summary
const summaryBox = document.getElementById("orderSummary");

function calcTotal() {
    return cart.reduce((sum, i) => sum + Number(i.price), 0);
}

function renderSummary() {
    summaryBox.innerHTML = "";
    cart.forEach((item) => {
        const p = document.createElement("p");
        p.innerText = `${item.food} × ${item.qty || 1}`;
        summaryBox.appendChild(p);
    });

    const total = document.createElement("h4");
    total.innerText = `Total: ₹ ${calcTotal().toFixed(2)}`;
    summaryBox.appendChild(total);
}

renderSummary();
