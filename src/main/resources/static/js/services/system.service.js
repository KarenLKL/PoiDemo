/**
 * 系统服务
 * author : hanwj
 * serviceName:LoginService
 */
app.factory("SystemService", ['WebHost', '$http', '$q', '$state', '$cookieStore', '$rootScope', 'commonUtil', function (WebHost, $http, $q, $state, $cookieStore, $rootScope, commonUtil) {
    var url = WebHost.monitorUrl;
    var serviceurl = WebHost.platformUrl;
    /*
     登录验证及权限获取
     */
    return {
        login: function (username, password, isRemenberMe, mtd) {

            var deferred = $q.defer();
            $http({
                method: 'POST',
                url: url + "/security/login",
                params: {
                    "userName": username,
                    "password": password,
                    "isRemenberMe": isRemenberMe,
                    "mtd": mtd
                }
            }).success(function (data, status, header, config) {
                deferred.resolve(data);
            }).error(function (data, status, header, config) {
                deferred.reject(data, status, header, config);
            });
            return deferred.promise;
        },
        //通过用户名判断用户是否存在
        findUser: function (username) {
            var deferred = $q.defer();
            $http({
                method: 'GET',
                url: WebHost.portalUrl + "/memberCenter/checkUserExists/" + username
            }).success(function (data, status, header, config) {
                deferred.resolve(data);
            }).error(function (data, status, header, config) {
                deferred.reject(data, status, header, config);
            });
            return deferred.promise;
        },
        validCode: function (code, tokenid) {
            var deferred = $q.defer();
            $http({
                method: 'GET',
                url: WebHost.portalUrl + "/captcha/validate",
                //url:"http://10.88.20.73:8094/acp-web-portal/captcha/validate",
                params: {
                    "piccaptcha": code,
                    "tokenid": tokenid
                }
            }).success(function (data, status, header, config) {
                deferred.resolve(data);
            }).error(function (data, status, header, config) {
                deferred.reject(data, status, header, config);
            });
            return deferred.promise;
        },
        //获取手机验证码
        getMobileValidCode: function (mobile) {
            var deferred = $q.defer();
            $http({
                method: 'GET',
                url: serviceurl + "/security/getVerifyCode/gstar/" + mobile
            }).success(function (data, status, header, config) {
                deferred.resolve(data);
            }).error(function (data, status, header, config) {
                deferred.reject(data, status, header, config);
            });
            return deferred.promise;
        },
        //比较手机验证码
        compareToeValidCode: function (mobile, code) {
            var deferred = $q.defer();
            $http({
                method: 'GET',
                url: serviceurl + '/user/compareToVerifyCode',
                //url:'http://10.88.20.92:8080/user/compareToVerifyCode',
                params: {
                    "mobile": mobile,
                    "code": code,
                    "Access-Token": commonUtil.getToken()
                }
            }).success(function (data, status, header, config) {
                deferred.resolve(data);
            }).error(function (data, status, header, config) {
                deferred.reject(data, status, header, config);
            });
            return deferred.promise;
        },
        //修改密码
        updateUserPassword: function (obj) {
            var deferred = $q.defer();
            var param = {
                "mobile": obj.mobile,
                "password": obj.password,
                "verifyCode": obj.verifyCode
            };
            $http({
                method: 'POST',
                url: serviceurl + '/user/setPwdWithVarifyCode',
                data: angular.toJson(param),
                params: {
                    "Access-Token": commonUtil.getToken()
                },
                headers: {
                    'Content-Type': 'application/json;charset=utf-8'
                }
            }).success(function (data, status, header, config) {
                deferred.resolve(data);
            }).error(function (data, status, header, config) {
                deferred.reject(data, status, header, config);
            });
            return deferred.promise;
        },
        //操作权限查询
        getOperatePermissions: function (functionId) {
            var deferred = $q.defer();
            var id = $cookieStore.get('id');
            //id=123;
            if ((typeof(id) == "undefined") || (functionId == "undefined")) {
                return;
            }
            $http({
                method: 'POST',
                url: url + "/security/getOperatePermission",
                params: {
                    "userId": id,
                    "functionId": functionId,
                    "Access-Token": commonUtil.getToken()
                }
            }).success(function (data, status, header, config) {
                deferred.resolve(data);
            }).error(function (data, status, header, config) {

            });
            return deferred.promise;
        },
        //用户刷新查询菜单
        getMenus: function () {

            var deferred = $q.defer();
            var id = $cookieStore.get('id');
            if ((typeof(id) == "undefined")) {
                return;
            }

            $http({
                method: 'POST',
                url: url + "/security/getMenus",
                params: {
                    "userId": id,
                    "Access-Token": commonUtil.getToken()
                }
            }).success(function (data, status, header, config) {
                deferred.resolve(data);
            }).error(function (data, status, header, config) {

            });
            return deferred.promise;
        },
        /**
         * 获取用户信息
         * @param  userId 用户Id；currentPage 当前页数； pageSize 页数大小
         * @return promise
         */
        getUserMessageList: function (userId, currentPage, pageSize) {
            var deferred = $q.defer();
            $http({
                url: url + "/message/all",
                //url:  url2+"message/all",
                method: "POST",
                params: {
                    "userid": userId,
                    "currentPage": currentPage,
                    "pageSize": pageSize,
                    "Access-Token": commonUtil.getToken()
                }
            }).success(function (data, status, header, config) {
                deferred.resolve(data);
            }).error(function (data, status, header, config) {
                deferred.reject(data);
            });
            return deferred.promise;
        },
        /**
         * 告警信息
         * @param  userId 用户Id；currentPage 当前页数； pageSize 页数大小
         * @return promise
         */
        getAlarmMessageList: function (userId, currentPage, pageSize) {
            var deferred = $q.defer();
            $http({
                //url:"http://10.88.20.94:8080/alarm/query",
                url: url + "/alarm/query",
                method: "POST",
                params: {
                    "userid": userId,
                    "pageno": currentPage,
                    "pageSize": pageSize,
                    "Access-Token": commonUtil.getToken()
                }
            }).success(function (data, status, header, config) {
                deferred.resolve(data);
            }).error(function (data, status, header, config) {
                deferred.reject(data);
            });
            return deferred.promise;
        },
        /**
         * 异常信息
         * @param  userId 用户Id；currentPage 当前页数； pageSize 页数大小
         * @return promise
         */
        getExceptionMessageList: function (userId, currentPage, pageSize) {
            var deferred = $q.defer();
            $http({
                url: url + "/alarm/exception/all",
                //url: url2+"alarm/exception/all",
                method: "POST",
                params: {
                    "userid": userId,
                    "currentPage": currentPage,
                    "pageSize": pageSize,
                    "Access-Token": commonUtil.getToken()
                }
            }).success(function (data, status, header, config) {
                deferred.resolve(data);
            }).error(function (data, status, header, config) {
                deferred.reject(data);
            });
            return deferred.promise;
        },
        /**
         * 告警信息删除
         * @param  u
         * @return promise
         */
        delAlarmInfo: function (userId, ids) {
            var deferred = $q.defer();
            $http({
                //url:"http://10.88.20.94:8080/acp-web-monitor/alarm/delete",
                url: url + "/alarm/delete",
                method: "POST",
                params: {
                    "userid": userId,
                    "ids": ids,
                    "Access-Token": commonUtil.getToken()
                }
            }).success(function (data, status, header, config) {
                deferred.resolve(data);
            }).error(function (data, status, header, config) {
                deferred.reject(data);
            });
            return deferred.promise;
        },
        /**
         * 获取系统告警信息总数
         * @param
         * @return promise
         */
        getSysMessageInfoCount: function (userId) {
            var deferred = $q.defer();
            $http({
                //url:"http://10.88.20.94:8080/acp-web-monitor/message/getAmount",
                url: url + "/message/getAmount",
                method: "POST",
                params: {
                    "userid": userId
                }
            }).success(function (data, status, header, config) {
                deferred.resolve(data);
            }).error(function (data, status, header, config) {
                deferred.reject(data);
            });
            return deferred.promise;
        },
        /**
         * 从百度获取天气信息
         * @param
         * @return promise
         */
        getSysWeatherInfoFromBaidu: function (area) {
            var deferred = $q.defer();
            $http({
                //url:"http://10.88.20.104:8079/acp-web-monitor/weather/getBaidu",
                url: url + "/weather/getBaidu",
                method: "POST",
                params: {
                    "area": area
                }
            }).success(function (data, status, header, config) {
                deferred.resolve(data);
            }).error(function (data, status, header, config) {
                deferred.reject(data);
            });
            return deferred.promise;
        },
        /**
         * 从百度获取天气信息
         * @param
         * @return promise
         */
        getSysWeatherNationInfo: function (area, proven) {
            var deferred = $q.defer();
            $http({
                //url:"http://10.88.20.104:8079/acp-web-monitor/weather/getNation",
                url: url + "/weather/getNation",
                method: "POST",
                params: {
                    "area": area,
                    "proven": proven
                }
            }).success(function (data, status, header, config) {
                deferred.resolve(data);
            }).error(function (data, status, header, config) {
                deferred.reject(data);
            });
            return deferred.promise;
        },
        /**区域（省份/市）*/
        //查询省份
        queryAllProvence: function () {
            var deferred = $q.defer();
            var url = serviceurl + '/org/queryArea';
            $http({
                url: url,
                method: "get",
                params: {
                    "Access-Token": commonUtil.getToken()
                }
            }).success(function (data) {
                deferred.resolve(data);
            }).error(function (data) {
                deferred.reject(data);
            });
            return deferred.promise;
        },
        //根据省查询市
        changeprovence: function (myprov) {
            var deferred = $q.defer();
            var url = serviceurl + '/org/queryAreaCity/gstar/' + myprov;
            $http({
                url: url,
                method: "get",
                params: {
                    "Access-Token": commonUtil.getToken()
                }
            }).success(function (data) {
                deferred.resolve(data);
            }).error(function (data) {
                deferred.reject(data);
            });
            return deferred.promise;
        },

    }
}]);
