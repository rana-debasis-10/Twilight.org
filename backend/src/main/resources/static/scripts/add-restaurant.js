import { CONFIG } from "./config.js";
import { showAlert } from "./modal.js";

document.getElementById("addRestaurantForm").addEventListener("submit", async e => {
    e.preventDefault();

    const data = {
        name: restaurantname.value,
        address: restoaddress.value,
        mobile: restaurantmob.value,
        landlineNumb: restaurnatlandline.value,
        ownerFullname: restaurantownerfullname.value,
        ownerEmail: restaurantowneremail.value,
        ownerMobile: restaurantownermob.value,
        img: restaurantImageUrl.value
    };

    await fetch(CONFIG.API_BASE_URL + CONFIG.ENDPOINTS.RESTAURANTS, {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(data)
    });

    showAlert("Restaurant registered", "success");
    setTimeout(() => location.href = "index.html", 1500);
});
