/**
 * Created by Imola on 6/1/2017.
 */

(function() {
    'use strict';

    angular
        .module('bpm_app')
        .controller('HomeController', HomeController);

    HomeController.$inject = ['$scope', '$state', 'ForumService'];

    function HomeController ($scope, $state, ForumService) {


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




    }

})();