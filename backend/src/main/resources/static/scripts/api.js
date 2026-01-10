// Centralized API configuration for Twilight

const BASE_URL = "https://easy-ruby-colt-boot.cyclic.app";

// Generic GET request
export const apiGet = async (endpoint) => {
    try {
        const res = await fetch(`${BASE_URL}${endpoint}`);
        if (!res.ok) throw new Error("GET request failed");
        return await res.json();
    } catch (err) {
        console.error(err);
        return null;
    }
};

// Generic POST request
export const apiPost = async (endpoint, body) => {
    try {
        const res = await fetch(`${BASE_URL}${endpoint}`, {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
            },
            body: JSON.stringify(body),
        });
        if (!res.ok) throw new Error("POST request failed");
        return await res.json();
    } catch (err) {
        console.error(err);
        return null;
    }
};

// Generic PUT request (future use)
export const apiPut = async (endpoint, body) => {
    try {
        const res = await fetch(`${BASE_URL}${endpoint}`, {
            method: "PUT",
            headers: {
                "Content-Type": "application/json",
            },
            body: JSON.stringify(body),
        });
        if (!res.ok) throw new Error("PUT request failed");
        return await res.json();
    } catch (err) {
        console.error(err);
        return null;
    }
};

// Generic DELETE request (future use)
export const apiDelete = async (endpoint) => {
    try {
        const res = await fetch(`${BASE_URL}${endpoint}`, {
            method: "DELETE",
        });
        if (!res.ok) throw new Error("DELETE request failed");
        return await res.json();
    } catch (err) {
        console.error(err);
        return null;
    }
};
