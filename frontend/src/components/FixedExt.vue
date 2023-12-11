<template>
  <article id="fixed-container">
    <div>고정 확장자</div>
    <ul id="fixed-list">
      <li v-for="item in items" :key="item.extName">
        <input
          type="checkbox"
          :key="item.extName"
          v-model="item.isActivate"
          @click="patchFixedStatus(item)"
        />
        {{ item.extName }}
      </li>
    </ul>
  </article>
</template>
<script>
export default {
  name: "FixedContainer",
  components: {},
  props: {
    items: {
      type: Array,
      required: true,
    },
  },
  methods: {
    patchFixedStatus(item) {
      this.$emit("patchFixedEvent", {
        extName: item.extName,
        isActivate: !item.isActivate,
      });
    },
  },
};
</script>
<style scoped>
#fixed-container {
  position: absolute;
  display: flex;
  align-items: center;
  display: flex;
  justify-content: space-between;
  width: 500px;
}
#fixed-list {
  display: flex;
  list-style: none;
}
#fixed-list > li {
  margin-right: 10px;
}
</style>
