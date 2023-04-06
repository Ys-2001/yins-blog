import useUserStore from './modules/user'
import useBlogStore from './modules/blogInfo'

// 统一导出useStore方法
export default function useStore() {
  return {
    user: useUserStore(),
    blog: useBlogStore(),
  }
}
