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
axios.defaults.baseURL = 'http://localhost:8081/api/'
Vue.prototype.$http = axios

//token拦截器
axios.interceptors.request.use(function (config) {
  // 获取token
  const token = store.state.token

  // 如果token存在，则在请求头中添加Authorization字段
  if (token) {
    config.headers.Authorization = `Bearer ${token}`;
  }

  return config;
}, function (error) {
  // 处理请求错误
  return Promise.reject(error);
});

new Vue({
  render: h => h(App),
  store:store,
  router:router
}).$mount('#app')
