// Common utility helpers for Twilight

import { CONFIG } from "./config.js";

export function showAlert(message, type = "success") {
    const alertBox = document.getElementById("alert");
    if (!alertBox) return;

    let bg = "green";
    if (type === "error") bg = "rgb(224, 53, 70)";
    if (type === "info") bg = "blue";

    alertBox.style.display = "block";
    alertBox.style.color = "white";
    alertBox.style.backgroundColor = bg;
    alertBox.style.border = `1px solid ${bg}`;
    alertBox.innerHTML = message;

    setTimeout(() => {
        alertBox.style.display = "none";
    }, CONFIG.ALERT_TIMEOUT);
}

export function debounce(fn, delay = 500) {
    let timer;
    return (...args) => {
        clearTimeout(timer);
        timer = setTimeout(() => fn(...args), delay);
    };
}

export function getFromStorage(key, fallback = null) {
    try {
        return JSON.parse(localStorage.getItem(key)) ?? fallback;
    } catch {
        return fallback;
    }
}

export function setToStorage(key, value) {
    localStorage.setItem(key, JSON.stringify(value));
}

export function removeFromStorage(key) {
    localStorage.removeItem(key);
}
