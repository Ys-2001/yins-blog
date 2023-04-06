<template>
  {{ typerOption.output }}<span class="typed-cursor">|</span>
</template>

<script setup>
import { onMounted, reactive } from "vue";
import EasyTyper from "easy-typer-js";
onMounted(() => {
  init();
})


const typerOption = reactive({
  output: "",
  isEnd: false,
  speed: 200,
  singleBack: false,
  sleep: 0,
  type: "rollback",
  backSpeed: 0,
  sentencePause: true,
});
// 初始化
const init = () => {
  document.title = "青春似乎不应该颓废";
  // 一言Api进行打字机循环输出效果
  fetch("https://v1.hitokoto.cn?c=i")
    .then((res) => {
      return res.json();
    })
    .then(({ hitokoto,from,from_who }) => {
      let str = hitokoto+"  —《"+from+"》";
      if(from_who !== null)
      str += " ● "+from_who
      initTyped(str);
    });
};
const initTyped = (input) => {
  // eslint-disable-next-line no-unused-vars
   new EasyTyper(typerOption, input);
};
</script>
<style lang="stylus" scoped>
.typed-cursor
  opacity: 1
  animation: blink 0.7s infinite
@keyframes blink
  0%
    opacity: 1
  50%
    opacity: 0
  100%
    opacity: 1
</style>