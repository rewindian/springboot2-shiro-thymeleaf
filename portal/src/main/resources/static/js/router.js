/**
 * 路由配置
 */
define(["resolve"], function (resolve) {
    return [
        {
            path: "/",
            name: "home",
            component: resolve(ctxPath + "js/models/home.js")
        },
        {
            path: "/news",
            name: "news",
            component: resolve(ctxPath + "js/models/news.js")
        }
    ];
});