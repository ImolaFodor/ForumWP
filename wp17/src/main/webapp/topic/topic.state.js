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
                    url: '/topic/{name}',
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
            .state('topicdetail', {
                    url: '/topicdetail/{subforum}/{name}',
                    views: {
                        'content@': {
                            templateUrl: 'topic/topicdetail.html',
                            controller: 'TopicDetailController'
                        },
                        'navbar':{
                            templateUrl: 'navbar/navbar.html',
                            controller: 'NavbarController'
                        }
                    }
                }
            )
            .state('newtopic', {
                    url: '/newtopic/{name}',
                    views: {
                        'content@': {
                            templateUrl: 'topic/newtopic.html',
                            controller: 'NewTopicController'
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
