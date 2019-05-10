//requireJs 配置 ：该文件是第一个被加载执行
require.config({
    baseUrl: "js",
    paths: {
        jquery: "lib/jquery/jquery-1.11.3",
        vue: "lib/vue/vue",
        vueRouter: "lib/vue/vue-router",
        router: "router",
        header: "components/header",
        com: "common/core",
        axios: "lib/axios/axios.min",
        http: "plugins/http"
    },
    shim: {
        vueRouter: ['vue'],
        com: ['jquery'],
        http: ['axios'],
        header: ['vue']
    },
    waitSeconds: 0
});

require(["vue", "vueRouter", "router", "http", "header"], function (Vue, vueRouter, router, http) {
    Vue.use(vueRouter);
    Vue.use(http);
    new Vue({
        router: router
    }).$mount("#indexApp");
});