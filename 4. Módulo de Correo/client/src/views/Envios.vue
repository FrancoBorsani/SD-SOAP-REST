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
          <span class="font-weight-bold">Envios</span>
        </div>
        <div class="card-body">
          <TableEnvios :envios="envios" />
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import TableEnvios from "../components/TableEnvios.vue";
import { mapState } from "vuex";
import $ from 'jquery'; 

export default {
  components: {
    TableEnvios
  },
  computed: {
    ...mapState(["envios"]),
    ...mapState(["loading"]),
  },
  mounted() {
    this.$store.dispatch("getAllEnvios");
  },
  updated() {
    this.showTable();
  },
  methods: {
    showTable() {
      this.$nextTick(() => {
        $("#datatable").DataTable({
          language: {
            processing: "Cargando...",
            search: "Buscar:",
            lengthMenu: "Mostrar  _MENU_ elementos",
            info: "Mostrando _START_ a _END_ de _TOTAL_ elementos",
            infoEmpty: "Mostrando 0 de 0 elementos",
            infoFiltered: "(filtrado de _MAX_ elementos en total)",
            infoPostFix: "",
            zeroRecords: "No hay solicitudes coincidentes con la busqueda",
            paginate: {
                first: "<<",
                previous: "Anterior",
                next: "Siguiente",
                last: ">>"
            },
          },
        });
      });
    },
  }
};
</script>