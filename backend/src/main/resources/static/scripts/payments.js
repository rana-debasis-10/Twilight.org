import { initNavbar } from "./navbar.js";
import { initFooter } from "./footer.js";
import { showAlert } from "./modal.js";

initNavbar();
initFooter();

document.getElementById("payNowBtn").addEventListener("click", () => {
    showAlert("Processing payment...", "success");
    setTimeout(() => {
        location.href = "order-success.html";
    }, 1500);
});
