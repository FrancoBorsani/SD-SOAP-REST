<template>
  <div id="app">
      <Header v-if="$route.path != '/login'" />
      <router-view />
      <!--<Newsletter v-if="$route.path != '/contact' && $route.path != '/envios'"  />
      <Footer v-if="$route.path != '/envios'" /> -->
  </div>
</template>

<script>
import Header from '@/components/Header.vue'

export default{
  name: 'app',
  components: {
    Header
  }
}
</script>

<style>
@import url('https://fonts.googleapis.com/css?family=Exo+2');
#app {
  background-color: #F8F8F8 !important;
  font-family: 'Exo 2', sans-serif !important;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  color: #2c3e50;
}
</style>
