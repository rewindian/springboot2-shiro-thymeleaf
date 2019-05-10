define(['com', 'js/api/homeApi.js'], function (Com, homeApi) {
    return {
        template: Com.getTemplate('home.html'),
        data: function () {
            return {
                name: ''
            }
        },
        methods: {},
        created: function () {
            var vm = this;
            homeApi.getName({id: '123'}).then(function (res) {
                var data = Com.assign({}, res.data);
                vm.name = data.name;
            });
        }
    }
});