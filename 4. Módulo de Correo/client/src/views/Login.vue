<template>
  <div class="row justify-content-center m-2 mt-5">
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
      //let user = await this.$store.dispatch("loginUser", loginInfo);
      alert('Login!', loginInfo)
    }
  },
};
</script>