'use strict';
/**
 * 常用过滤器
 */
angular.module('app')
    .filter('isOrBoot', function () {
        return function (isEnabled) {
            return isEnabled === 1 ? '启用' : '禁用';
        }
    })
    .filter('isOrNo', function () {
        return function (isEnabled) {
            return isEnabled === 1 ? '是' : '否';
        }
    })
    .filter('acptrim', function () {
        return function (item, length) {
            if (angular.isString(item)) {
                if ((item.length || 0) > (parseInt(length) || 0)) {
                    return item.substring(0, length) + "...";
                }
            }
            return item;
        }
    })
    .filter('acpSubstring', function () {
        return function (item, length) {
            if (angular.isString(item)) {
                if ((item.length || 0) > (parseInt(length) || 0)) {
                    return item.substring(0, length);
                }
            }
            return item;
        }
    })
    .filter('acSplit', function () {
        return function (item, splitChar, splitIndex) {
            if (item) {
                return item.split(',')[0];
            }
            return "";
        }
    });