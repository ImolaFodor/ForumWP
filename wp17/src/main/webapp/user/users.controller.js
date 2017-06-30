/**
 * Created by Imola on 6/29/2017.
 */
/**
 * Created by Imola on 6/1/2017.
 */

(function() {
    'use strict';

    angular
        .module('angular')
        .controller('UserController', UserController);

    UserController.$inject = ['$scope','$window','$location', '$state', 'UserService','MessageService', 'ngDialog'];

    function UserController ($scope,$window, $location, $state, UserService, MessageService, ngDialog) {
        $scope.value = true;
        $scope.message={};
        $scope.message.subject=" ";
        $scope.message.content=" ";


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

        MessageService.getMessages(
            function(response){
                /*if(!response.data.success){
                 $state.go('home');
                 }else{*/
                console.log(response.data);
                $scope.messages=response.data;
                //}
            }/*,
             function(response){
             $state.go('home');
             }*/);

        /*$scope.start = function(){
         $state.go('process');
         }*/

        $scope.seeMessage= function(subject,content,recipient){

            $scope.message.recipient=recipient;
            $scope.message.subject=subject;
            $scope.message.content=content;

            ngDialog.open({
                template: 'message/message.html',
                scope: $scope,
                controller: ['$scope', function($scope) {
                    $scope.content=$scope.$parent.message.content;
                    $scope.subject=$scope.$parent.message.subject;
                    $scope.recipient=$scope.$parent.message.recipient;
            }]
            });
        }

        $scope.sendMessage= function() {

            ngDialog.open({
                template: 'message/newmessage.html',
                scope: $scope,
                controller: ['$scope', function($scope) {
                 $scope.confirmSendMessage= function(subject, content){
                     $scope.$parent.message.subject=subject;
                     $scope.$parent.message.content=content;

                     MessageService.addMessage($scope.$parent.message,
                         function(response){
                             /*if(!response.data.success){
                              $state.go('home');
                              }else{*/
                             //console.log(response.data);
                             //}

                             alert("Poslata poruka!");
                             ngDialog.close();
                         }

                         /*,
                          function(response){
                          $state.go('home');

                          }*/);

                 };
            }]
            });



            //$window.location.reload();
        }



    }

})();