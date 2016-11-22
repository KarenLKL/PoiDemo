'use strict';

/**
 * Config for the router
 */
angular.module('app')
    .run(
        ['$rootScope', '$state', '$stateParams',
            function ($rootScope, $state, $stateParams) {
                $rootScope.$state = $state;
                $rootScope.$stateParams = $stateParams;
            }
        ]
    )
    .config(
        ['$stateProvider', '$urlRouterProvider', '$locationProvider',
            function ($stateProvider, $urlRouterProvider, $locationProvider) {
                $urlRouterProvider.otherwise('/app');//默认界面

                $stateProvider
                //主页面路由
                .state('app', {
                    url: '/app',
                    templateUrl: 'template/app.html',
                    resolve: {
                        deps: ['$ocLazyLoad',
                            function ($ocLazyLoad) {
                                return $ocLazyLoad.load(['js/controller/nav.js', 'js/controller/header.js', 'js/services/MainService.js']);
                            }]
                    }
                })
                    .state('login', {
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
                    .state('app.user', {
                        url: '/system/user',
                        templateUrl: 'function/system/user/user.html',
                        resolve: {
                            deps: ['$ocLazyLoad',
                                function ($ocLazyLoad) {
                                    return $ocLazyLoad.load([
                                        'function/system/user/user.css',
                                        'function/system/user/UserController.js',
                                        'function/system/user/UserService.js'
                                    ])
                                }]
                        }
                    })
                    .state('app.authority', {
                        url: '/system/authority',
                        templateUrl: 'function/system/authority/authority.html',
                        resolve: {
                            deps: ['$ocLazyLoad',
                                function ($ocLazyLoad) {
                                    return $ocLazyLoad.load([
                                        'function/system/authority/authority.css',
                                        'function/system/authority/AuthorityController.js',
                                        'function/system/authority/AuthorityServicer.js'
                                    ])
                                }]
                        }
                    })
                    .state('app.role', {
                        url: '/system/role',
                        templateUrl: 'function/system/role/role.html',
                        resolve: {
                            deps: ['$ocLazyLoad',
                                function ($ocLazyLoad) {
                                    return $ocLazyLoad.load([
                                        'function/system/role/role.css',
                                        'function/system/role/RoleController.js',
                                        'function/system/role/RoleService.js'
                                    ])
                                }]
                        }
                    })
                    .state('app.resources', {
                        url: '/system/resources',
                        templateUrl: 'function/system/resources/resources.html',
                        resolve: {
                            deps: ['$ocLazyLoad',
                                function ($ocLazyLoad) {
                                    return $ocLazyLoad.load([
                                        'function/system/resources/resources.css',
                                        'function/system/resources/ResourcesController.js',
                                        'function/system/resources/ResourcesService.js'
                                ])
                            }]
                    }
                })
        }]
    );
