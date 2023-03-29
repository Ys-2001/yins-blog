<template>
    <v-dialog v-model="registerModelFlag" :fullscreen="isMobile()" max-width="460">
        <v-card class="login-container" style="border-radius:4px">
            <v-icon class="float-right" @click="registerModelFlag = false">
                mdi-close
            </v-icon>
            <div class="login-wrapper">
                <!-- 用户名 -->
                <v-text-field v-model="state.username" label="邮箱号" placeholder="请输入您的邮箱号" clearable @keyup.enter="register" />
                <!-- 密码 -->
                <v-text-field v-model="state.password" class="mt-7" label="密码" placeholder="请输入您的密码" @keyup.enter="register"
                    :append-icon="state.show ? 'mdi-eye' : 'mdi-eye-off'" :type="state.show ? 'text' : 'password'"
                    @click:append="state.show = !state.show" />

                    <!-- 密码 -->
                <v-text-field v-model="state.password2" class="mt-7" label="确认密码" placeholder="请再次输入您的密码" @keyup.enter="register"
                    :append-icon="state.show ? 'mdi-eye' : 'mdi-eye-off'" :type="state.show ? 'text' : 'password'"
                    @click:append="state.show = !state.show" />
                <!-- 注册按钮 -->
                <v-btn class="mt-7" block color="red" style="color:#fff" @click="register">
                    注册
                </v-btn>
                <!-- 登录 -->
                <div class="mt-10 login-tip">
                    已有账号？<span @click="openLogin()">登录</span>
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
const { registerModelFlag,loginModelFlag } = toRefs(user);

const state = reactive({
  show: false,
  username: "",
  password: "",
  password2: "",
})



const isMobile = () => {
  const clientWidth = document.documentElement.clientWidth;
  if (clientWidth > 960) {
    return false;
  }
  return true;
}

const openLogin = () => {
    loginModelFlag.value = true
}


const register = () => {
  var reg = /^[A-Za-z0-9\u4e00-\u9fa5]+@[a-zA-Z0-9_-]+(\.[a-zA-Z0-9_-]+)+$/;
  if (!reg.test(state.username)) {
    Message({ type: "error", message: "邮箱格式不正确" });
    return false;
  }
  if (state.password.trim().length == 0) {
    Message({ type: "error", message: "密码不能为空" });
    return false;
  }
  if (state.password.trim().length < 6 && state.password2.trim().length < 6) {
    Message({ type: "error", message: "密码不能少于6位" });
        return false;
      }

  if (state.password2.trim().length == 0) {
    Message({ type: "error", message: "密码不能为空" });
    return false;
  }

  if (state.password2 !== state.password) {
    Message({ type: "error", message: "两次输入的密码不一致" });
    return false;
  }
  //发送登录请求
  const param ={
    username : state.username,
    password : state.password
  }
  axios.post("/api/register", param).then(({ data }) => {
    if (data.flag) {
      state.username = "";
      state.password = "";
      state.password2 = "";
      user.closeModel();
      Message({ type: "success", message: "注册成功" });
    } else {
      Message({ type: "error", message: data.message });
    }
  });
}

</script>