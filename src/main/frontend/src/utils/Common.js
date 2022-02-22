export const getToken = () => {
    return localStorage.getItem("token") || null;
}

export const setToken = (token) => {
    localStorage.setItem("token", token);
}