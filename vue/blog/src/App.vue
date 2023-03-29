<template>
  <v-app id="app">
    <TopNavBar></TopNavBar>
    <router-view />
    <LoginModel></LoginModel>
    <RegisterModel></RegisterModel>
  </v-app>
</template>

<script setup lang="ts">
import TopNavBar from "./components/layout/TopNavBar.vue";
import LoginModel from "./components/model/LoginModel.vue"
import RegisterModel from "./components/model/RegisterModel.vue";
</script>
