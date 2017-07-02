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

    UserController.$inject = ['$scope','$window','$location', '$state', 'UserService','MessageService','AuthService', 'ngDialog'];
		
    function UserController ($scope,$window, $location, $state, UserService, MessageService, AuthService, ngDialog) {
        $scope.value = true;
        $scope.message={};
        $scope.message.subject=" ";
        $scope.message.content=" ";
        
        
       AuthService.getLoggedUser(
            	function(response){
                    console.log(response.data);
                    $scope.logged=response.data;
                    console.log($scope.logged.savedTopics);
                    console.log($scope.logged.followedSubForums);
                    $scope.savedTopics=$scope.logged.savedTopics;
                    $scope.followedSubForums=$scope.logged.followedSubForums;
                    
                    var loggedUser= {username: $scope.logged.username};
                    
            		MessageService.getMessagesByRecipient(loggedUser,
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
           		});


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
		
		
		
        

        $scope.seeMessage= function(subject,content,recipient){

            $scope.message.recipient=recipient;
            $scope.message.subject=subject;
            $scope.message.content=content;
            $scope.message.read=true;
                     
            $scope.message.sender= $scope.logged.username;
            

            
        

            ngDialog.open({
                template: 'message/message.html',
                scope: $scope,
                controller: ['$scope','MessageService', function($scope, $MessageService) {
                    $scope.content=$scope.$parent.message.content;
                    $scope.subject=$scope.$parent.message.subject;
                    $scope.recipient=$scope.$parent.message.recipient;
                    
                    var edited= {recipient: $scope.$parent.message.recipient, sender: $scope.logged.username, subject: $scope.$parent.message.subject, content: $scope.$parent.message.content, read: $scope.$parent.message.read}
                    
                    MessageService.editMessage(edited,
             				function(response){
             				console.log(response.data);
                 				$scope.message=response.data;
             				});
            }]
            });
        }

        $scope.sendMessage= function() {

            ngDialog.open({
                template: 'message/newmessage.html',
                scope: $scope,
                controller: ['$scope', 'AuthService', function($scope, $AuthService) {
                 $scope.confirmSendMessage= function(subject, content, recipient){
                     $scope.$parent.message.subject=subject;
                     $scope.$parent.message.content=content;
                     $scope.$parent.message.recipient= recipient;
                     
                     AuthService.getLoggedUser(
            				function(response){
                    			console.log(response.data);
                    			$scope.logged=response.data;
           					});
                     
                     $scope.$parent.message.sender= $scope.logged.username;
                     

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