/**
 * Created by Imola on 6/3/2017.
 */

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
            .state('topic', {
                    url: '/topic/{id}',
                    views: {
                        'content@': {
                            templateUrl: 'topic/topics.html',
                            controller: 'TopicController'
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
