<template>
  <form action="#" class="col-md-6 shadow p-4" method="POST" @submit.prevent="submitForm(userInfo)">
    <h3 class="text-uppercase text-center mb-2">Login</h3>

    <div class="form-group">
      <label>Username</label>
      <input type="text" class="form-control" name="username" v-model="userInfo.username" required />
    </div>

    <div className="form-group">
      <label>Password</label>
      <div class="pass">
        <input type="password" class="form-control" name="password" v-model="userInfo.password" required />
      </div>
    </div>

    <button type="submit" class="btn btn-outline-dark w-100 my-3">
      Login
    </button>
  </form>
</template>

<script>
export default {
  data() {
    return {
      userInfo: {},
    };
  },
  props: ['submitForm']
};
</script>

