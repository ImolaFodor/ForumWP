/**
 * Created by Imola on 6/1/2017.
 */

(function() {
    'use strict';

    angular
        .module('bpm_app')
        .controller('TopicDetailController', TopicDetailController);

    TopicDetailController.$inject = ['$scope', '$state', 'AuthService', '$rootScope', '$stateParams', 'TopicService','CommentService'];

    function TopicDetailController($scope, $state, AuthService, $rootScope, $stateParams, TopicService, CommentService) {
        TopicService.getTopic($stateParams.name,
            function(response){
                /*if(!response.data.success){
                 $state.go('home');
                 }else{*/
                console.log(response.data);
                $scope.topicdetail=response.data;
                //}
            }/*,
             function(response){
             $state.go('home');
             }*/);

        CommentService.getComments($stateParams.name,
            function(response){
                /*if(!response.data.success){
                 $state.go('home');
                 }else{*/
                console.log(response.data);
                $scope.comments=response.data;
                //}
            }/*,
             function(response){
             $state.go('home');
             }*/);

    }
})();
