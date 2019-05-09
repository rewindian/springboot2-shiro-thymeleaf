/**
 * 原生方法扩展
 */
(function () {
    Array.prototype.contain = function (item) {
        if (!this || this.length === 0) {
            return;
        }
        for (var i = 0; i < this.length; i++) {
            if (item == this[i]) {
                return true;
            }
        }
        return false;
    };

//删除 array中包含prop且值为 value的项
    Array.prototype.delete = function (value, prop) {
        if (!this || this.length === 0) {
            return;
        }
        var _index;
        for (var _i = 0; _i < this.length; _i++) {
            var item = this[_i];
            if (item === UNDEFINED) {
                continue;
            }
            if (prop && item[prop] && item[prop] === value) {
                _index = _i;
                break;
            } else {
                if (item === value) {
                    _index = _i;
                    break;
                }
            }
        }
        if (_index !== UNDEFINED) {
            this.splice(_index, 1);
        }
    };

    /*
 *   des:        时间格式化
 *   @formatStr: 格式化参数
 *   @return:    string类型时间
 *   ex:         new Date().formatDate('yyyy-MM-dd')
 */
    Date.prototype.formatDate = function (formatStr) {
        if (!formatStr || typeof formatStr != 'string') formatStr = "yyyy-MM-dd HH:mm";
        var dict = {
            "yyyy": this.getFullYear(),
            "M": this.getMonth() + 1,
            "d": this.getDate(),
            "H": this.getHours(),
            "m": this.getMinutes(),
            "s": this.getSeconds(),
            "MM": ("" + (this.getMonth() + 101)).substr(1),
            "dd": ("" + (this.getDate() + 100)).substr(1),
            "HH": ("" + (this.getHours() + 100)).substr(1),
            "mm": ("" + (this.getMinutes() + 100)).substr(1),
            "ss": ("" + (this.getSeconds() + 100)).substr(1)
        };
        return formatStr.replace(/(yyyy|MM?|dd?|HH?|ss?|mm?)/g, function () {
            return dict[arguments[0]];
        });
    };

    /*
 *   des:        字符串转时间
 *   @return:    date类型时间
 *   ex:         '1991-01-01 08:00'.toDate()
 */
    String.prototype.toDate = function () {
        var date = this.toString();
        //IE
        if (!!window.ActiveXObject || "ActiveXObject" in window) {
            var result = new Date();
            if (date.length <= 10) {
                var str = date.split('-');
                result.setUTCFullYear(str[0], str[1] ? str[1] - 1 : 0, str[2] ? str[2] : 1);
                result.setUTCHours(-8, 0, 0, 0);
            } else {
                var strs = date.trim().split(" ");
                var one = strs[0].split('-'),
                    two = strs[1].split(':');
                result.setUTCFullYear(one[0], one[1] ? one[1] - 1 : 0, one[2] ? one[2] : 1);
                result.setUTCHours(two[0] - 8, two[1] ? two[1] : 0, two[2] ? two[2] : 0, 0);
            }
            return result;
        } else {
            //火狐
            if (navigator.userAgent.indexOf('Firefox') > -1) {
                date = new Date(date.replace(/ /g, 'T').replace(/\//g, "-"));
            } else {
                date = new Date(date.replace(/T/g, ' ').replace(/-/g, "/"));
            }
        }
        return date;
    };

    /*
 *   des:        格式化参数,用法同 C# 的 string.Format(),将字符串一个或多个格式,替换为指定对象
 *   @return:    格式化后的字符串
 *   ex:         '{0} world {1}'.formatString('hello','!!!!');
 */
    String.prototype.formatString = function () {
        var str = this.toString();
        for (var i = 0; i < arguments.length; i++) {
            var exp = new RegExp('\\{' + (i) + '\\}', 'gm');
            str = str.replace(exp, arguments[i]);
        }
        return str;
    };

    String.prototype.formatStringWithArr = function (arr) {
        var str = this.toString();
        for (var i = 0; i < arr.length; i++) {
            var exp = new RegExp('\\{' + (i) + '\\}', 'gm');
            str = str.replace(exp, arr[i]);
        }
        return str;
    };

    /*
     *   des:        字符串转时间
     *   @return:    date类型时间
     *   ex:         1427414400000.toDate()
     */
    Number.prototype.toDate = function () {
        return new Date(this);
    };
})();