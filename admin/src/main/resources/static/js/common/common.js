var UNDEFINED;

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

var com;

(function ($) {
    com = {
        ajax: function (opts) {
            if (typeof opts === 'string') {
                opts = {
                    url: opts
                };
            }
            var ajaxDefaults = {
                dataType: 'json',
                contentType: 'application/json;charset=UTF-8',
                cache: false, //设置为false将不会从浏览器缓存中加载请求信息。开发false,发布true   *改为了false*
                error: function (e) {
                    // console.log(e)
                    var msg = e.statusText;
                    console.error('请求出错,请联系管理员: \r\n' + opts.url + '\r\n', msg);
                }
            };
            opts = $.extend(ajaxDefaults, opts);
            return $.ajax(opts);
        },
        get: function (url, data, callBack) {
            return this.ajax({
                url: url,
                data: data,
                type: 'get',
                success: callBack
            });
        },
        post: function (url, data, callBack) {
            if (typeof data !== 'string') {
                data = JSON.stringify(data);
            }
            return this.ajax({
                url: url,
                data: data,
                type: 'post',
                success: callBack
            });
        },
        ajaxSuccess: function (res, noData) {
            return noData ? res && res.result === "000000" : res && res.result === "000000" && !this.isEmpty(res.data);
        },
        isEmpty: function (param) {
            if (param === null) return true;
            if (param === 'null') return true;
            if (param === '') return true;
            if (param === undefined) return true;
            if (this.isEmptyObject(param)) return true;
            if ((this.isArray(param)) && (param.length === 0)) return true;
            return false;
        },
        isNotEmpty: function (param) {
            return !this.isEmpty(param);
        },
        isArray: function (value) {
            if (typeof Array.isArray === "function") {
                return Array.isArray(value);
            } else {
                return Object.prototype.toString.call(value) === "[object Array]";
            }
        },
        isEmptyObject: function (obj) {
            for (var key in obj) {
                return false
            }
            return true
        },
        tips: function (message, type) { // sure 成功 绿色  caution警示橙色   red 红色
            var colorClass = 'sure';
            if (type === 1) {
                colorClass = 'caution';
            } else if (type === 2) {
                colorClass = 'red';
            }
            var $tips = $('<div class="yql_jcxt_tips ' + colorClass + ' dis_none">' + message + '</div>');
            $("body").append($tips);
            $tips.show().delay(2000).fadeOut(100);
            setTimeout(function () {
                $tips.remove();
            }, 2200)

        },
        confirm: function (title, tips, callbackOk) {
            var content = '  <div id="jy_dia">\n' +
                '    <div class="f20 c555 lh36 mgl66">\n' +
                '     <i class="dia_iconTips mgr10"></i><span class="dis_inb lh36 v_m">' + tips + '</span>\n' +
                '    </div>\n' +
                '    <div class="yql_dia_btnBox mgt50 clearfix t_c  ">\n' +
                '      <a href="javascript:;" class="yql_jc_btn02 sumbit  mgr10">确认</a>\n' +
                '      <a href="javascript:;" class="yql_jc_btn02 cancel ">取消</a>\n' +
                '    </div>\n' +
                '  </div>\n';
            var dialog = art.dialog({
                title: title,
                content: content, //弹出框的内容
                width: '500px', //弹出框的宽度
                height: '206px',
                initialize: function () {
                    var that = this;
                    $("#jy_dia").on("click","a.sumbit",function(){
                        callbackOk();
                        that.close();
                    })
                    $("#jy_dia").on("click","a.cancel",function(){
                        that.close();
                    })
                } //自定义函数
            });

        },
        validation: {
            getByteLen: function (val) { //获取字符串长度中文占2个字符
                var len = 0;
                for (var i = 0; i < val.length; i++) {
                    var a = val.charAt(i);
                    if (a.match(/[^\x00-\xff]/ig) != null) {
                        len += 2;
                    }
                    else {
                        len += 1;
                    }
                }
                return len;
            },
            includeChn: function (val) { //是否包含中文
                var flag = false;
                for (var i = 0; i < val.length; i++) {
                    var a = val.charAt(i);
                    if (a.match(/[^\x00-\xff]/ig) != null) {
                        flag = true;
                        break;
                    }
                }
                return flag;
            },
            checkSpecChar: function (str) { //是否包含特殊字符
                var containSpecial = RegExp(/[(\ )(\~)(\!)(\@)(\#)(\$)(\%)(\^)(\&)(\*)(\()(\))(\-)(\_)(\+)(\=)(\[)(\])(\{)(\})(\|)(\\)(\;)(\:)(\')(\")(\,)(\.)(\/)(\<)(\>)(\?)(\)]+/);
                return containSpecial.test(str);
            },
            checkMobile: function (tel) { //判断移动电话号码
                return /^1\d{10}$/.test(tel);
            },
            checkTel: function (tel) { //判断座机号码
                return /^(\(\d{3,4}\)|\d{3,4}-|\s)?\d{7,14}$/.test(tel);
            },
            checkPhoneNum(tel) { //判断电话号码
                return this.checkMobile(tel) || this.checkTel(tel);
            },
            checkUsername: function (str) {
                return /^[a-zA-Z0-9]{6,20}$/.test(str);
            },
            checkEmail: function (str) {
                return /^([a-zA-Z]|[0-9])(\w|\-)+@[a-zA-Z0-9]+\.([a-zA-Z]{2,4})$/.test(str);
            }
        }
    }
})($);






