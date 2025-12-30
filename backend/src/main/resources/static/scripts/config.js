// Central configuration for Twilight frontend

export const CONFIG = {
    API_BASE_URL: "https://easy-ruby-colt-boot.cyclic.app",

    ENDPOINTS: {
        USERS: "/user",
        POSTS: "/posts",
        RESTAURANTS: "/restaurant",
    },

    STORAGE_KEYS: {
        USER: "successUser",
        CART: "cartProducts",
        FOOD_ITEM: "foodItem",
        CITY: "searchedCity",
        FILTER: "filterApplied",
        FOOD_SHOW: "foodShow",
    },

    ALERT_TIMEOUT: 1500,
};
