import { initNavbar } from "./navbar.js";
import { initFooter } from "./footer.js";
import { showAlert } from "./modal.js";

// Initialize common layout
initNavbar();
initFooter();

// Section navigation
document.getElementById("section_item1")?.addEventListener("click", () => {
    window.location.href = "./pages/food.html";
});
document.getElementById("section_item2")?.addEventListener("click", () => {
    window.location.href = "./pages/dinning.html";
});
document.getElementById("section_item3")?.addEventListener("click", () => {
    window.location.href = "./pages/nightlife.html";
});

// Search food from home
document.getElementById("search1")?.addEventListener("keypress", (e) => {
    if (e.key === "Enter") {
        const value = e.target.value.trim();
        if (!value) {
            showAlert("Please enter a food or restaurant name", "error");
            return;
        }
        localStorage.setItem("foodItem", value);
        window.location.href = "./pages/food.html";
    }
});

// Restore last searched city
const searchedCity = localStorage.getItem("searchedCity");
if (searchedCity) {
    const cityEls = [
        "cityName1",
        "cityName2",
        "cityName3",
        "cityName4"
    ];
    cityEls.forEach(id => {
        const el = document.getElementById(id);
        if (el) el.innerText = searchedCity;
    });
}
