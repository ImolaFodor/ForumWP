/**
 * Created by Imola on 6/29/2017.
 */
/**
 * Created by Imola on 6/1/2017.
 */

(function() {
    'use strict';

    angular
        .module('angular')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider', '$urlRouterProvider'];

    function stateConfig($stateProvider, $urlRouterProvider) {

        $stateProvider
            .state('users', {
                    url: '/users',
                    views: {
                        'content@': {
                            templateUrl: 'user/users.html',
                            controller: 'UserController'
                        },
                        'navbar':{
                            templateUrl: 'navbar/navbar.html',
                            controller: 'NavbarController'
                        }
                    }
                }
            )
            .state('userprofile', {
                    url: '/userprofile',
                    views: {
                        'content@': {
                            templateUrl: 'user/profile.html',
                            controller: 'UserController'
                        },
                        'navbar':{
                            templateUrl: 'navbar/navbar.html',
                            controller: 'NavbarController'
                        }
                    }
                }
            )
    }

})();
