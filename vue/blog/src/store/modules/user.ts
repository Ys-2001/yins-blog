import { defineStore } from "pinia";

export default defineStore("user", {
    state: () => {
        return {
            userInfo: {
                userId: null,
                avatar: "",
                token: "",
                nickname: "",
                intro: "",
                webSite: "",
                articleLikeSet: [],
                commentLikeSet: [],
                email: "",
                loginType: null,
            },
            isLogin: false,
            loginModelFlag: false,
            registerModelFlag: false,
            forgetModelFlag: false,
            emailModelFlag: false,
            searchModelFlag: false,
            drawer: false,
            loginUrl: "",
            blogInfo: {},
            token: "",
        }
    },
    actions: {
        login(userInfo: any) {
            this.userInfo = userInfo;
            this.isLogin = true;
            this.token = this.userInfo.token;
        },
        logout() {
            this.userInfo.userId = null;
            this.userInfo.avatar = "";
            this.userInfo.nickname = "";
            this.userInfo.intro = "";
            this.userInfo.webSite = "";
            this.userInfo.articleLikeSet = [];
            this.userInfo.commentLikeSet = [];
            this.userInfo.email = "";
            this.userInfo.loginType = null;
            this.isLogin = false;
        },
        saveLoginUrl(loginUrl: string) {
            this.loginUrl = loginUrl;
        },
        saveEmail(email: string) {
            this.userInfo.email = email;
        },
        updateUserInfo(userInfo: any) {
            this.userInfo.nickname = userInfo.nickname;
            this.userInfo = userInfo.intro;
            this.userInfo = userInfo.webSite;
        },
        updateAvatar(avatar: string) {
            this.userInfo.avatar = avatar;
        },
        closeModel() {
            this.registerModelFlag = false;
            this.loginModelFlag = false;
            this.searchModelFlag = false;
            this.emailModelFlag = false;
            this.forgetModelFlag = false;
        },
        commentLike(commentId: number) {
            const commentLikeSet: number[] = this.userInfo.commentLikeSet;
            if (commentLikeSet.indexOf(commentId) != -1) {
                commentLikeSet.splice(
                    commentLikeSet.indexOf(commentId),
                    1
                );
            } else {
                commentLikeSet.push(commentId);
            }
        },
        articleLike(articleId: number) {
            const commentLikeSet: number[] = this.userInfo.commentLikeSet;
            if (commentLikeSet.indexOf(articleId) != -1) {
                commentLikeSet.splice(
                    commentLikeSet.indexOf(articleId),
                    1
                );
            } else {
                commentLikeSet.push(articleId);
            }
        },
    },
    persist: {
        enabled: true, // 开启数据缓存
        strategies: [
            {
                storage: sessionStorage,
                paths: ['userInfo', 'isLogin'], //只对指定的state进行缓存
            },
            {
                storage: sessionStorage,
                key: "token",
                paths: ['token']
            }
        ],
    }





















})