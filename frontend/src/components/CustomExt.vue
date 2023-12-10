<template>
  <article>
    <div id="form-container">
      <label>커스텀 확장자</label>
      <form @submit.prevent="postCustomData">
        <div id="input-btn">
          <div>
            <input
              type="text"
              id="custom-ext-name"
              ref="inputValue"
              v-model="inputRef"
              placeholder="확장자 입력"
              autofocus
            />
          </div>
          <div>
            <button type="submit">+추가</button>
          </div>
        </div>
      </form>
    </div>
    <div id="custom-list">
      <div>{{ items.length + "/" + 200 }}</div>
      <ul class="list">
        <li v-for="item in items" :key="item.extName">
          <div id="cancel-btn">
            <span id="custom-name">
              {{ item.extName }}
            </span>
            <span
              :key="item.extName"
              @click="deleteCustomData(item)"
              :disabled="item.length === 200"
              id="cancel"
            >
              X
            </span>
          </div>
        </li>
      </ul>
    </div>
  </article>
</template>
<script>
import { ref, watch } from "vue";
export default {
  name: "CustomContainer",
  components: {},
  props: {
    items: {
      type: Array,
      required: true,
    },
  },
  setup(props, context) {
    const inputRef = ref(null);
    const inputValue = ref("");

    watch(
      () => props.items,
      () => {
        context.emit("reload-custom-event");
      },
      { deep: true }
    );

    return {
      inputRef,
      inputValue,
    };
  },
  beforeCreate() {},
  created() {},
  beforeMount() {},
  beforeUpdate() {},
  updated() {},
  beforeUnmount() {},
  unmounted() {},
  methods: {
    postCustomData() {
      this.$emit("create-custom-event", { extName: this.inputValue.value });
      this.inputRef = "";
    },
    deleteCustomData(item) {
      this.$emit("delete-custom-event", item);
    },
  },
};
</script>

<style scoped>
article {
  position: absolute;
  top: 50px;
  width: 500px;
  height: 300px;
}
#form-container {
  display: flex;
  align-items: center;
  justify-content: space-between;
}

#input-btn {
  display: flex;
}
#input-btn > div {
  margin-left: 10px;
}
#input-btn > div > * {
  padding: 3px;
}
#custom-list {
  margin-top: 20px;
  overflow-y: auto;
  height: 210px;
  border: 0.3px solid black;
  padding: 10px;
}
#custom-list > ul {
  padding-top: 10px;
  display: flex;
  justify-content: space-between;
  flex-wrap: wrap;
}
#custom-list > ul > li {
  display: inline;
  padding-top: 10px;
  padding-right: 10px;
}
#cancel-btn {
  background-color: #888;
  border: 1px solid black;
  border-radius: 5px;
  padding: 3px;
  color: #f5f5f5;
}
#cancel {
  color: black;
  font-size: 12px;
  cursor: pointer;
}
#custom-name {
  font-size: 14px;
  margin-inline: 2px;
}
</style>