// Navbar component for Twilight

import { getCurrentUser, logoutUser } from "./auth.js";

export const navbar = () => {
    return `
    <nav id="navbar">
        <div id="navbar-left">
            <img
                src="../images/twilight-logo.png"
                alt="Twilight Logo"
                id="navbarLogo"
            />
        </div>

        <div id="navbar-center">
            <div id="locationBox">
                <i class="fa-solid fa-location-dot"></i>
                <input
                    type="text"
                    id="searchLocation"
                    placeholder="Select location"
                />
            </div>
            <div id="searchBox">
                <i class="fa-solid fa-magnifying-glass"></i>
                <input
                    type="text"
                    id="search"
                    placeholder="Search for food or restaurant"
                />
            </div>
        </div>

        <div id="navbar-right">
            <div id="userSection">
                <img
                    src="../images/user.png"
                    alt="User"
                    id="userImage"
                />
                <span id="userNameNav">Guest</span>
                <i class="fa-solid fa-angle-down" id="dropUserButton"></i>

                <div id="dropUser">
                    <p id="profileSection">Profile</p>
                    <p id="logOutBttn">Logout</p>
                </div>
            </div>
        </div>
    </nav>
    `;
};

export const initNavbar = () => {
    const user = getCurrentUser();
    const userNameEl = document.getElementById("userNameNav");
    const dropUser = document.getElementById("dropUser");

    if (user && userNameEl) {
        userNameEl.innerText = user.name;
    }

    const dropBtn = document.getElementById("dropUserButton");
    if (dropBtn && dropUser) {
        dropBtn.addEventListener("click", () => {
            dropUser.style.display =
                dropUser.style.display === "grid" ? "none" : "grid";
        });
    }

    const logoutBtn = document.getElementById("logOutBttn");
    if (logoutBtn) {
        logoutBtn.addEventListener("click", logoutUser);
    }

    const profileBtn = document.getElementById("profileSection");
    if (profileBtn) {
        profileBtn.addEventListener("click", () => {
            window.location.href = "../pages/profile.html";
        });
    }

    const logo = document.getElementById("navbarLogo");
    if (logo) {
        logo.addEventListener("click", () => {
            window.location.href = "../index.html";
        });
    }
};
