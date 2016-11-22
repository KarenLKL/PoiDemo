'use strict';

/* Filters */
// need load the moment.js to use this filter. 
angular.module('app')
    .filter('fromNow', function () {
        return function (date) {
            return moment(date).fromNow();
        }
    })
    .filter('parseDate', function ($filter) {
        return function (date, format) {
            if (!format) {
                format = "yyyy-MM-dd HH:mm:ss";
            }
            if (angular.isDate(date)) {
                return $filter('date')(date, format);
            }
            if (angular.isString(date)) {
                var transDate = new Date(date.replace(/-/g, "/"));
                var returnDate = $filter('date')(transDate, format);
                if (returnDate) {
                    return returnDate;
                }
                return date;
            }

            if (angular.isNumber(date)) {
                return $filter('date')(date, format);
            }
        }
    })
    .filter('strTodate', function ($filter) {
        return function (date, format) {
            if (angular.isString(date)) {
                if (date && (date.length >= 8) && new Date($.trim(date)).getTime()) {
                    return $filter('parseDate')(date, format);
                }
            }

            return date;
        }
    })