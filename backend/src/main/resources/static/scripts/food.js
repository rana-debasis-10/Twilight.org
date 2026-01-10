import { initNavbar } from "./navbar.js";
import { initFooter } from "./footer.js";
import { fetchJSON } from "./api.js";
import { showAlert } from "./modal.js";

initNavbar();
initFooter();

const ITEMS_CONTAINER = document.querySelector(".itemsContainer");
const NO_DATA = document.getElementById("noDataFound");

const API_BASE = "/api/food"; // dummy backend endpoint

function renderItems(items) {
    ITEMS_CONTAINER.innerHTML = "";
    items.forEach(item => {
        const card = document.createElement("div");
        card.className = "food-card";

        card.innerHTML = `
            <img src="${item.img}" alt="${item.food}">
            <div class="food-info">
                <h4>${item.food}</h4>
                <p>${item.place}</p>
                <p class="rating">${item.rating} ★</p>
                <p class="price">₹ ${item.price}</p>
                <button class="add-to-cart">Add to Cart</button>
            </div>
        `;

        card.querySelector(".add-to-cart").addEventListener("click", (e) => {
            e.stopPropagation();
            addToCart(item);
        });

        card.addEventListener("click", () => {
            localStorage.setItem("foodShow", JSON.stringify(item));
            window.location.href = "food-description.html";
        });

        ITEMS_CONTAINER.appendChild(card);
    });
}

function addToCart(item) {
    const cart = JSON.parse(localStorage.getItem("cartProducts")) || [];
    const exists = cart.find(p => p.id === item.id);

    if (exists) {
        showAlert("Item already in cart", "error");
        return;
    }

    cart.push({ ...item, qty: 1 });
    localStorage.setItem("cartProducts", JSON.stringify(cart));
    showAlert("Item added to cart");
}

async function loadFood() {
    const search = localStorage.getItem("foodItem") || "";
    try {
        const data = await fetchJSON(`${API_BASE}?q=${search}`);
        if (!data || data.length === 0) {
            NO_DATA.style.display = "block";
            ITEMS_CONTAINER.innerHTML = "";
        } else {
            NO_DATA.style.display = "none";
            renderItems(data);
        }
    } catch (err) {
        showAlert("Failed to load food items", "error");
    }
}

document.getElementById("search")?.addEventListener("keypress", (e) => {
    if (e.key === "Enter") {
        localStorage.setItem("foodItem", e.target.value.trim());
        loadFood();
    }
});

loadFood();
