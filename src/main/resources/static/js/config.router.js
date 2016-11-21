/**
 * Created by Major on 2016/11/21.
 */
angular.module('app')
    .run(
        ['$rootScope', '$state', '$stateParams',
            function ($rootScope, $state, $stateParams) {
                $rootScope.state = $state;
                $rootScope.$stateful = $stateParams;
            }]
    )
    .config(['$stateProvider', '$urlRouterProvider', '$locationProvider',
        function ($stateProvider, $urlRouterProvider, $locationProvider) {
            $stateProvider
            //主页面路由
                .state('app', {
                    url: '/app',
                    templateUrl: 'template/app.html',
                    resolve: {
                        deps: ['$ocLazyLoad',
                            function ($ocLazyLoad) {
                                return $ocLazyLoad.load(['js/controllers/nav.js', 'js/controllers/header.js']);
                            }]
                    }
                })
                .state('app.login', {
                    url: '/login',
                    templateUrl: 'function/login/login.html',
                    resolve: {
                        deps: ['$ocLazyLoad',
                            function ($ocLazyLoad) {
                                return $ocLazyLoad.load([
                                    'function/login/LoginController.js',
                                    'function/login/LoginCss.css'
                                ])
                            }]
                    }
                })
        }]
    );
