import { defineStore } from "pinia";

export default defineStore("blog", {
    state: () => {
        return {
            blogInfo: {
                viewsCount: "",
                articleCount: 0,
                categoryCount: 0,
                tagCount: 0,
                pagaList: [],
                websiteConfig: {
                    gitee: "",
                    github: "",
                    qq: "",
                    socialUrlList: [""],
                    articleCover: "",
                    websiteNotice: "",
                    touristAvatar: "",
                    userAvatar: "",
                    websiteAuthor: "Y",
                    websiteAvatar: "",
                    websiteCover: "",
                    websiteCreateTime: "2021",
                    websiteIntro: "",
                    websiteName: "青春似乎不应该颓废",
                    websiteRecordNo: "鄂ICP备2020022189号-1",
                    weiXinQRCode:"",
                    alipayQRCode:"",
                    isReward:0,
                    


                }
            }
        }

    }
})