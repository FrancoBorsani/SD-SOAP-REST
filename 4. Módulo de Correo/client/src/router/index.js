import Vue from 'vue'
import VueRouter from 'vue-router'
import Envios from '@/views/Envios.vue'
import Login from '@/views/Login.vue'
import DetailEnvio from '@/views/DetailEnvio.vue'

Vue.use(VueRouter)

const routes = [
  { 
    path: '/', 
    redirect: { name: 'Envios' }
  },
  {
    path: '/envios',
    name: 'Envios',
    component: Envios,
    beforeEnter: (to, from, next) => {
      let user = localStorage.getItem('currentUser');

      if (user && JSON.parse(window.localStorage.currentUser).username) {
        next()
      } else {
        next("/login");
      }
    },
  },
  {
    path: '/envios/:id',
    name: 'envio-detail',
    component: DetailEnvio,
    beforeEnter: (to, from, next) => {
      let user = localStorage.getItem('currentUser');

      if (user && JSON.parse(window.localStorage.currentUser).username) {
        next()
      } else {
        next("/login");
      }
    },
  },
  {
    path: '/login',
    name: 'Login',
    component: Login
  },
]

const router = new VueRouter({
  routes
})

export default router
