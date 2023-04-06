<template>
  <section class="page_404">
    <div class="container">
      <div id="lottie_box" class="lottie-container"></div>
      <div class="contant_box_404">
        <h1 class="h2">啊哦，出错啦</h1>
        <h1>你寻找的页面走丢啦!</h1>
        <router-link to="/" class="link_404">返回主页</router-link>
      </div>
    </div>
  </section>
</template>
<script setup>
</script>

<style scoped>
.lottie-container {
  width: 40%;
  left: 30%;
  right: 30%;
  height: 400px;
}
.page_404 {
  color: indianred;
  height: 100%;
  background-image: url("@/assets/img/404.png") ;
}

.container {
  padding: 40px 0;
  text-align: center;
}

.page_404 img {
  width: 100%;
}

.link_404 {
  color: #fff;
  background: #1892ff;
  text-decoration: none;
  padding: 8px 20px;
  border-radius: 5px;
  margin: 20px 0;
  display: inline-block;
}

.contant_box_404 {
  margin-bottom: 9%;
}
</style>
