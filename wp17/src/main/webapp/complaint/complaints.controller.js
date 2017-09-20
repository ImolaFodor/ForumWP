/**
 * Created by Imola on 6/30/2017.
 */

(function() {
    'use strict';

    angular
        .module('angular')
        .controller('ComplaintController', ComplaintController);

    ComplaintController.$inject = ['$scope','$location', '$state', 'ComplaintService', 'ngDialog','$rootScope'];

    function ComplaintController($scope,$location, $state, ComplaintService, ngDialog, $rootScope) {

        $scope.subforum={};
        $scope.subforum.responsibleModerator="";
        $scope.subforum.name="";
        $scope.subforum.description="";

        $scope.addSubForum = function(){

            ComplaintService.addComplaint(
                $scope.subforum,
                function(res){
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

        ComplaintService.getComplaints(
            function(response){
                /*if(!response.data.success){
                 $state.go('home');
                 }else{*/
                console.log(response.data);
                $scope.complaints=response.data;
                //}
            }/*,
             function(response){
             $state.go('home');
             }*/);

        /*$scope.start = function(){
         $state.go('process');
         }*/

        $scope.seeContent=function(content){
            $scope.parentcontent=content;

            ngDialog.open({
                template: 'complaint/complaint.html',
                scope: $scope,
                controller: ['$scope', function($scope) {
                    $scope.content=$scope.$parent.parentcontent;
                    /*$scope.subject=$scope.$parent.message.subject;
                     $scope.recipient=$scope.$parent.message.recipient;*/
                }]
            });
        }

    }
})();
