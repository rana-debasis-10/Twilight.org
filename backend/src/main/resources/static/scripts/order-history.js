import { initNavbar } from "./navbar.js";
import { initFooter } from "./footer.js";

initNavbar();
initFooter();

const history = JSON.parse(localStorage.getItem("orderHistory")) || [];
const container = document.getElementById("orderHistory");

history.forEach(o => {
    const div = document.createElement("div");
    div.innerText = `Order ₹${o.total} on ${o.date}`;
    container.appendChild(div);
});
