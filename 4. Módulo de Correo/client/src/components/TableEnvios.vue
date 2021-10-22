<template>
  <div class="table-responsive">
    <table class="table" id="datatable">
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
            <router-link class="text-dark" :to="{ name: 'envio-detail', params: { id: envio.codigoDeSeguimiento }}">
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
</template>

<script>
export default {
    props: ['envios']
};
</script>
