/**
 * Created by Imola on 6/1/2017.
 */

(function() {
    'use strict';

    angular
        .module('angular')
        .controller('TopicDetailController', TopicDetailController);

    TopicDetailController.$inject = ['$window','$location','$scope', '$state', 'AuthService', '$rootScope', '$stateParams', 'TopicService','CommentService'];

    function TopicDetailController($window, $location, $scope, $state, AuthService, $rootScope, $stateParams, TopicService, CommentService) {

        $scope.isDisabled=true;
        $scope.showOkButton=false;
        $scope.showEditContent=false;
        $scope.showContent=true;
        $scope.showImageContent=false;
        $scope.showEditTopicButton=true;
        $scope.showConfirmEditButton=false;
        
        
        AuthService.getLoggedUser(
            function(response){
                /*if(!response.data.success){
                    $state.go('home');
                }else{*/
                    console.log(response.data);
                    $scope.logged=response.data;
                    
                    if($scope.logged.username){
        				$scope.disableTopicLike=false;
        				$scope.disableTopicDislike=false;
        				$scope.disableCommentLike=false;
        				$scope.disableCommentDislike=false;
                    }else{
                    	$scope.showAddComment=true;
        				$scope.disableTopicLike=true;
        				$scope.disableTopicDislike=true;
        				$scope.disableCommentLike=true;
        				$scope.disableCommentDislike=true;
                    }
                     
                //}
            }/*,
            function(response){
                $state.go('home');
            }*/);
            
        $scope.showAddComment=false;

        TopicService.getTopic($stateParams.subforum, $stateParams.name,
            function(response){
                /*if(!response.data.success){
                 $state.go('home');
                 }else{*/
                console.log(response.data);
                $scope.topicdetail=response.data;
                
                if($scope.topicdetail.type=="IMAGE"){
                	$scope.showContent=false;
                	$scope.showLinkContent=false;
                		$scope.showImageContent=true;
                	var str1 = "file:///";
					var str2 = $scope.topicdetail.content;
					$scope.img_source = str1.concat(str2);
                	}
                	if($scope.topicdetail.type=="LINK")
                	{
                	$scope.showContent=false;
                	$scope.showImageContent=false;
                		$scope.showLinkContent=true;
                	var str3= "https://";
                	var str4= $scope.topicdetail.content;
                	$scope.linkContent=str3.concat(str4);
                	}
                
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
                	TopicService.getTopic($stateParams.subforum, $stateParams.name,
            function(response){
                /*if(!response.data.success){
                 $state.go('home');
                 }else{*/
                console.log(response.data);
                $scope.topicdetail=response.data;
                
                if($scope.topicdetail.type=="IMAGE"){
                	$scope.showContent=false;
                	$scope.showLinkContent=false;
                		$scope.showImageContent=true;
                	var str1 = "file:///";
					var str2 = $scope.topicdetail.content;
					$scope.img_source = str1.concat(str2);
                	}
                	if($scope.topicdetail.type=="LINK")
                	{
                	$scope.showContent=false;
                	$scope.showImageContent=false;
                		$scope.showLinkContent=true;
                	var str3= "https://";
                	var str4= $scope.topicdetail.content;
                	$scope.linkContent=str3.concat(str4);
                	}
                
                //}
            }/*,
             function(response){
             $state.go('home');
             }*/);
                
                
                    /*if(!response.data.success){
                     $state.go('home');
                     }else{*/
                    //console.log(response.data);
                    //}
                }/*,
                 function(response){
                 $state.go('home');
                 }*/);
                 
             $scope.disableTopicLike=true;

            //$window.location.reload();
        }

        $scope.dislikedTopic= function() {
            var dislike= "dislike";
            var obj={name: $stateParams.name, rateType: dislike};
            TopicService.giveRating(obj,
                function(response){
                	TopicService.getTopic($stateParams.subforum, $stateParams.name,
            function(response){
                /*if(!response.data.success){
                 $state.go('home');
                 }else{*/
                console.log(response.data);
                $scope.topicdetail=response.data;
                
                if($scope.topicdetail.type=="IMAGE"){
                	$scope.showContent=false;
                	$scope.showLinkContent=false;
                		$scope.showImageContent=true;
                	var str1 = "file:///";
					var str2 = $scope.topicdetail.content;
					$scope.img_source = str1.concat(str2);
                	}
                	if($scope.topicdetail.type=="LINK")
                	{
                	$scope.showContent=false;
                	$scope.showImageContent=false;
                		$scope.showLinkContent=true;
                	var str3= "https://";
                	var str4= $scope.topicdetail.content;
                	$scope.linkContent=str3.concat(str4);
                	}
                
                //}
            }/*,
             function(response){
             $state.go('home');
             }*/);
                
          
                }/*,
                 function(response){
                 $state.go('home');
                 }*/);
                 
            $scope.disableTopicDislike=true;

            //$window.location.reload();

        }

        $scope.likedComment= function(id){
            var like= "like";
            var obj={subForum: $stateParams.subforum, topic: $stateParams.name, rateType: like, id: id};
             CommentService.giveRating(obj,
             function(response){
             
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
             
             /*if(!response.data.success){
             $state.go('home');
             }else{*/
             //console.log(response.data);
             //}
             }/*,
             function(response){
             $state.go('home');
             }*/);
				
			$scope.disableCommentLike=true;
				
             //$window.location.reload();
        }

        $scope.dislikedComment= function(id) {
            var dislike= "dislike";
            var obj={topic: $stateParams.name, rateType: dislike, id: id};
            CommentService.giveRating(obj,
                function(response){
                
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
                
                    /*if(!response.data.success){
                     $state.go('home');
                     }else{*/
                    //console.log(response.data);
                    //}
                }/*,
                 function(response){
                 $state.go('home');
                 }*/);
			$scope.disableCommentDislike=true;
			
            //$window.location.reload();

        }

        $scope.deleteComment= function(id) {
            var obj={topic: $stateParams.name,id: id};
            CommentService.deleteComment(obj,
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

        $scope.editComment= function() {
            $scope.isDisabled=false;
            $scope.showOkButton=true;

        }

        $scope.confirmEditComment= function(id, newContent) {
            var obj={topic: $stateParams.name,id: id, content: newContent};
             CommentService.editComment(obj,
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

        $scope.editTopic= function() {

            $scope.showEditContent=true;
            $scope.showContent=false;
            $scope.showConfirmEditButton=true;
            $scope.showEditTopicButton=false;


        }

        $scope.confirmEditTopic= function() {

            var obj={name: $stateParams.name, content: $scope.topicdetail.content};

            TopicService.editTopic(obj,
             function(response){
             /*if(!response.data.success){
             $state.go('home');
             }else{*/
             //console.log(response.data);
             //}

                 console.log(response.data);
                 $scope.topicdetail=response.data;
             }/*,
             function(response){
             $state.go('home');
             }*/);
             $window.location.reload();
        }
        
        $scope.saveTopic= function() {

            var obj={name: $stateParams.name, content: $scope.topicdetail.content};

            TopicService.saveTopic(obj,
             function(response){
             /*if(!response.data.success){
             $state.go('home');
             }else{*/
             //console.log(response.data);
             //}

                 console.log(response.data);
                 $scope.topicdetail=response.data;
             }/*,
             function(response){
             $state.go('home');
             }*/);
             $window.location.reload();
        }

        $scope.deleteTopic= function() {

            var obj={name: $stateParams.name, subforum: $stateParams.subforum};

            TopicService.deleteTopic(obj,
                function(response){
                    /*if(!response.data.success){
                     $state.go('home');
                     }else{*/
                    //console.log(response.data);
                    //}

                    alert("Izbrisana tema!");
                }/*,
                 function(response){
                 $state.go('home');
                 }*/);
            $location.path("/topic"+$stateParams.subforum + "/" + $stateParams.name)
        }





    }
})();
