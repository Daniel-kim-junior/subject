<template>
  <div class="MainContainer">
    <FixedContainer :items="fixedList" @patch-fixed-event="patchFixedData" />
    <CustomContainer
      :items="customList"
      @create-custom-event="postCustomData"
      @delete-custom-event="deleteCustomData"
    />
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
    const baseURL = "http://localhost:8080";
    return {
      getCustomExtURL: "/api-v1/excl/ext-custom-list",
      getFixedExtURL: "/api-v1/excl/ext-fixed-list",
      patchFixedExtURL: "/api-v1/ext-fixed",
      deleteCustomExtURL: "/api-v1/ext-custom",
      postCustomExtURL: "/api-v1/ext-custom",
      api_v1: new ApiClient(baseURL),
      fixedList: [],
      customList: [],
    };
  },
  mounted() {
    this.getFixedList();
    this.getCustomList();
  },
  methods: {
    async getFixedList() {
      const { data } = await this.api_v1.fetchData("GET", this.getFixedExtURL);
      this.fixedList = data.data;
    },
    async patchFixedData(data) {
      await this.api_v1.fetchData("PATCH", this.patchFixedExtURL, data);
    },
    async getCustomList() {
      const { data } = await this.api_v1.fetchData("GET", this.getCustomExtURL);
      console.log(data.data);
      this.customList = data.data;
    },
    async postCustomData(data) {
      await this.api_v1.fetchData("POST", this.postCustomExtURL, data);
    },
    async deleteCustomData(data) {
      await this.api_v1.fetchData("DELETE", this.deleteCustomExtURL, data);
    },
  },
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
</style>
