import { initNavbar } from "./navbar.js";
import { initFooter } from "./footer.js";
import { showAlert } from "./modal.js";

initNavbar();
initFooter();

const CART_KEY = "cartProducts";

const cartContainer = document.getElementById("divForCartData");
const emptyCart = document.getElementById("emptyCart");
const cartWrapper = document.getElementById("divForCartDataUpperDiv");

const itemTotalEl = document.getElementById("ItemTotal");
const promoOffEl = document.getElementById("offByPromo");
const amountTotalEl = document.getElementById("amountTotal");

let cart = JSON.parse(localStorage.getItem(CART_KEY)) || [];

function saveCart() {
    localStorage.setItem(CART_KEY, JSON.stringify(cart));
}

function calcMRP() {
    return cart.reduce((sum, i) => sum + Number(i.price), 0);
}

function promoDiscount() {
    return localStorage.getItem("couponApplied") === "1"
        ? (calcMRP() * 0.1)
        : 0;
}

function calcTotal() {
    return calcMRP() - promoDiscount();
}

function updateSummary() {
    itemTotalEl.innerText = calcMRP().toFixed(2);
    promoOffEl.innerText = promoDiscount().toFixed(2);
    amountTotalEl.innerText = calcTotal().toFixed(2);
}

function removeItem(index) {
    cart.splice(index, 1);
    saveCart();
    renderCart();
    showAlert("Item removed from cart", "info");
}

function changeQty(index, qty) {
    const item = cart[index];
    const unitPrice = item.price / (item.qty || 1);
    item.qty = qty;
    item.price = unitPrice * qty;
    saveCart();
    renderCart();
}

function renderCart() {
    if (cart.length === 0) {
        emptyCart.style.display = "block";
        cartWrapper.style.display = "none";
        return;
    }

    emptyCart.style.display = "none";
    cartWrapper.style.display = "grid";
    cartContainer.innerHTML = "";

    cart.forEach((item, index) => {
        const div = document.createElement("div");
        div.className = "productDiv";

        div.innerHTML = `
            <div class="productDescription">
                <img src="${item.img}" alt="${item.food}">
                <p class="cartName">${item.food} ${item.place || ""}</p>
                <div class="delete"><i class="fa-solid fa-trash"></i></div>
            </div>
            <div class="amountdiv">
                <div class="qtyDiv">
                    <p>Quantity</p>
                    <select>
                        ${[1,2,3,4,5].map(q => 
                            `<option value="${q}" ${q === (item.qty || 1) ? "selected" : ""}>${q}</option>`
                        ).join("")}
                    </select>
                </div>
                <div class="priceDiv">
                    <p class="strike">₹ ${(item.price * 1.1).toFixed(2)}</p>
                    <h4>₹ ${item.price.toFixed(2)}</h4>
                </div>
            </div>
        `;

        div.querySelector(".delete").addEventListener("click", () => removeItem(index));
        div.querySelector("select").addEventListener("change", (e) => {
            changeQty(index, Number(e.target.value));
        });

        cartContainer.appendChild(div);
    });

    updateSummary();
}

document.getElementById("applyPromoCode")?.addEventListener("click", (e) => {
    e.preventDefault();
    const code = document.getElementById("promoCodeValue").value.trim();
    if (code === "TWILIGHT10") {
        localStorage.setItem("couponApplied", "1");
        showAlert("Promo applied successfully", "success");
    } else {
        localStorage.setItem("couponApplied", "0");
        showAlert("Invalid promo code", "error");
    }
    renderCart();
});

renderCart();
