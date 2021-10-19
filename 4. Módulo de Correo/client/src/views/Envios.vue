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
  }
};
</script>