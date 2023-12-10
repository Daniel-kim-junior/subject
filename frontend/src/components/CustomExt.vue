<template>
  <main class="custom-container">
    <div>
      <form @submit.prevent="postCustomData">
        <label>커스텀 확장자</label>
        <input
          type="text"
          id="custom-ext-name"
          v-model="data.extName"
          placeholder="확장자 입력"
        />
        <button type="submit">추가</button>
      </form>
    </div>
    <div class="custom-list">
      <div class="text-box">
        <ul class="list">
          <li v-for="item in items" :key="item.extName">
            {{ item.extName }}
            <button :key="item.extName" @click="deleteCustomData(item)">
              X
            </button>
          </li>
        </ul>
      </div>
    </div>
  </main>
</template>
<script>
export default {
  name: "CustomContainer",
  components: {},
  data() {
    return {
      data: {
        extName: "",
      },
    };
  },
  props: {
    items: {
      type: Array,
      required: true,
    },
  },
  beforeCreate() {},
  created() {},
  beforeMount() {},
  mounted() {},
  beforeUpdate() {},
  updated() {},
  beforeUnmount() {},
  unmounted() {},
  methods: {
    postCustomData() {
      this.$emit("create-custom-event", this.data);
    },
    deleteCustomData(item) {
      this.$emit("delete-custom-event", item);
    },
  },
};
</script>