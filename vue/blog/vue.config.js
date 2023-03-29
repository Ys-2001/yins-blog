const { defineConfig } = require('@vue/cli-service')
module.exports = defineConfig({
  transpileDependencies: ["vuetify"],
  devServer: {
    proxy: {
      "/api": {
        target: "http://127.0.0.1:8088",
        changeOrigin: true,
        pathRewrite: {
          "^/api": ""
        }
      }
    },
  },
  productionSourceMap: false,
  css: {
    extract: true,
    sourceMap: false
  }
});
