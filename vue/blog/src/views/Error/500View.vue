<template>
  <section class="page_404">
    <div class="container">
      <div id="lottie_box" class="lottie-container"></div>
      <div class="contant_box_404">
        <h3 class="h2">服务器内部异常</h3>
        <p>👻博主会尽快处理👻</p>
        <router-link to="/" class="link_404">返回主页</router-link>
      </div>
    </div>
  </section>
</template>
<script setup>
</script>

<style scoped>
.lottie-container {
  position: sticky;
  width: 40%;
  left: 30%;
  right: 30%;
  height: 400px;
}
.page_404 {
  background: #fff;
  height: 100%;
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
