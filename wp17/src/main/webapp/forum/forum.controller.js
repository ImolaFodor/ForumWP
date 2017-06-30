/**
 * Created by Imola on 6/3/2017.
 */

/**
 * Created by Imola on 6/1/2017.
 */

(function() {
    'use strict';

    angular
        .module('angular')
        .controller('ForumController', ForumController);

    ForumController.$inject = ['$scope','$location', '$state', 'ForumService', '$rootScope'];

    function ForumController($scope,$location, $state, ForumService, $rootScope) {

        $scope.subforum={};
        $scope.subforum.responsibleModerator="";
        $scope.subforum.name="";
        $scope.subforum.description="";

        $scope.addSubForum = function(){

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
