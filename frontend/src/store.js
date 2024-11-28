import { createStore } from 'vuex';

export default createStore({
    state: {
        token: localStorage.getItem('token') || null,
        role: localStorage.getItem('role') || null,
    },
    mutations: {
        setToken(state, token) {
            state.token = token;
            localStorage.setItem('token', token);
        },
        setRole(state, role) {
            state.role = role;
            localStorage.setItem('role', role);
        },
        clearUserData(state) {
            state.token = null;
            state.role = null;
            localStorage.removeItem('token');
            localStorage.removeItem('role');
        },
    },
    getters: {
        isAuthenticated: (state) => Boolean(state.token),
        userRole: (state) => state.role,
    },
});