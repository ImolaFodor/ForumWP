/**
 * Created by Imola on 6/29/2017.
 */
/**
 * Created by Imola on 6/1/2017.
 */

(function() {
    'use strict';

    angular
        .module('bpm_app')
        .controller('UserController', UserController);

    UserController.$inject = ['$scope','$window','$location', '$state', 'UserService'];

    function UserController ($scope,$window, $location, $state, UserService) {


        UserService.getUsers(
            function(response){
                /*if(!response.data.success){
                 $state.go('home');
                 }else{*/
                console.log(response.data);
                $scope.users=response.data;
                //}
            }/*,
             function(response){
             $state.go('home');
             }*/);

        /*$scope.start = function(){
         $state.go('process');
         }*/

        $scope.sendMessage= function() {

            ngDialog.open({
                template: 'message/newmessage.html',
                className: 'ngdialog-theme-plain',
                scope: $scope
            });

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