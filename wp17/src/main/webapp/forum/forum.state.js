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
            .state('forum', {
                    url: '/forum',
                    views: {
                        'content@': {
                            templateUrl: 'forum/forum.html',
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
