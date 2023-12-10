<template>
  <header class="FixedContainer">
    <div>고정 확장자</div>
    <ul>
      <li v-for="item in items" :key="item.extName">
        {{ item.extName }}
        <input
          type="checkbox"
          :key="item.extName"
          v-model="item.isActivate"
          @click="patchFixedStatus(item)"
        />
      </li>
    </ul>
  </header>
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
  data() {},
  beforeCreate() {},
  created() {},
  beforeMount() {},
  mounted() {},
  beforeUpdate() {},
  updated() {},
  beforeUnmount() {},
  unmounted() {},
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