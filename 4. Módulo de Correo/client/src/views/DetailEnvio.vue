<template>
  <div class="row justify-content-center mt-4">
    <div class="container">
      <div class="card">
        <div class="row d-flex justify-content-between px-3 top">
          <div class="d-flex flex-column">
            <p>
              ORDER
              <span class="text-primary font-weight-bold"
                >#{{ envio.dnidestinatario }}</span
              >
            </p>
            <p>
              ORDER
              <span class="text-primary font-weight-bold"
                >#{{ envio.createdAt }}</span
              >
            </p>
            <p>
              ORDER
              <span class="text-primary font-weight-bold"
                >#{{ envio.descripcion }}</span
              >
            </p>
          </div>
          <div class="d-flex flex-column text-sm-right">
            <p>
              Código
              <span class="font-weight-bold">{{
                envio.codigoDeSeguimiento
              }}</span>
            </p>
            <p>
              USPS
              <span class="font-weight-bold">{{
                envio.codigoDeSeguimiento
              }}</span>
            </p>
          </div>
        </div>
        <!-- Add class 'active' to progress -->
        <div class="row d-flex justify-content-center">
          <div class="col-12">
            <ul id="progressbar" class="text-center">
              <li
                class="step1 active"
                id="step1"
                v-on:click="cambiarEstado('En Prepación')"
              ></li>
              <li
                :class="(envio.estado === 'Despachado' || envio.estado === 'En Camino' || envio.estado === 'Entregado')? 'step2 active' : 'step2'"
                id="step2"
                v-on:click="cambiarEstado('Despachado')"
              ></li>
              <li
                :class="(envio.estado === 'En Camino' || envio.estado === 'Entregado')? 'step3 active' : 'step3'"
                id="step3"
                v-on:click="cambiarEstado('En Camino')"
              ></li>
              <li
                class="step4"
                id="step4"
                :class="(envio.estado === 'Entregado')? 'step4 active' : 'step4'"
                v-on:click="cambiarEstado('Entregado')"
              ></li>
            </ul>
          </div>
        </div>
        <div class="row justify-content-between top">
          <div class="row d-flex icon-content">
            <img class="icon" src="https://i.imgur.com/9nnc9Et.png" />
            <div class="d-flex flex-column">
              <p class="font-weight-bold">Orden<br />En Prepación</p>
            </div>
          </div>
          <div class="row d-flex icon-content">
            <img class="icon" src="https://i.imgur.com/u1AzR7w.png" />
            <div class="d-flex flex-column">
              <p class="font-weight-bold">Orden<br />Despachada</p>
            </div>
          </div>
          <div class="row d-flex icon-content">
            <img class="icon" src="https://i.imgur.com/TkPm63y.png" />
            <div class="d-flex flex-column">
              <p class="font-weight-bold">Orden<br />En Camino</p>
            </div>
          </div>
          <div class="row d-flex icon-content">
            <img class="icon" src="https://i.imgur.com/HdsziHP.png" />
            <div class="d-flex flex-column">
              <p class="font-weight-bold">Orden<br />Entregada</p>
            </div>
          </div>
        </div>
      </div>
    </div>

  </div>
</template>

<script>
import { mapState } from "vuex";
import axios from "../config/axios";

export default {
  data() {
    return {
      envio: {},
      estadoFinal: "",
    };
  },
  computed: {
    ...mapState(["envios"]),
    ...mapState(["loading"]),
  },
  mounted() {
    axios()
      .get(`/envios/codigo/${this.$route.params.id}`)
      .then((response) => (this.envio = response.data))
      .catch((error) => console.log(error));
  },
  methods: {
    cambiarEstado(estado) {
      var step2 = document.getElementById("step2");
      var step3 = document.getElementById("step3");
      var step4 = document.getElementById("step4");

      switch (estado) {
        case "En Prepación":
          this.estadoFinal = 'En Prepación';
          this.updateEstadoDeEnvio(this.envio.id, 'En Prepación')
          step2.classList.remove("active");
          step3.classList.remove("active");
          step4.classList.remove("active");
          break;
        case "Despachado":
          this.estadoFinal = 'Despachado';
          this.updateEstadoDeEnvio(this.envio.id, 'Despachado')
          step2.classList.add("active");
          step3.classList.remove("active");
          step4.classList.remove("active");
          break;
        case "En Camino":
          this.estadoFinal = 'En Camino';
          this.updateEstadoDeEnvio(this.envio.id, 'En Camino')
          step2.classList.add("active");
          step3.classList.add("active");
          step4.classList.remove("active");
          break;

        case "Entregado":
          this.estadoFinal = 'Entregado';
          this.updateEstadoDeEnvio(this.envio.id, 'Entregado')
          step2.classList.add("active");
          step3.classList.add("active");
          step4.classList.add("active");
          break;

        default:
          break;
      }
    },
    async updateEstadoDeEnvio(idEnvio, estado) {
      await this.$store.dispatch("updateEstadoDeEnvio", {
        estado,
        idEnvio,
      });
    },
  },
};
</script>

<style scoped>
body {
  color: #000;
  overflow-x: hidden;
  height: 100%;
  background-color: #8c9eff;
  background-repeat: no-repeat;
}

.card {
  z-index: 0;
  background-color: #eceff1;
  border-radius: 10px;
}

.top {
  padding-top: 40px;
  padding-left: 13% !important;
  padding-right: 13% !important;
}

#progressbar {
  margin-bottom: 30px;
  overflow: hidden;
  color: #455a64;
  padding-left: 0px;
  margin-top: 30px;
}

#progressbar li {
  list-style-type: none;
  font-size: 13px;
  width: 25%;
  float: left;
  position: relative;
  font-weight: 400;
}

#progressbar .step1:before,
#progressbar .step2:before,
#progressbar .step3:before,
#progressbar .step4:before {
  font-family: FontAwesome;
  content: "\f10c";
  color: #fff;
}

#progressbar li:before {
  width: 40px;
  height: 40px;
  line-height: 45px;
  display: block;
  font-size: 20px;
  background: #c5cae9;
  border-radius: 50%;
  margin: auto;
  padding: 0px;
}

#progressbar li:after {
  content: "";
  width: 100%;
  height: 12px;
  background: #c5cae9;
  position: absolute;
  left: 0;
  top: 16px;
  z-index: -1;
}

#progressbar li:last-child:after {
  border-top-right-radius: 10px;
  border-bottom-right-radius: 10px;
  position: absolute;
  left: -50%;
}

#progressbar li:nth-child(2):after,
#progressbar li:nth-child(3):after {
  left: -50%;
}

#progressbar li:first-child:after {
  border-top-left-radius: 10px;
  border-bottom-left-radius: 10px;
  position: absolute;
  left: 50%;
}

#progressbar li:last-child:after {
  border-top-right-radius: 10px;
  border-bottom-right-radius: 10px;
}

#progressbar li:first-child:after {
  border-top-left-radius: 10px;
  border-bottom-left-radius: 10px;
}

#progressbar li.active:before,
#progressbar li.active:after {
  background: #651fff;
}

#progressbar li.active:before {
  font-family: FontAwesome;
  content: "\f00c";
}

.icon {
  width: 60px;
  height: 60px;
  margin-right: 15px;
}

.icon-content {
  padding-bottom: 20px;
}

@media screen and (max-width: 992px) {
  .icon-content {
    width: 50%;
  }
}
</style>