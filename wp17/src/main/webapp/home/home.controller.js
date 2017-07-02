/**
 * Created by Imola on 6/1/2017.
 */

(function() {
    'use strict';

    angular
        .module('angular')
        .controller('HomeController', HomeController);

    HomeController.$inject = ['$scope','$window','$location', '$state', 'ForumService','ComplaintService','AuthService'];

    function HomeController ($scope,$window, $location, $state, ForumService,ComplaintService, AuthService) {
        $scope.query = {}
        $scope.queryBy = '$'
        
        AuthService.getLoggedUser(
            function(response){
                /*if(!response.data.success){
                    $state.go('home');
                }else{*/
                    console.log(response.data);
                    $scope.logged=response.data;
                //}
            }/*,
            function(response){
                $state.go('home');
            }*/);

        /*$scope.start = function(){
            $state.go('process');
        }*/
        

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
            $scope.complaint.name= name;
            $scope.complaint.complainer= "ulogovanikorisnik";
            $scope.complaint.content= content;
            $scope.complaint.id= 1;



            ComplaintService.addComplaint($scope.complaint,
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




    }

})();