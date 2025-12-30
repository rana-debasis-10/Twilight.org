document.getElementById("alreadylistedinput-right")
.addEventListener("keypress", e => {
    if (e.key === "Enter") {
        location.href = "create-restaurant.html";
    }
});
