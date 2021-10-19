<template>
  <div class="row justify-content-center m-3 mt-5">
    <login-form :submitForm="loginUser" />
  </div>
</template>

<script>
import LoginForm from "@/components/LoginForm";

export default {
  components: {
    LoginForm,
  },
  methods: {
    async loginUser(loginInfo) {
      let user = await this.$store.dispatch("loginUser", loginInfo);

      if (user.error) {
        this.$toast.error(user.error)
      } else {
        this.$toast.success('Logueado satisfactoriamente.')
        this.$router.push({ name: 'Envios'})
      }

    },
  },
};
</script>