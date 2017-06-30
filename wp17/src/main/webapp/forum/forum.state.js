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
            .state('newsubforum', {
                    url: '/newforum',
                    views: {
                        'content@': {
                            templateUrl: 'forum/newforum.html',
                            controller: 'ForumController'
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
