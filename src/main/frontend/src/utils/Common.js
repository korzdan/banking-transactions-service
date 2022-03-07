import jwt from 'jwt-decode';

export const getToken = () => {
    return localStorage.getItem("token") || null;
}

export const setToken = (token) => {
    localStorage.setItem("token", token);
}

export const getRole = (token) => {
    return jwt(token).role;
}