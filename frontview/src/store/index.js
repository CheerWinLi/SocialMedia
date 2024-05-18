import Vuex from "vuex";
import Vue from "vue";
Vue.use(Vuex)
const store = new Vuex.Store({
    state: {
        token: null,
    },
    mutations: {
        setToken(state,token) {
            state.token = token;
        },
    }
})

export default store;
