angular.module("app")
/**
 * 分页插件
 * 陈星伯
 * 2016-05-06
 *
 * @Param page-options ：数组，每页显示的记录数选择列表 [5,10,15,20]
 *
 * @Param  page-info : 对象，分页信息，{currentPage : 当前页, pageSize : 每页记录数， totalRecord : 总记录数}
 *
 * @Param query-method : 函数，查询数据时调用的方法，带小括号
 *
 * @Eg: <acp-pagebar page-options="perPageOptions" page-info="pageInfo" page-for-index="countPage" query-method="queryData()"></acp-pagebar>
 */
    .directive('acpPagebar', function () {

        var template = [];
        template.push('<div class="acp-card-pagination">');
        template.push('<div class="pagination">');
        template.push('<pagination class="pagination-sm" ng-model="pageInfo.currentPage" num-pages="numPages" total-items="pageInfo.totalRecord" max-size="8" items-per-page="pageInfo.pageSize" ng-change="query()" previous-text="上一页" next-text="下一页" first-text="首页" last-text="末页" boundary-links="true"></pagination>');
        template.push('</div>');
        /*template.push('<div class="pageInfo col-md-3 col-sm-4 col-xs-4">');
         /!*template.push('<span class="curry-page-panel">第 <input class="curry-page-ipt" ng-model="jumpPage" type="text" ng-keyup="jumpToPage($event);"/> 页</span>');
         template.push('<span class="page-size-panel">每页 <select class="page-size-select" ng-model="pageInfo.pageSize" ng-options="option as option for option in pageOptions" ng-change="query()"></select> 条</span>');*!/
         /!*template.push('<span class="total-items-panel">共 </span><span class="total-items-panel" ng-bind="pageInfo.totalRecord"></span><span class="total-items-panel"> 条</span>');*!/
         template.push('</div>');*/
        template.push('</div>');

        return {
            replace: true,
            restrict: 'E',
            priority: 0,
            template: template.join(''),
            scope: {
                pageOptions: '=',
                pageInfo: '=',
                queryMethod: '&'
            },
            controller: function ($scope) {
                $scope.pageForIndex = 1;
                $scope.jumpPage = 1;

                $scope.query = function () {
                    $scope.queryMethod();
                    var totalPage = $scope.pageInfo.totalRecord == 0 ? 1 : Math.ceil($scope.pageInfo.totalRecord / $scope.pageInfo.pageSize);
                    if ($scope.pageInfo.currentPage > totalPage) {
                        $scope.pageInfo.currentPage = totalPage;
                    }
                    if ($scope.pageInfo.currentPage < 1) {
                        $scope.pageInfo.currentPage = 1;
                    }
                    $scope.jumpPage = $scope.pageInfo.currentPage;
                };
                $scope.jumpToPage = function (event) {
                    if (event.keyCode != 13) {
                        return;
                    }
                    $scope.pageInfo.currentPage = $scope.jumpPage;
                    $scope.query();
                };
            },
        };
    })
    .directive('appPagebar', function () {
        var template = [];
        template.push('<div class="acp-card-pagination">');
        template.push('<div class="pagination col-md-9 col-sm-8 col-xs-8">');
        template.push('<pagination class="pagination-sm" ng-model="pageInfo.currentPage" num-pages="numPages" total-items="pageInfo.totalRecord" max-size="5" items-per-page="pageInfo.pageSize" ng-change="query()" previous-text="上一页" next-text="下一页" first-text="首页" last-text="末页" boundary-links="true"></pagination>');
        template.push('</div>');
        template.push('<div class="pageInfo col-md-3 col-sm-4 col-xs-4">');
        template.push('<span class="curry-page-panel">第 <input class="curry-page-ipt" ng-model="jumpPage" type="text" ng-keyup="jumpToPage($event);"/> 页</span>');
        template.push('<span class="page-size-panel">每页 <select class="page-size-select" ng-model="pageInfo.pageSize" ng-options="option as option for option in pageOptions" ng-change="query()"></select> 条</span>');
        template.push('<span class="total-items-panel">共 </span><span class="total-items-panel" ng-bind="pageInfo.totalRecord"></span><span class="total-items-panel"> 条</span>');
        template.push('</div>');
        template.push('</div>');
        return {
            replace: true,
            restrict: 'E',
            priority: 0,
            template: template.join(''),
            scope: {
                pageOptions: '=',
                pageInfo: '=',
                queryMethod: '&'
            },
            controller: function ($scope) {
                $scope.pageForIndex = 1;
                $scope.jumpPage = 1;
                $scope.query = function () {
                    $scope.queryMethod();
                    var totalPage = $scope.pageInfo.totalRecord == 0 ? 1 : Math.ceil($scope.pageInfo.totalRecord / $scope.pageInfo.pageSize);
                    if ($scope.pageInfo.currentPage > totalPage) {
                        $scope.pageInfo.currentPage = totalPage;
                    }
                    if ($scope.pageInfo.currentPage < 1) {
                        $scope.pageInfo.currentPage = 1;
                    }
                    $scope.jumpPage = $scope.pageInfo.currentPage;
                };
                $scope.jumpToPage = function (event) {
                    if (event.keyCode != 13) {
                        return;
                    }
                    $scope.pageInfo.currentPage = $scope.jumpPage;
                    $scope.query();
                };
            },
        };
    })
    /**
     * 弹窗顶部样式
     * 陈星伯
     * 2016-05-12
     *
     * @Param cancel-method: 关闭弹窗时调用的方法
     *
     * @Param modal-title: 弹窗标题
     */
    .directive('acpTopBar', function () {
        var template = [];
        template.push('<div class="panel-acpgreen" style="height: 30px;">');
        template.push('<span ng-bind="modalTitle" style="display:inline-block;margin:5px 10px;"></span>');
        template.push('<a ng-click="cancelMethod()" style="float: right"><img src="img/icon_close.png"></a>');
        template.push('</div>');

        return {
            replace: true,
            restrict: 'E',
            priority: 0,
            template: template.join(''),
            scope: {
                cancelMethod: '&',
                modalTitle: '='
            },
            link: function (scope, element, attr, ctrl) {
                scope.modalTitle = ' > ' + scope.modalTitle;
            }
        };
    })
    /**
     * 格式化日期为 ‘yyyy-MM-dd’
     */
    .directive('acpDateFormat', ['$filter', function ($filter) {
        var dateFilter = $filter('date');
        return {
            require: 'ngModel',
            link: function (scope, elm, attrs, ctrl) {

                function formatter(value) {
                    return dateFilter(value, 'yyyy-MM-dd'); //format
                }

                function parser() {
                    return ctrl.$modelValue;
                }

                ctrl.$formatters.push(formatter);
                ctrl.$parsers.unshift(parser);
            }
        };
    }])
    /**
     * 格式化日期时间为 ‘yyyy-MM-dd HH:mm’
     */
    .directive('acpDateTimeFormat', ['$filter', function ($filter) {
        var dateFilter = $filter('date');
        return {
            require: 'ngModel',
            link: function (scope, elm, attrs, ctrl) {

                function formatter(value) {
                    return dateFilter(value, 'yyyy-MM-dd HH:mm'); //format
                }

                function parser() {
                    return ctrl.$modelValue;
                }

                ctrl.$formatters.push(formatter);
                ctrl.$parsers.unshift(parser);
            }
        };
    }])
    /**
     * 格式化日期时间为 ‘yyyy-MM-dd HH:mm’
     */
    .directive('acpTimeFormat', ['$filter', function ($filter) {
        var dateFilter = $filter('date');
        return {
            require: 'ngModel',
            link: function (scope, elm, attrs, ctrl) {

                function formatter(value) {
                    return dateFilter(value, 'HH:mm'); //format
                }

                function parser() {
                    return ctrl.$modelValue;
                }

                ctrl.$formatters.push(formatter);
                ctrl.$parsers.unshift(parser);
            }
        };
    }])
/**
 * 构造菜单树
 */
/* .directive('acpMenu',function(){
 var templateItem = [];
 templateItem.push('<i ng-click="itemExpended(item, $event);" class=""></i>');
 templateItem.push('<input type="checkbox" ng-model="item.$$isChecked" class="check-box" ng-if="canChecked" ng-change="warpCallback(\'itemCheckedChanged\', item, $event)">');
 templateItem.push('<span class=\'text-field\' ng-click="warpCallback(\'itemClicked\', item, $event);"></span>');
 templateItem.push('<ul ng-if="!isLeaf(item)" ng-show="item.$$isExpend"> <li ng-repeat="item in item.children" ng-include="\'/treeItem.html\'"> </li></ul>');
 var templateMemu = [];
 templateMemu.push('<ul class="tree-view"><li ng-repeat="item in treeData" ng-include=templateItem.join(\'\')></li></ul>');
 return {
 restrict: 'E',
 template: templateMemu.join(''),
 scope: {
 treeData: '='/!*,
 canChecked: '=',
 textField: '@',
 itemClicked: '&',
 itemCheckedChanged: '&',
 itemTemplateUrl: '@'*!/
 },
 controller:['$scope', function($scope){
 $scope.itemExpended = function(item, $event){
 item.$$isExpend = ! item.$$isExpend;
 $event.stopPropagation();
 };

 $scope.getItemIcon = function(item){
 var isLeaf = $scope.isLeaf(item);

 if(isLeaf){
 return 'fa fa-leaf';
 }

 return item.$$isExpend ? 'fa fa-minus': 'fa fa-plus';
 };

 $scope.isLeaf = function(item){
 return !item.children || !item.children.length;
 };

 $scope.warpCallback = function(callback, item, $event){
 ($scope[callback] || angular.noop)({
 $item:item,
 $event:$event
 });
 };
 }]
 };


 })*/
;