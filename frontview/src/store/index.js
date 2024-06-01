import Vuex from "vuex";
import Vue from "vue";

Vue.use(Vuex)
const store = new Vuex.Store({
    state: {
        token: null,
        userId: null,
    },
    mutations: {
        setToken(state, token) {
            state.token = token;
        },
        setUserId(state, userId) {
            state.userId = userId;
        }
    }
})

export default store;
