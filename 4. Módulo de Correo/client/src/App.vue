<template>
  <div id="app">
      <Header v-if="$route.path != '/login'" />
      <router-view />
  </div>
</template>

<script>
import Header from '@/components/Header.vue'

export default{
  name: 'app',
  components: {
    Header
  },
  mounted() {
    this.$store.dispatch('loadCurrentUser')
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
