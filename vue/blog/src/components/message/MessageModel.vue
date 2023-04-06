<script lang="ts" setup name="GlMessage">
import { ref, onMounted, PropType, defineProps } from 'vue'

defineProps({
    type: {
        type: String as PropType<'success' | 'error' | 'warning' | 'info'>,
        default: 'success'
    },
    message: {
        type: String,
        default: ''
    }
})

const isShow = ref(false)
onMounted(() => {
    isShow.value = true
})

// 定义一个对象，包含三种情况的样式，对象key就是类型字符串
const style = {
    warning: {
        icon: 'iconfont iconchenggong',
        color: '#FFF',
        backgroundColor: 'rgb(245,124,0)',
    },
    error: {
        icon: 'iconfont iconcuowu',
        color: '#FFF',
        backgroundColor: 'rgb(245,108,108)',
    },
    success: {
        icon: 'iconfont iconchenggong',
        color: '#FFF',
        backgroundColor: 'rgb(82,196,26)',
    },
    info: {
        icon: 'iconfont iconchenggong',
        color: '#FFF',
        backgroundColor: 'rgb(0,122,204)',
    }
}
</script>

<template>
    <Transition name="down">
        <div class="gl-message" v-show="isShow" :style="style[type]">
            <i class="iconfont" :class="style[type].icon"></i>
            <span class="text">{{ message }}</span>
        </div>
    </Transition>
</template>

<style scoped lang="less">
.down {
    &-enter {
        &-from {
            transform: translate3d(0, -75px, 0);
            opacity: 0;
        }

        &-active {
            transition: all 0.5s;
        }

        &-to {
            transform: none;
            opacity: 1;
        }
    }
}

.gl-message {
    width: 345px;
    height: 50px;
    position: fixed;
    z-index: 9999;
    top: 25px;
    line-height: 50px;
    padding: 0 25px;
    background: #f5f5f5;
    color: #999;
    border-radius: 6px;
    left: 50%;
    transform: translate(-50%);


    i {
        margin-right: 4px;
        vertical-align: middle;
    }

    .text {
        vertical-align: middle;
        font-size: .875rem;
    }
}
</style>
