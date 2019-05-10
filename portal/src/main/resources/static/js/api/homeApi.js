define(['axios'], function (Axios) {
    return {
        getName: function (params) {
            return Axios.get('/testHttp', {params: params});
        }
    }
});