/**
 * Created by Imola on 6/1/2017.
 */

(function() {
    'use strict';

    angular
        .module('bpm_app')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider', '$urlRouterProvider'];

    function stateConfig($stateProvider, $urlRouterProvider) {

        $stateProvider
            .state('login', {
                url: '/login',
                views: {
                    'content@': {
                        templateUrl: 'auth/login.html',
                        controller: 'LoginController'
                    },
                    'navbar':{
                        templateUrl: 'navbar/navbar.html',
                        controller: 'NavbarController'
                    }
                }
            }
            )
            .state('signup', {
                    url: '/signup',
                    views: {
                        'content@': {
                            templateUrl: 'auth/signup.html',
                            controller: 'SignUpController'
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
