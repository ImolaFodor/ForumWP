/**
 * Created by Imola on 6/30/2017.
 */

(function() {
    'use strict';

    angular
        .module('angular')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider', '$urlRouterProvider'];

    function stateConfig($stateProvider, $urlRouterProvider) {

        $stateProvider
            .state('complaints', {
                    url: '/complaints',
                    views: {
                        'content@': {
                            templateUrl: 'complaint/complaints.html',
                            controller: 'ComplaintController'
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
