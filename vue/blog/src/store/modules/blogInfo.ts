import { defineStore } from "pinia";

export default defineStore("blogInfo", {
    state: () => {
        return {
            blogInfo: {
                viewsCount: "",
                articleCount: 0,
                categoryCount: 0,
                tagCount: 0,
                websiteConfig: {
                    articleCover: "",
                    websiteNotice: "",
                    touristAvatar: "",
                    userAvatar: "",
                    websiteAuthor: "",
                    websiteAvatar: "",
                    websiteCover: "",
                    websiteCreateTime: "",
                    websiteIntro: "",
                    websiteName: "",
                    websiteRecordNo: "",
                }
            }
        }























    }
})