/**
 * Created by Major on 2016/11/22.
 */
app.controller('ResourcesController', ['$scope', 'ResourcesService', function ($scope, ResourcesService) {

    var currentSelectedResourcesNode = {
        id: null,
        orgName: null
    };

    //初始化菜单
    initResourcesTree();
    function initResourcesTree() {
        $scope.resourcesTree_settings = {
            view: {
                showIcon: true,
                dblClickExpand: false
            },
            check: {
                enable: false,
                radioType: "all"
            },
            data: {
                key: {
                    name: 'name'
                },
                simpleData: {
                    enable: true,
                    idKey: 'id',
                    pIdKey: "parentId",
                    //rootPid: 0
                }
            },
            callback: {
                onClick: function (event, treeId, treeNode) {
                    console.log("当前点击的", treeId)
                    currentSelectedResourcesNode.id = treeNode.id;
                    currentSelectedResourcesNode.name = treeNode.name;
                }

            }
        };

        //请求资源
        var res = ResourcesService.loadResourcesList();
        res.then(function (data) {
            if (data == null) {
                console.log("没有查询到信息");
            } else {
                $scope.resourcesData = data.data;
                console.log($scope.resourcesData);
                var orgTreeRender = $.fn.zTree.init($("#orgTree"), $scope.resourcesTree_settings, $scope.resourcesData);
                orgTreeRender.expandAll(true);//全部展开

                var nodes = orgTreeRender.getNodes(); //获取节点
                if (nodes.length > 0) {
                    orgTreeRender.selectNode(nodes[0]);
                }

                /*var defaultNode = "";
                 if($scope.resourcesData.length > 0){
                 angular.forEach($scope.resourcesData,function(item){
                 /!*if(item.id == 10000){*!/
                 if(item.id == $cookieStore.get("orgId")){
                 defaultNode = item;
                 currentSelectedResourcesNode.id = item.id;
                 currentSelectedResourcesNode.name = item.name;
                 }

                 });
                 }*/
            }
        })

    }

    //初始化导航
    if ($scope.getNavSize() === 0) {
        console.log($scope.getNavSize());
        var navItem = {
            href: 'app.system.resources',
            text: '系统管理 > 菜单管理'
        };
        $scope.addNavItem(navItem);
    }
    ;



}]);