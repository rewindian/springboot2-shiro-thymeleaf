//requireJs 配置 ：该文件是第一个被加载执行
require.config({
    baseUrl: "js",
    paths: {
        jquery: "lib/jquery/jquery-1.11.3",
        vue: "lib/vue/vue",
        vueRouter: "lib/vue/vue-router",
        router: "router",
        header: "components/header",
        promise: "lib/promise/bluebird.min",
        com: "common/core",
        axios: "lib/axios/axios.min",
        http: "plugins/http"
    },
    shim: {
        vueRouter: ['vue'],
        com: ['jquery'],
        axios: ['promise'],
        http: ['axios']
    },
    waitSeconds: 0
});

require(["vue", "vueRouter", "router", "http", "header"], function (Vue, vueRouter, router, http) {
    Vue.use(vueRouter);
    Vue.use(http);
    var routes = new vueRouter({
        routes: router
    });

    new Vue({
        router: routes
    }).$mount("#indexApp");
});