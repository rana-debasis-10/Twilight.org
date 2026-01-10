// Generic modal & alert handler for Twilight

export function showModal(modalId) {
    const modal = document.getElementById(modalId);
    if (!modal) return;

    modal.style.display = "block";
    document.body.style.overflow = "hidden";
}

export function closeModal(modalId) {
    const modal = document.getElementById(modalId);
    if (!modal) return;

    modal.style.display = "none";
    document.body.style.overflow = "auto";
}

export function showAlert(message, type = "success", duration = 1500) {
    const alertBox = document.getElementById("alert");
    if (!alertBox) return;

    alertBox.innerText = message;
    alertBox.style.display = "block";

    if (type === "success") {
        alertBox.style.backgroundColor = "green";
        alertBox.style.color = "white";
        alertBox.style.border = "1px solid green";
    } else if (type === "error") {
        alertBox.style.backgroundColor = "rgb(224, 53, 70)";
        alertBox.style.color = "white";
        alertBox.style.border = "1px solid rgb(224, 53, 70)";
    } else if (type === "info") {
        alertBox.style.backgroundColor = "blue";
        alertBox.style.color = "white";
        alertBox.style.border = "1px solid blue";
    }

    setTimeout(() => {
        alertBox.style.display = "none";
    }, duration);
}

// Close modal when clicking outside content
export function bindOutsideClick(modalId, contentClass) {
    const modal = document.getElementById(modalId);
    if (!modal) return;

    modal.addEventListener("click", (e) => {
        if (!e.target.closest(`.${contentClass}`)) {
            closeModal(modalId);
        }
    });
}
