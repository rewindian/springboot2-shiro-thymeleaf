/**
 * 路由配置
 */
define(["resolve", "vueRouter", "axios"], function (resolve, vueRouter, Axios) {
    var routes = [
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
    var router = new vueRouter({
        routes: routes
    });
    router.beforeEach(function (to, from, next) {
        Axios.get("testSession").then(function (res) {
            next();
        }).catch(function (reason) {
            next(false);
            if (reason.code === '401') {
                window.location.href = "login.htm";
            }
        });

    });
    return router;
});