/**
 * Created by Imola on 6/1/2017.
 */

(function() {
    'use strict';

    angular
        .module('bpm_app')
        .controller('TopicDetailController', TopicDetailController);

    TopicDetailController.$inject = ['$window','$scope', '$state', 'AuthService', '$rootScope', '$stateParams', 'TopicService','CommentService'];

    function TopicDetailController($window, $scope, $state, AuthService, $rootScope, $stateParams, TopicService, CommentService) {
        TopicService.getTopic($stateParams.subforum, $stateParams.name,
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



        $scope.add= function(){
            alert($scope.commentcontent);

            var comment= {topic:$stateParams.name,
                            content:$scope.commentcontent}

            CommentService.writeComment(comment,
                function(response){
                    /*if(!response.data.success){
                     $state.go('home');
                     }else{*/
                    //console.log(response.data);
                    //}
                }/*,
                 function(response){
                 $state.go('home');
                 }*/);

            $window.location.reload();
        }

        $scope.likedTopic= function(){
            var like= "like";
            var obj={subForum: $stateParams.subforum, name: $stateParams.name, rateType: like};
            TopicService.giveRating(obj,
                function(response){
                    /*if(!response.data.success){
                     $state.go('home');
                     }else{*/
                    //console.log(response.data);
                    //}
                }/*,
                 function(response){
                 $state.go('home');
                 }*/);

            $window.location.reload();
        }

        $scope.dislikedTopic= function() {
            var dislike= "dislike";
            var obj={name: $stateParams.name, rateType: dislike};
            TopicService.giveRating(obj,
                function(response){
                    /*if(!response.data.success){
                     $state.go('home');
                     }else{*/
                    //console.log(response.data);
                    //}
                }/*,
                 function(response){
                 $state.go('home');
                 }*/);

            $window.location.reload();

        }

        $scope.likedComment= function(){
            alert("like!");
        }

        $scope.dislikedComment= function() {
            alert("dislike!");

        }

    }
})();
