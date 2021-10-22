import Vue from 'vue'
import App from './App.vue'
import router from './router/index'
import store from './store/index';

import "bootstrap/dist/css/bootstrap.min.css";
import "datatables.net-bs4";
import "datatables.net-bs4/css/dataTables.bootstrap4.min.css";

import Toast,{ POSITION } from "vue-toastification";
import "vue-toastification/dist/index.css";

const options = {
  position: POSITION.BOTTOM_CENTER,
  timeout: 1500
};

Vue.use(Toast, options);

Vue.config.productionTip = false
Vue.config.devtools = true

new Vue({
  router, 
  store,
  render: h => h(App)
}).$mount('#app')
