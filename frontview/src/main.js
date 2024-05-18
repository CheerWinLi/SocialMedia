import Vue from 'vue'
import App from './App.vue'
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
import axios from 'axios'
import router from './router'
import Vuex from 'vuex'
import store from './store'


Vue.use(ElementUI);
Vue.use(Vuex);
axios.defaults.baseURL = 'http://localhost:8080/api/'
Vue.prototype.$http = axios


new Vue({
  render: h => h(App),
  store:store,
  router:router
}).$mount('#app')
