<template>
    <v-app-bar app :class="navClass"  flat height="60">
        <!-- 手机端导航栏 -->
        <div class="d-md-none nav-mobile-container">
            <div style="font-size: 18px; font-weight: bold">
                <router-link to="/">
                    Y
                </router-link>
            </div>

            <div class="menus-item">
                <a class="menu-btn" @click="openSearch">
                    <i class="iconfont iconsousuo" /> 搜索
                </a>
            </div>


        </div>
        <!-- 电脑导航栏 -->
        <div class="d-md-block d-none nav-container">
            <div class="float-left blog-title blog-title2">
                <router-link to="/">
                    Y
                </router-link>
            </div>
            <div class="float-left blog-title">
                <div class="menus-item">
                    <router-link class="menu-btn" to="/">
                        <i class="iconfont iconzhuye" /> 首页
                    </router-link>
                </div>
                <div class="menus-item">
                    <a class="menu-btn" @click="openSearch">
                        <i class="iconfont iconsousuo" /> 搜索
                    </a>
                </div>
                <div class="menus-item">
                    <a class="menu-btn">
                        <i class="iconfont iconfaxian" /> 发现
                        <i class="iconfont iconxiangxia2 expand" />
                    </a>
                    <ul class="menus-submenu">
                        <li>
                            <router-link to="/archives">
                                <i class="iconfont iconguidang" /> 归档
                            </router-link>
                        </li>
                        <li>
                            <router-link to="/categories">
                                <i class="iconfont iconfenlei" /> 分类
                            </router-link>
                        </li>
                        <li>
                            <router-link to="/tags">
                                <i class="iconfont iconbiaoqian" /> 标签
                            </router-link>
                        </li>
                    </ul>
                </div>

                <div class="menus-item">
                    <router-link class="menu-btn" to="/links">
                        <i class="iconfont iconlianjie" /> 友链
                    </router-link>
                </div>
                <div class="menus-item">
                    <router-link class="menu-btn" to="/about">
                        <i class="iconfont iconzhifeiji" /> 关于
                    </router-link>
                </div>
                <div class="menus-item">
                    <router-link class="menu-btn" to="/message">
                        <i class="iconfont iconpinglunzu" /> 留言
                    </router-link>
                </div>

            </div>
            <div class="float-right nav-title">
                <div class="menus-item">
                    <a class="menu-btn" v-if="!isLogin" @click="openLogin">
                        <i class="iconfont icondenglu" /> 登录
                    </a>
                    <template v-else>
                        <img class="user-avatar" :src="userInfo.avatar" height="30" width="30" />
                        <ul class="menus-submenu">
                            <li>
                                <router-link to="/user">
                                    <i class="iconfont icongerenzhongxin" /> 个人中心
                                </router-link>
                            </li>
                            <li>
                                <a @click="logout"><i class="iconfont icontuichu" /> 退出</a>
                            </li>
                        </ul>
                    </template>
                </div>
            </div>
        </div>
    </v-app-bar>
</template>

<script setup lang="ts">
import { ref, onMounted, toRefs } from "vue";
import useStore from '../../store/'
import axios from "axios";
import Message from "../message";

const { user } = useStore();
const { loginModelFlag, userInfo, isLogin, searchModelFlag } = toRefs(user)
const scrollTop = ref(0);
const navClass = ref("nav");

const scroll = () => {
    scrollTop.value = window.pageYOffset || document.documentElement.scrollTop || document.body.scrollTop;
    if (scrollTop.value > 60) {
        navClass.value = "nav-fixed";
    } else {
        navClass.value = "nav";
    }
};

const logout = () => {
    axios.get("/api/logout").then(({ data }) => {
        if (data.flag) {
            user.logout();
            Message({ type: "success", message: "注销成功" });
        } else {
            Message({ type: "error", message: data.message });
        }
    });
}


onMounted(() => {
    window.addEventListener("scroll", scroll);
});

const openLogin = () => {
    loginModelFlag.value = true;
};

const openSearch = () => {
    searchModelFlag.value = true;
};

</script>

<style scoped>
i {
    margin-right: 4px;
}

ul {
    list-style: none;
}

.nav {
    background: rgba(0, 0, 0, 0) !important;
}

.nav a {
    color: #eee !important;
}

.nav .menu-btn {
    text-shadow: 0.05rem 0.05rem 0.1rem rgba(0, 0, 0, 0.3);
}

.nav .blog-title a {
    text-shadow: 0.1rem 0.1rem 0.2rem rgba(0, 0, 0, 0.15);
}

.v-theme--light.nav-fixed {
    background: rgba(255, 255, 255, 0.8) !important;
    box-shadow: 0 5px 6px -5px rgba(133, 133, 133, 0.6);
}

.v-theme--dark.nav-fixed {
    background: rgba(18, 18, 18, 0.8) !important;
}

.v-theme--dark.nav-fixed a {
    color: rgba(255, 255, 255, 0.8) !important;
}

.v-theme--light.nav-fixed a {
    color: #4c4948 !important;
}

.nav-fixed .menus-item a,
.nav-fixed .blog-title a {
    text-shadow: none;
}

.nav-container {
    font-size: 14px;
    width: 100%;
    height: 100%;
    overflow: visible;
}

.nav-mobile-container {
    width: 100%;
    display: flex;
    align-items: center;
}

.blog-title,
.nav-title {
    display: flex;
    align-items: center;
    height: 100%;
}

.blog-title a {
    font-size: 18px;
    font-weight: bold;
}

.blog-title2 {
    margin-right: 5%;
}

.menus-item {
    position: relative;
    display: inline-block;
    margin: 0 0 0 0.875rem;
}

.menus-item a {
    transition: all 0.2s;
}

.nav-fixed .menu-btn:hover {
    color: #49b1f5 !important;
}

.menu-btn:hover:after {
    width: 100%;
}

.menus-item a:after {
    position: absolute;
    bottom: -5px;
    left: 0;
    z-index: -1;
    width: 0;
    height: 3px;
    background-color: #80c8f8;
    content: "";
    transition: all 0.3s ease-in-out;
}

.user-avatar {
    cursor: pointer;
    border-radius: 50%;
}

.menus-item:hover .menus-submenu {
    display: block;
}

.menus-submenu {
    position: absolute;
    display: none;
    right: 0;
    width: max-content;
    margin-top: 8px;
    box-shadow: 0 5px 20px -4px rgba(0, 0, 0, 0.5);
    background-color: #fff;
    animation: submenu 0.3s 0.1s ease both;
}

.menus-submenu:before {
    position: absolute;
    top: -8px;
    left: 0;
    width: 100%;
    height: 20px;
    content: "";
}

.menus-submenu a {
    line-height: 2;
    color: #4c4948 !important;
    text-shadow: none;
    display: block;
    padding: 6px 14px;
}

.menus-submenu a:hover {
    background: #4ab1f4;
}

@keyframes submenu {
    0% {
        opacity: 0;
        filter: alpha(opacity=0);
        transform: translateY(10px);
    }

    100% {
        opacity: 1;
        filter: none;
        transform: translateY(0);
    }
}
</style>