/**
 * Created by Major on 2016/11/22.
 */
app.service('ResourcesService', ['$http', '$q', 'WebHost', function ($http, $q, WebHost) {
    var url = WebHost.serverUrl + '/resources'
    return {
        loadResourcesList: function () {
            var deferred = $q.defer();
            $http({
                method: 'GET',
                url: url + '/query/1'
            }).success(function (data, status, header, config) {
                deferred.resolve(data);
            }).error(function (data, status, header, config) {
                deferred.reject(data, status, header, config);
            })
            return deferred.promise;
        },
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
    }

}]);