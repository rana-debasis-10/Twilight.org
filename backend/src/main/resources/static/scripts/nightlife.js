import { initNavbar } from "./navbar.js";
import { initFooter } from "./footer.js";
import { fetchJSON } from "./api.js";
import { showAlert } from "./modal.js";

initNavbar();
initFooter();

const ITEMS_CONTAINER = document.querySelector(".itemsContainer");
const NO_DATA = document.getElementById("noDataFound");

const API_BASE = "/api/nightlife";

function renderItems(items) {
    ITEMS_CONTAINER.innerHTML = "";
    items.forEach(item => {
        const card = document.createElement("div");
        card.className = "nightlife-card";

        card.innerHTML = `
            <img src="${item.img}" alt="${item.name}">
            <div class="nightlife-info">
                <h4>${item.name}</h4>
                <p>${item.address}</p>
                <p class="rating">${item.rating} ★</p>
                <p class="timing">${item.timing}</p>
                <button class="view-details">View Details</button>
            </div>
        `;

        card.querySelector(".view-details").addEventListener("click", (e) => {
            e.stopPropagation();
            localStorage.setItem("restaurantShow", JSON.stringify(item));
            window.location.href = "restaurant.html";
        });

        ITEMS_CONTAINER.appendChild(card);
    });
}

async function loadNightlife() {
    try {
        const data = await fetchJSON(API_BASE);
        if (!data || data.length === 0) {
            NO_DATA.style.display = "block";
            ITEMS_CONTAINER.innerHTML = "";
        } else {
            NO_DATA.style.display = "none";
            renderItems(data);
        }
    } catch (err) {
        showAlert("Failed to load nightlife places", "error");
    }
}

document.getElementById("search")?.addEventListener("keypress", (e) => {
    if (e.key === "Enter") {
        localStorage.setItem("nightlifeSearch", e.target.value.trim());
        loadNightlife();
    }
});

loadNightlife();
