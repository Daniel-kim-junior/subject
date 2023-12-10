<template>
  <MainComponent />
</template>

<script>
import MainComponent from "./components/Main.vue";

export default {
  name: "App",
  components: {
    MainComponent,
  },
};
</script>

<style>
@import "reset.css";
@font-face {
  font-family: "Noto Sans KR";
  font-style: normal;
  font-weight: 400;
  src: local("Noto Sans KR"), local("NotoSansKR"),
    url(https://fonts.gstatic.com/s/notosanskr/v10/Pby6FmXiEBPT4ITbgNA5CgmOsn7uwpvIyhcgUviC.woff2)
      format("woff2");
  unicode-range: U+AC00-D7AF, U+1100-11FF, U+3130-318F, U+A960-A97F, U+D7B0-D7FF;
}
body {
  font-family: "Noto Sans KR", sans-serif;
}
</style>
