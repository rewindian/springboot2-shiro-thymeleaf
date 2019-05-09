define(['com'], function (Com) {
    return {
        template: Com.getTemplate('views/home.html'),
        data: function () {
            return {
                name: ''
            }
        },
        created: function () {
            var vm = this;
            this.$http.get(ctxPath + 'testHttp').then(function (res) {
                var data = Com.assign({}, res.data);
                vm.name = data.name;
            });
        }
    }
});