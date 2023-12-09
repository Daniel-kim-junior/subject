<template>
  <div class="MainContainer">
    <FixedContainer />
    <CustomContainer />
  </div>
</template>

<script>
import FixedContainer from "./FixedExt.vue";
import CustomContainer from "./CustomExt.vue";
import ApiClient from "../api/ApiClient.js";
export default {
  name: "MainComponent",
  components: {
    FixedContainer,
    CustomContainer,
  },
  data() {
    return {
      baseURL: "http://localhost:8080",
      getCustomExtURL: "/api-v1/excl/ext-custom-list",
      getFixedExtURL: "/api-v1/excl/ext-Fixed-list",
    };
  },
  mounted() {
    const api = new ApiClient(this.baseURL);
    const data = api.fetchData("GET", this.getCustomExtURL);
    data.then((res) => console.log(res.data));
  },
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
</style>
