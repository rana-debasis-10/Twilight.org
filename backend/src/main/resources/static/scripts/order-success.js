import { initNavbar } from "./navbar.js";
import { initFooter } from "./footer.js";

initNavbar();
initFooter();

localStorage.removeItem("cartProducts");
localStorage.removeItem("deliveryAddress");
