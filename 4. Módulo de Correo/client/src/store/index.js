import Vue from 'vue';
import Vuex from 'vuex';
import axios from '../config/axios';

Vue.use(Vuex);

export default new Vuex.Store({
  state: {
    loading: false,
    envios: [],
    currentUser: {}
  },
  mutations: {
    SET_ENVIOS(state, envios) {
      state.envios = envios;
    },
    SET_LOADING(state, loading) {
      state.loading = loading;
    },
    SET_CURRENT_USER(state, user) {
      state.currentUser = user;
      window.localStorage.currentUser = JSON.stringify(user);
    },
    LOGOUT_USER(state) {
      state.currentUser = {};
      window.localStorage.currentUser = JSON.stringify({});
    },
  },
  actions: {
    async getAllEnvios({ commit }) {
      commit('SET_LOADING', true);

      let response = await axios().get('/envios');

      commit('SET_ENVIOS', response.data);
      commit('SET_LOADING', false);
    },
    async updateEstadoDeEnvio({ commit }, { estado, idEnvio }) {

      commit('SET_LOADING', true);

      let response  = await axios().patch(`/envios/${idEnvio}?estado=${estado}`);

      this._vm.$toast.success(response.data.apiResponse.message)
      commit('SET_LOADING', false);

    },
    async loginUser({ commit }, loginInfo) {

      try {
        let response = await axios().post('/auth/signin', loginInfo);

        commit('SET_CURRENT_USER', response.data);

        return response

      } catch {
        return { error : 'Usuario o contraseña incorrecta.' }
      }

    },
    loadCurrentUser({ commit }) {
      let user = JSON.parse(window.localStorage.currentUser);
      commit('SET_CURRENT_USER', user);
    },
    logoutUser({ commit }) {
      commit('LOGOUT_USER');
      this._vm.$toast.success('Sesión cerrada satisfactoriamente.')
    }
  }
})
