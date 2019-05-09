define(['com', 'vue'], function (Com, Vue) {
    Vue.component('app-header', {
        template: '<div><p>我是头部</p>' +
            '<ul>' +
            '<li><a href="javascript:;" @click="routerTo(\'/\')">HOME</a></li>' +
            '<li><a href="javascript:;" @click="routerTo(\'/news\')">NEWS</a></li>' +
            '</ul>' +
            '</div>',
        methods: {
            routerTo: function (path) {
                this.$router.push(path);
            }
        },
        created: function () {
        }
    });

});