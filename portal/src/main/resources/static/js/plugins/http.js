/**
 * axios拦截器设置
 */
define(['axios', 'promise', 'com'], function (Axios, Promise, Com) {
    Axios.interceptors.request.use(
        function (request) {
            request.headers["Content-Type"] = "application/json;charset=UTF-8";
            request.timeout = 5000;
            request.retry = 0;
            request.retryDelay = 1000;
            return request;
        },
        function (error) {
            return Promise.reject(error);
        }
    );
    Axios.interceptors.response.use(
        function (response) {
            if (response.status === 200) {
                // 正常请求，并且逻辑验证成功
                if (response.data.result === "000000") {
                    return response.data;
                } else {
                    Com.log(response.data.resultDesc);
                    return Promise.reject(response);
                }
            } else {
                return Promise.reject(response);
            }
        },
        function (error) {
            return Promise.reject(error);
        }
    );
    return {
        install: function (Vue) {
            Vue.prototype.$http = Axios;
        }
    }
});