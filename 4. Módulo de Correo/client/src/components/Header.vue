<template>
    <nav class="navbar navbar-light">
      <div class="dropdown d-xl-none d-lg-none mr-auto">
        <img
          src="https://png.icons8.com/windows/32/000000/menu.png"
          data-toggle="dropdown"
          data-target="#navd"
          aria-haspopup="true"
          aria-expanded="false"
        />
        <div class="dropdown-menu hb" aria-labelledby="navd">
          <router-link class="dropdown-item" to="/">Home</router-link>
          <router-link class="dropdown-item" to="/envios">Envios</router-link>
          <router-link class="dropdown-item" to="/contact"
            >Contact us</router-link
          >
        </div>
      </div>
      <!--Logo-->
      <router-link class="navbar-brand py-0 pl-5" to="/">
        <img src="@/assets/fi-logo.svg" width="50" height="50" />
      </router-link>
      <!--Header navigation-->
      <span class="navbar-item bc d-none d-xl-block d-lg-block py-0">
        <router-link class="pl-5" to="/">Home</router-link>
        <router-link class="px-5" to="/envios">Envios</router-link>
        <router-link to="/contact">Contact us</router-link>
      </span>

      <div class="nav-item dropdown ml-auto pr-5">

        <router-link
          class="btn-login"
          to="/login"
          style="text-decoration: none"
          v-if="!currentUser.username"
        >
          Login
        </router-link> 

        <div v-else>

          <span class="nav-link dropdown-toggle" id="navbarDropdown" role="button" data-toggle="dropdown"
              aria-haspopup="true" aria-expanded="false">
              <img src="https://www.findcollab.com/img/user-folder/5da9aea95eb62user.png" width="30" />
          </span>

          <div class="dropdown-menu ml-auto user" aria-labelledby="navbarDropdown">
              <a class="dropdown-item" href="#" data-toggle="modal" data-target="#exampleModal">
                <span>Profile</span>
              </a>
              <div class="dropdown-divider"></div>
              <a class="dropdown-item" href="#" @click="logoutUser">
                  Logout
              </a>
          </div>
        </div>

        <!-- Modal -->
        <div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
          <div class="modal-dialog" role="document">
            <div class="modal-content">
              <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">{{ currentUser.username }}</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                  <span aria-hidden="true">&times;</span>
                </button>
              </div>
              <div class="modal-body">
                ...
              </div>
              <div class="modal-footer">
                <button type="button" class="btn btn-primary" data-dismiss="modal">Close</button>
              </div>
            </div>
          </div>
        </div>

      </div>
    </nav>
</template>

<script>

import { mapState } from "vuex";

export default {
  name: "Header",
  computed: {
  ...mapState(["currentUser"]),
  },
  methods: {
    logoutUser() {
      this.$store.dispatch('logoutUser')
      this.$router.push({ name: 'Login'})
    }
  }
};
</script>

<style>

.navbar {
  border-bottom: 1px solid #dcdcdc;
  background-color: #f8f8f8;
}

.navbar-item.bc a {
  font-size: 17px;
  text-decoration: none;
  color: black;
}

.navbar-item.bc a:hover,
.navbar-item.bc a:active {
  color: #ffd700;
}

.btn-login {
  cursor: pointer;
  font-size: 17px;
  color: black;
}

.btn-login:hover,
.btn-login:active {
  color: #ffd700;
}

.user {
    position: absolute;
    left: inherit;
    right: 0;
}
</style>
