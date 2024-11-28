import { createStore } from 'vuex';

export default createStore({
    state: {
        token: null,
        role: null,
    },
    mutations: {
        setToken(state, token) {
            state.token = token;
        },
        setRole(state, role) {
            state.role = role;
        },
        clearUserData(state) {
            state.token = null;
            state.role = null;
        },
    },
    getters: {
        isAuthenticated: (state) => Boolean(state.token),
        userRole: (state) => state.role,
    },
});