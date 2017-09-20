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
	$scope.showUpload=false;
	$scope.showText=true;
	
	AuthService.getLoggedUser(
            function(response){
                    $scope.logged=response.data;
                    $scope.topic.author=$scope.logged.username;
            }
            );
	
	$scope.topic={};
        
        $scope.topic.title="";
        $scope.topic.content="";
        $scope.topic.subForum=$stateParams.name;
        $scope.fileURL="";
	
    $scope.topicTypes=["TEXT", "IMAGE", "LINK"];
    
    $scope.changeInTypes= function(){
    	
    	if($scope.selectedTopicType=="IMAGE"){
    		$scope.showUpload=true;
    		$scope.showText=false;
    	}else{
    		$scope.showUpload=false;
			$scope.showText=true;
    	}
    }
    
    $scope.addImage = function() {
    	alert(document.getElementById("file").value);
	    $scope.topic.content=document.getElementById("file").value;
	}


        $scope.addTopic = function(){
			$scope.topic.type=$scope.selectedTopicType;
            TopicService.addTopic(
                $scope.topic,
                function(res){
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
