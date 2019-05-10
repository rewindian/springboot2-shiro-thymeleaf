/**
 * 通用方法封装
 */
(function (global, factory) {
    typeof define === 'function' && define.amd ? define(factory) :
        (global = global || self, global.Com = factory());
})(typeof window !== "undefined" ? window : this, (function () {

    var validation = {
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
        checkPhoneNum: function (tel) { //判断电话号码
            return this.checkMobile(tel) || this.checkTel(tel);
        },
        checkUsername: function (str) {
            return /^[a-zA-Z0-9]{6,20}$/.test(str);
        },
        checkEmail: function (str) {
            return /^([a-zA-Z]|[0-9])(\w|\-)+@[a-zA-Z0-9]+\.([a-zA-Z]{2,4})$/.test(str);
        }
    };

    var Com = function () {
        this.validation = validation;
    };

    Com.prototype = {
        log: function (msg) {
            if (console && typeof console.log === 'function') {
                console.log(msg);
            }
        },
        error: function (msg) {
            if (console && typeof console.error === 'function') {
                console.error(msg);
            }
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
        randomNum: function (min, max) {    //随机整数
            var range = Math.abs(max - min);
            var rand = Math.random();
            return Math.min(min, max) + Math.round(rand * range); //四舍五入
        },
        getTemplate: function (url) {   //请求获取字符串模板
            var templateHtml = '';
            var that = this;
            $.ajax({
                url: ctxPath + url,
                type: 'get',
                async: false,
                timeout: 5000,
                contentType: 'text/html;charset=UTF-8',
                success: function (res) {
                    templateHtml = res;
                },
                error: function (e) {
                    var msg = e.statusText;
                    that.error('字符串模板请求失败: \r\n' + url + '\r\n', msg);
                }
            });
            return templateHtml;
        },
        assign: function (target) { //浅拷贝对象
            'use strict';
            if (target == null) {
                throw new TypeError('Cannot convert undefined or null to object');
            }
            target = Object(target);
            for (var index = 1; index < arguments.length; index++) {
                var source = arguments[index];
                if (source != null) {
                    for (var key in source) {
                        if (Object.prototype.hasOwnProperty.call(source, key)) {
                            target[key] = source[key];
                        }
                    }
                }
            }
            return target;
        }
    };

    return new Com();
}));