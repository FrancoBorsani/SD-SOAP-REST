import Vue from 'vue'
import VueRouter from 'vue-router'
import Envios from '@/views/Envios.vue'
import Login from '@/views/Login.vue'
import DetailEnvio from '@/views/DetailEnvio.vue'

const Home = () => import('@/views/Home.vue');
const Products = () => import('@/views/Products.vue');
const Contact = () => import('@/views/Contact.vue');
const Info = () => import('@/views/Info.vue');

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'Home',
    component: Home
  }, {
    path: '/products',
    name: 'Products',
    component: Products
  },
  {
    path: '/contact',
    name: 'Contact',
    component: Contact
  },
  {
    path: '/info',
    name: 'Info',
    component: Info
  },
  {
    path: '/envios',
    name: 'Envios',
    component: Envios
  },
  {
    path: '/envios/:id',
    name: 'envio-detail',
    component: DetailEnvio
  },
  {
    path: '/login',
    name: 'Login',
    component: Login
  }
]

const router = new VueRouter({
  routes
})

export default router
