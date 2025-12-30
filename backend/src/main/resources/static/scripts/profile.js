import { initNavbar } from "./navbar.js";
import { initFooter } from "./footer.js";
import { showAlert } from "./modal.js";

initNavbar();
initFooter();

const user = JSON.parse(localStorage.getItem("successUser"));
if (!user) location.href = "index.html";

document.getElementById("profileName").value = user.name;
document.getElementById("profileEmail").value = user.email;

document.getElementById("profileForm").addEventListener("submit", e => {
    e.preventDefault();
    showAlert("Profile updated", "success");
});
