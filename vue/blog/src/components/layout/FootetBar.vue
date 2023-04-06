<template>
    <v-bottom-navigation>
        <v-btn class="btn_btn">
            <v-icon>mdi-music-note</v-icon>
            <span>Music</span>
        </v-btn>

        <v-btn class="btn_shijian">
            <div>
                ©{{ formDateYear(new Date(blogInfo.websiteConfig.websiteCreateTime)) }} -
                {{ new Date().getFullYear() }} By
                {{ blogInfo.websiteConfig.websiteAuthor }}
            </div>
        </v-btn>

        <v-btn class="btn_beian">
            <a href="https://beian.miit.gov.cn/" target="_blank">
                {{ blogInfo.websiteConfig.websiteRecordNo }}
            </a>
        </v-btn>


    </v-bottom-navigation>
</template>
  
<script setup lang="ts">
import { computed } from 'vue';
// import { useRouter } from 'vue-router';
import useStore from "@/store/"
import { formDateYear } from '@/utils/dateFormat';

const { blog } = useStore();

// let router = useRouter();

// const isMessage = computed(() => {
//     return router.currentRoute.value.path == "/message";
// });
// const isChatgpt = computed(() => {
//     return router.currentRoute.value.path == "/chat";
// })
const blogInfo = computed(() => {
    return blog.blogInfo;
})

</script>

<script>
</script>
  
<style scoped>
@keyframes Gradient {
    0% {
        background-position: 0 50%;
    }

    50% {
        background-position: 100% 50%;
    }

    100% {
        background-position: 0 50%;
    }
}

.v-bottom-navigation {
    width: 100%;
    line-height: 2;
    position: relative !important;
    padding: 40px 20px;
    color: #eee;
    font-size: 14px;
    text-align: center;
    background: linear-gradient(-45deg, #23a6d5, #23d5ab);
    background-size: 400% 400%;
    animation: Gradient 10s ease infinite;
}

.v-bottom-navigation a {
    color: #eee !important;
}

.btn_btn {
    position: relative;
    text-align: center;
    float: left;
}

.btn_shijian {
    position: relative;
    text-align: center;
    margin: auto;

}

.btn_beian {
    position: relative;
    text-align: center;
    float: right;

}
</style>
  