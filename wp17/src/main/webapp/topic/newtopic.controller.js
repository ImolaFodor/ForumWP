/**
 * Created by Imola on 6/28/2017.
 */

(function() {
    'use strict';

    angular
        .module('angular')
        .controller('NewTopicController', NewTopicController);

    NewTopicController.$inject = ['$window', '$location','$scope', '$state', 'AuthService', '$rootScope', '$stateParams', 'TopicService','CommentService'];

    function NewTopicController($window, $location, $scope, $state, AuthService, $rootScope, $stateParams, TopicService, CommentService) {

    $scope.topicTypes=["TEXT", "IMAGE", "LINK"];

    $scope.topic={};
        $scope.topic.author="";
        $scope.topic.type="";
        $scope.topic.title="";
        $scope.topic.content="";
        $scope.topic.subForum=$stateParams.name;

        $scope.addTopic = function(){

            TopicService.addTopic(
                $scope.topic,
                function(res){
                    // AuthService.saveToStorage("token", res.data.token);
                    // AuthService.setHeader();
                    // AuthService.me(
                    //     function(res){
                    //         $rootScope.user = res.data.user;
                    //     },
                    //     function(res){
                    //
                    //     });
                    alert("Tema je dodata!");
                    $location.path("topic/"+$stateParams.name);
                },
                function(res){
                    if(res.data){
                        alert("Postoji tema sa unetim nazivom!");
                    }
                    console.log(res);
                });
        }



    }
})();
