import { h, render, ref } from 'vue'
import GlMessage from './MessageModel.vue'
type Props = {
    type: 'success' | 'error' | 'warning' | 'info'
    message: string
    duration?: number
}
const div = document.createElement('div')
div.setAttribute('class', 'gl-message')
document.body.appendChild(div)
// 定时器
const timer = ref<any>(null)
export default function Message({ type, message, duration = 3000 }: Props) {
    const vNode = h(GlMessage, { type, message })
    render(vNode, div)
    timer.value && clearTimeout(timer.value)
    timer.value = setTimeout(() => {
        render(null, div)
    }, duration)
}

Message.sussess = (message: string, duration?: number) => {
    Message({ type: 'success', message, duration })
}
Message.error = (message: string, duration?: number) => {
    Message({ type: 'error', message, duration })
}
Message.warning = (message: string, duration?: number) => {
    Message({ type: 'warning', message, duration })
}
Message.info = (message: string, duration?: number) => {
    Message({ type: 'info', message, duration })
}

