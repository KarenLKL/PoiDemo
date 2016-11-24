/**
 * 主页面service，包括头部和左侧菜单
 * Created by Major on 2016/11/22.
 */
app.service('MainService', ['WebHost', '$http', '$q', function (WebHost, $http, $q) {
    var url = WebHost.serverUrl + "/resources";
    return {
        initMenu: function (type) {
            var defer = $q.defer();
            $http({
                method: 'GET',
                url: url + '/queryJson/' + type
            }).success(function (data, status, header, config) {
                defer.resolve(data);
            }).error(function (data, status, header, config) {
                defer.reject(data, status, header, config);
            });
            return defer.promise;
        }
    }
}]);
