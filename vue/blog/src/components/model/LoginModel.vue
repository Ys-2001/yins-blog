<template>
  <v-dialog v-model="loginModelFlag" :fullscreen="isMobile()" max-width="460" class="dia">
    <v-card class="login-container" style="border-radius:4px">
      <v-icon class="float-right" @click="loginModelFlag = false">
        mdi-close
      </v-icon>
      <div class="login-wrapper">
        <!-- 用户名 -->
        <v-text-field v-model="state.username" label="邮箱号" placeholder="请输入您的邮箱号" clearable @keyup.enter="login" />
        <!-- 密码 -->
        <v-text-field v-model="state.password" class="mt-7" label="密码" placeholder="请输入您的密码" @keyup.enter="login"
          :append-icon="state.show ? 'mdi-eye' : 'mdi-eye-off'" :type="state.show ? 'text' : 'password'"
          @click:append="state.show = !state.show" />
        <!-- 按钮 -->
        <v-btn class="mt-7" block color="blue" style="color:#fff" @click="login">
          登录
        </v-btn>
        <!-- 注册和找回密码 -->
        <div class="mt-10 login-tip">
          <a @click="openRegisterModel">立即注册</a>
          <a @click="openForgetModel" class="float-right">忘记密码?</a>
        </div>
        <div class="social-login-title">社交账号登录</div>
        <div class="social-login-wrapper">
          <!-- qq登录 -->
          <a  class="iconfont iconqq" style="color:#00AAEE" />
        </div>
      </div>
    </v-card>
  </v-dialog>
</template>

<script setup lang="ts">
import { reactive, toRefs } from 'vue';
import axios from 'axios';
import Message from '../message';
import useStore from '../../store/'


const { user } = useStore();
const { registerModelFlag,forgetModelFlag,loginModelFlag } = toRefs(user);

const state = reactive({
  show: false,
  username: "",
  password: "",
})



const isMobile = () => {
  const clientWidth = document.documentElement.clientWidth;
  if (clientWidth > 960) {
    return false;
  }
  return true;
}
//注册
const openRegisterModel = () => {
  loginModelFlag.value = false;
  registerModelFlag.value = true;
  
}

//找回密码
const openForgetModel = () => {
  loginModelFlag.value = false;
  forgetModelFlag.value = true
}


const login = () => {
  var reg = /^[A-Za-z0-9\u4e00-\u9fa5]+@[a-zA-Z0-9_-]+(\.[a-zA-Z0-9_-]+)+$/;
  if (!reg.test(state.username)) {
    Message({ type: "error", message: "邮箱格式不正确" });
    return false;
  }
  if (state.password.trim().length == 0) {
    Message({ type: "error", message: "密码不能为空" });
    return false;
  }
  //发送登录请求
  let param = new URLSearchParams();
  param.append("username", state.username);
  param.append("password", state.password);
  axios.post("/api/login", param).then(({ data }) => {
    if (data.flag) {
      state.username = "";
      state.password = "";
      user.login(data.data);
      user.closeModel();
      Message({ type: "success", message: "登录成功" });
    } else {
      Message({ type: "error", message: data.message });
    }
  });
}
</script>

<style scoped>
.social-login-title {
  margin-top: 1.5rem;
  color: #b5b5b5;
  font-size: 0.75rem;
  text-align: center;
}

.social-login-title::before {
  content: "";
  display: inline-block;
  background-color: #d8d8d8;
  width: 60px;
  height: 1px;
  margin: 0 12px;
  vertical-align: middle;
}

.social-login-title::after {
  content: "";
  display: inline-block;
  background-color: #d8d8d8;
  width: 60px;
  height: 1px;
  margin: 0 12px;
  vertical-align: middle;
}

.social-login-wrapper {
  margin-top: 1rem;
  font-size: 2rem;
  text-align: center;
}

.social-login-wrapper a {
  text-decoration: none;
}
</style>