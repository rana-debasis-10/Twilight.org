// Footer component for Twilight

export const footer = () => {
    return `
    <footer class="tw-footer">
        <div class="footer-top">
            <div class="footer-brand">
                <img
                    src="../images/twilight-logo.png"
                    alt="Twilight Logo"
                    class="footer-logo"
                />
                <p>
                    Twilight is a food discovery and delivery platform built to
                    connect people with great restaurants.
                </p>
            </div>

            <div class="footer-links">
                <div>
                    <h4>Company</h4>
                    <a href="../pages/about.html">About</a>
                    <a href="../pages/terms.html">Terms</a>
                    <a href="../pages/privacy.html">Privacy</a>
                </div>

                <div>
                    <h4>Explore</h4>
                    <a href="../pages/food.html">Food</a>
                    <a href="../pages/dinning.html">Dining</a>
                    <a href="../pages/nightlife.html">Nightlife</a>
                </div>

                <div>
                    <h4>For Restaurants</h4>
                    <a href="../pages/add-restaurant.html">Add Restaurant</a>
                </div>
            </div>
        </div>

        <div class="footer-bottom">
            <p>© ${new Date().getFullYear()} Twilight. All rights reserved.</p>
        </div>
    </footer>
    `;
};
