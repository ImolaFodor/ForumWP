/**
 * Created by Imola on 6/1/2017.
 */

(function() {
    'use strict';

    angular
        .module('angular')
        .controller('HomeController', HomeController);

    HomeController.$inject = ['$scope','$window','$location', '$state', 'ForumService'];

    function HomeController ($scope,$window, $location, $state, ForumService) {
        $scope.query = {}
        $scope.queryBy = '$'

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




    }

})();