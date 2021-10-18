import Vue from 'vue'
import VueRouter from 'vue-router'
import Envios from '@/views/Envios.vue'
import Login from '@/views/Login.vue'
import DetailEnvio from '@/views/DetailEnvio.vue'

const Home = () => import('@/views/Home.vue');
const Contact = () => import('@/views/Contact.vue');

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'Home',
    component: Home,
    beforeEnter: (to, from, next) => {
      let currentUser = JSON.parse(window.localStorage.currentUser);
      if(currentUser && currentUser.username) {
        next();
      } else {
        next("/login");
      }
    },
  },
  {
    path: '/contact',
    name: 'Contact',
    component: Contact,
    beforeEnter: (to, from, next) => {
      let currentUser = JSON.parse(window.localStorage.currentUser);
      if(currentUser && currentUser.username) {
        next();
      } else {
        next("/login");
      }
    },
  },
  {
    path: '/envios',
    name: 'Envios',
    component: Envios,
    beforeEnter: (to, from, next) => {
      let currentUser = JSON.parse(window.localStorage.currentUser);
      if(currentUser && currentUser.username) {
        next();
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
      let currentUser = JSON.parse(window.localStorage.currentUser);
      if(currentUser && currentUser.username) {
        next();
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
