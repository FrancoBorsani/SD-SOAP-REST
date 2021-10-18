<template>
  <div class="row justify-content-center mt-4">
    <div
      v-if="loading"
      class="spinner-border text-success text-center"
      role="status"
    >
      <span class="visually-hidden"></span>
    </div>

    <div v-else class="col-md-10">
      <svg xmlns="http://www.w3.org/2000/svg" style="display: none">
        <symbol id="check-circle-fill" fill="currentColor" viewBox="0 0 16 16">
          <path
            d="M16 8A8 8 0 1 1 0 8a8 8 0 0 1 16 0zm-3.97-3.03a.75.75 0 0 0-1.08.022L7.477 9.417 5.384 7.323a.75.75 0 0 0-1.06 1.06L6.97 11.03a.75.75 0 0 0 1.079-.02l3.992-4.99a.75.75 0 0 0-.01-1.05z"
          />
        </symbol>
      </svg>

      <div class="card">
        <div class="card-header">
          <span>Envios</span>
        </div>
        <div class="card-body">
          <div class="table-responsive">
            <table class="table">
              <thead>
                <tr>
                  <th scope="col">#</th>
                  <th scope="col">Descripción</th>
                  <th scope="col">Fecha</th>
                  <th scope="col">Destinatario</th>
                  <th scope="col">Estado</th>
                </tr>
              </thead>
              <tbody>
                <tr v-for="envio in envios" :key="envio.id">
                  <th scope="row">
                    <router-link class="text-dark" :to="{ name: 'envio-detail', params : { id : envio.codigoDeSeguimiento} }">
                      {{ envio.codigoDeSeguimiento }}
                    </router-link>
                  </th>
                  <td>{{ envio.descripcion }}</td>
                  <td>{{ envio.createdAt }}</td>
                  <td>{{ envio.dnidestinatario }}</td>
                  <td>{{ envio.estado }}</td>
                </tr>
              </tbody>
            </table>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { mapState } from "vuex";
export default {
  computed: {
    ...mapState(["envios"]),
    ...mapState(["loading"]),
  },
  mounted() {
    this.$store.dispatch("getAllEnvios");
  },
  methods: {
    deleteCountry(country) {
      console.log(country);
    },
  },
};
</script>