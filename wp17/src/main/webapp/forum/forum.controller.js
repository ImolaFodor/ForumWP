/**
 * Created by Imola on 6/3/2017.
 */

(function() {
    'use strict';

    angular
        .module('angular')
        .controller('ForumController', ForumController);

    ForumController.$inject = ['$scope','$location', '$state', 'ForumService', 'AuthService','UserService','$rootScope'];

    function ForumController($scope,$location, $state, ForumService, AuthService, UserService, $rootScope) {

        $scope.subforum={};
        
        $scope.subforum.name="";
        $scope.subforum.description="";
        
        UserService.getModerators(
            function(response){
                /*if(!response.data.success){
                    $state.go('home');
                }else{*/
                    console.log(response.data);
                    $scope.moderators=response.data;
                //}
            }/*,
            function(response){
                $state.go('home');
            }*/);
        
        AuthService.getLoggedUser(
            function(response){
                /*if(!response.data.success){
                    $state.go('home');
                }else{*/
                    console.log(response.data);
                    $scope.logged=response.data;
                    $scope.subforum.responsibleModerator=$scope.logged.username;
                //}
            }/*,
            function(response){
                $state.go('home');
            }*/);
		
		
		
        $scope.addSubForum = function(){
        
        $scope.subforum.responsibleModerator=$scope.selectedModerator.username;

            ForumService.addSubForum(
                $scope.subforum,
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
                    alert("Podforum je dodat!");
                    $location.path("home/");
                },
                function(res){
                    if(res.data){
                        alert("Postoji podforum sa unetim nazivom!");
                    }
                    console.log(res);
                });
        }


    }
})();
