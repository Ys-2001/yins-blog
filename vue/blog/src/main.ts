import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import { createPinia } from 'pinia'
import piniaPluginPersist from "pinia-plugin-persist";
// Vuetify
import vuetify from './plugins/vuetify'
import "./assets/css/index.css";
import "./assets/css/iconfont.css";
import "./assets/css/markdown.css";
import "./assets/css/vue-social-share/client.css";
import axios from "axios";
import VueAxios from "vue-axios";


const pinia = createPinia();
pinia.use(piniaPluginPersist);

axios.interceptors.request.use(function (config) {
    // 在发送请求之前做些什么
    // 判断是否存在token,如果存在将每个页面header添加token
    if (sessionStorage.getItem("token")) {
        const tokenObj = JSON.parse(sessionStorage.getItem("token") as string);
        const token = tokenObj.token;
        config.headers.common['Authorization'] = `Bearer ${token}`;
    }
    return config
})
//   , function (error) {
//     router.push('/login')
//     return Promise.reject(error)
//   })


createApp(App)
    .use(router)
    .use(vuetify)
    .use(pinia)
    .use(VueAxios, axios)
    .mount('#app')
