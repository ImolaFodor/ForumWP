/**
 * Created by Imola on 6/1/2017.
 */

(function() {
    'use strict';

    angular
        .module('angular')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider', '$urlRouterProvider'];

    function stateConfig($stateProvider, $urlRouterProvider, $translateProvider) {

        $urlRouterProvider.otherwise('/home');

        $stateProvider
            .state('home', {
                url: '/home',
                views: {
                    'content': {
                        templateUrl: 'home/home.html',
                        controller: 'HomeController'
                    },
                    'navbar':{
                        templateUrl: 'navbar/navbar.html',
                        controller: 'NavbarController'
                    }
                }
            });

    }


})();