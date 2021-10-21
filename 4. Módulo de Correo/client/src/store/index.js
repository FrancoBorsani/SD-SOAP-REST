import Vue from 'vue';
import Vuex from 'vuex';
import axios from '../config/axios';
import authHeader from '../config/authHeader';

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
      localStorage.removeItem('currentUser')
    },
  },
  actions: {
    async getAllEnvios({ commit }) {
      commit('SET_LOADING', true);

      let response = await axios().get('/envios', {
        headers: authHeader()
      });

      commit('SET_ENVIOS', response.data);
      commit('SET_LOADING', false);
    },
    async updateEstadoDeEnvio({ commit }, { estado, idEnvio }) {

      commit('SET_LOADING', true);

      let response = await axios().put(`/envios/${idEnvio}?estado=${estado}`, {
        headers: authHeader()
      });

      this._vm.$toast.success(response.data.apiResponse.message)
      commit('SET_LOADING', false);

    },
    async loginUser({ commit }, loginInfo) {

      try {
        let response = await axios().post('/auth/signin', loginInfo);

        commit('SET_CURRENT_USER', response.data);

        return response.data

      } catch {
        return { error: 'Usuario o contraseña incorrecta.' }
      }

    },
    loadCurrentUser({ commit }) {

      let user = localStorage.getItem('currentUser');

      if (user) commit('SET_CURRENT_USER', JSON.parse(window.localStorage.currentUser));

    },
    logoutUser({ commit }) {
      commit('LOGOUT_USER');
      this._vm.$toast.success('Sesión cerrada satisfactoriamente.')
    }
  }
})
