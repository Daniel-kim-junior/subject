<template>
  <main id="main-container">
    <section>
      <FixedContainer :items="fixedList" @patch-fixed-event="patchFixedData" />
      <CustomContainer
        :items="customList"
        @create-custom-event="postCustomData"
        @delete-custom-event="deleteCustomData"
        @reload-custom-event="getCustomList"
      />
    </section>
  </main>
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
    const baseURL = "http://refresh.directory:8080";
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
    async patchFixedData(fix) {
      const { data } = await this.api_v1.fetchData(
        "PATCH",
        this.patchFixedExtURL,
        fix
      );
      this.invalidHandling(data);
    },
    async getCustomList() {
      const { data } = await this.api_v1.fetchData("GET", this.getCustomExtURL);
      this.customList = data.data;
    },
    async postCustomData(fix) {
      const { data } = await this.api_v1.fetchData(
        "POST",
        this.postCustomExtURL,
        fix
      );
      this.invalidHandling(data);
    },
    async deleteCustomData(fix) {
      const { data } = await this.api_v1.fetchData(
        "DELETE",
        this.deleteCustomExtURL,
        fix
      );
      this.invalidHandling(data);
    },
    invalidHandling({ status, data }) {
      if (status !== "success") {
        alert(data);
      } else {
        alert("성공적으로 작업을 완료했습니다");
      }
    },
  },
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
main {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  min-height: 100vh;
}
section {
  border: 1px solid black;
  padding: 20px;
  height: 300px;
  width: 500px;

  position: relative;
}
</style>
