/**
 * Created by Imola on 6/1/2017.
 */

(function() {
    'use strict';

    angular
        .module('angular')
        .controller('HomeController', HomeController);

    HomeController.$inject = ['$scope','$window','$location', '$state', 'ForumService','ComplaintService','AuthService','ngDialog'];

    function HomeController ($scope,$window, $location, $state, ForumService,ComplaintService, AuthService,ngDialog) {
        $scope.query = {}
        $scope.queryBy = '$'
        $scope.showSFOptions=false;
        $scope.showSFOptionDelete=false;
        $scope.showAddSubForum=false;
        
        AuthService.getLoggedUser(
            function(response){
                /*if(!response.data.success){
                    $state.go('home');
                }else{*/
                    console.log(response.data);
                    $scope.logged=response.data;
                    $scope.followedSubForums=[];
                    
                   var i = 0;
					var len = $scope.logged.followedSubForums.length;
					for (; i < len; ) { 
    				$scope.followedSubForums.push($scope.logged.followedSubForums[i]);
    				i++;
					}
                    
                    console.log($scope.followedSubForums);
                    
                    if($scope.logged.username){
        				$scope.showFollowedSubForums=true;
        				$scope.showPleaseLogIn=false;
        				$scope.showSFOptions=true;
                    }
                    
                    if($scope.logged.role=="ADMIN"){
                    	$scope.showAddSubForum=true;
                    	$scope.showSFOptionDelete=true;
                    }
                     
                //}
            }/*,
            function(response){
                $state.go('home');
            }*/);
            
        $scope.showPleaseLogIn=true;
        
        $scope.showFollowedSubForums=false;
        
        

        ForumService.getSubForums(
            function(response){
                /*if(!response.data.success){
                    $state.go('home');
                }else{*/
                    console.log(response.data);
                    $scope.subforums=response.data;
                //}
            }/*,
            function(response){
                $state.go('home');
            }*/);

        /*$scope.start = function(){
            $state.go('process');
        }*/

        $scope.deleteSubForum= function(name) {

            ForumService.deleteSubForum(name,
                function(response){
                    /*if(!response.data.success){
                     $state.go('home');
                     }else{*/
                    //console.log(response.data);
                    //}

                    alert("Izbrisan podforum!");
                }/*,
                 function(response){
                 $state.go('home');
                 }*/);
            $window.location.reload();
        }

        $scope.complainOnSubForum= function(name, content) {

            $scope.complaint={};
            $scope.complaint.subforum= name;
            $scope.complaint.name= name;
            $scope.complaint.type= "subforum";
            
            //$scope.complaint.complainer= "ulogovanikorisnik";

            ngDialog.open({
                template: 'complaint/complaint.html',
                scope: $scope,
                controller: ['$scope', 'AuthService', function($scope, $AuthService) {
                 $scope.confirmSendComplaint= function(content){
                     $scope.$parent.complaint.content=content;
                     
                     AuthService.getLoggedUser(
            				function(response){
                    			console.log(response.data);
                    			$scope.logged=response.data;
           					});
                     
                     $scope.$parent.complaint.complainer= $scope.logged.username;
                     

                     ComplaintService.addComplaint($scope.$parent.complaint,
                function(response){
                    /*if(!response.data.success){
                     $state.go('home');
                     }else{*/
                    //console.log(response.data);
                    //}

                    alert("Ulozena zalba!");
                }/*,
                 function(response){
                 $state.go('home');
                 }*/);
            $window.location.reload();
        }
            }]
            });



            //$window.location.reload();
        }



            

		$scope.followSubForum= function(name) {

            ForumService.followSubForum(name,
                function(response){
                    /*if(!response.data.success){
                     $state.go('home');
                     }else{*/
                    //console.log(response.data);
                    //}

                    alert("Zapracen podforum!");
                }/*,
                 function(response){
                 $state.go('home');
                 }*/);
            $window.location.reload();
        }


    }

})();