/**
 * Created by Major on 2016/11/21.
 */
app.controller('NavController', ['$scope', '$state', 'MainService', function ($scope, $state, MainService) {
    //请求菜单
    initMenu();
    function initMenu() {
        var promise = MainService.initMenu(1);
        promise.then(function (data) {
            console.log(data);
            if (data.flag == 0) {
                $scope.menuList = data.data;
            }
        })
    }

    $scope.navClick = function (event, functionParam) {
        //设置菜单选中状态
        if (functionParam.level != 1 && functionParam.childs == null) {
            $scope.isNavActive = functionParam.resUrl;
        }

        //根据选中菜单跳转
        if (functionParam.resUrl) {
            $state.go(functionParam.resUrl, {
                //参数
                //"orgId": $cookieStore.get("orgId")
            });
        }

    }

}]);