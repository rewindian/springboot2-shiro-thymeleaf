/**
 * axios拦截器设置
 * 作为插件挂在到Vue
 */
define(['axios', 'com'], function (Axios, Com) {
    Axios.interceptors.request.use(
        function (request) {
            request.headers["Content-Type"] = "application/json;charset=UTF-8";
            // request.timeout = 5000;
            request.retry = 0;
            request.retryDelay = 1000;
            var url = request.url;
            if (Com.isNotEmpty(ctxPath) && url.indexOf("http") !== 0) {
                request.url = ctxPath + (url.indexOf("/") === 0 ? url.substring(1) : url);
            }
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
                } else if (response.data.result === "401") {    //鉴权失败
                    var error = new Error("登录验证失败");
                    error.code = "401";
                    return Promise.reject(error);
                } else {
                    Com.log(response.data.resultDesc);
                    return Promise.reject(new Error(response.data.resultDesc));
                }
            } else {
                var error = new Error("登录验证失败");
                error.code = response.status + "";
                return Promise.reject(error);
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