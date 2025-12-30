// Authentication utilities for Twilight

import { CONFIG } from "./config.js";
import { autoHideAlert } from "./auto.js";

// Sign Up
export const signUpUser = async (userData, alertEl) => {
    try {
        const res = await fetch(`${CONFIG.API_BASE_URL}${CONFIG.ENDPOINTS.USERS}`, {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
            },
            body: JSON.stringify(userData),
        });

        if (!res.ok) throw new Error("Signup failed");

        if (alertEl) {
            alertEl.style.color = "white";
            alertEl.style.backgroundColor = "green";
            alertEl.style.border = "1px solid green";
            alertEl.innerHTML = "Successfully Registered";
            autoHideAlert(alertEl, CONFIG.ALERT_TIMEOUT);
        }

        return true;
    } catch (err) {
        console.error(err);
        if (alertEl) {
            alertEl.style.color = "white";
            alertEl.style.backgroundColor = "rgb(224,53,70)";
            alertEl.style.border = "1px solid rgb(224,53,70)";
            alertEl.innerHTML = "Registration Failed";
            autoHideAlert(alertEl, CONFIG.ALERT_TIMEOUT);
        }
        return false;
    }
};

// Login
export const loginUser = async (email, password, alertEl) => {
    try {
        const res = await fetch(`${CONFIG.API_BASE_URL}${CONFIG.ENDPOINTS.USERS}`);
        const users = await res.json();

        const user = users.find(
            (u) => u.email === email && u.password === password
        );

        if (!user) throw new Error("Invalid credentials");

        localStorage.setItem("successUser", JSON.stringify(user));

        if (alertEl) {
            alertEl.style.color = "white";
            alertEl.style.backgroundColor = "green";
            alertEl.style.border = "1px solid green";
            alertEl.innerHTML = `Welcome ${user.name}`;
            autoHideAlert(alertEl, CONFIG.ALERT_TIMEOUT);
        }

        return user;
    } catch (err) {
        console.error(err);
        if (alertEl) {
            alertEl.style.color = "white";
            alertEl.style.backgroundColor = "rgb(224,53,70)";
            alertEl.style.border = "1px solid rgb(224,53,70)";
            alertEl.innerHTML = "Wrong Email or Password";
            autoHideAlert(alertEl, CONFIG.ALERT_TIMEOUT);
        }
        return null;
    }
};

// Logout
export const logoutUser = () => {
    localStorage.removeItem("successUser");
    window.location.href = "../index.html";
};

// Get Logged-in User
export const getCurrentUser = () => {
    return JSON.parse(localStorage.getItem("successUser"));
};
