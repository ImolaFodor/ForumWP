/**
 * Created by Imola on 6/1/2017.
 */

(function() {
    'use strict';

    angular
        .module('angular')
        .controller('NavbarController', NavbarController);

    NavbarController.$inject = ['$window','$scope', '$state', 'AuthService', '$rootScope'];

    function NavbarController ($window,$scope, $state, AuthService, $rootScope) {
		$scope.showZalbe=true;
		
		$scope.logout= function(){
            AuthService.deleteLoggedUser($scope.logged,
                function(response){
                    /*if(!response.data.success){
                     $state.go('home');
                     }else{*/
                    //console.log(response.data);
                    //}
                }/*,
                 function(response){
                 $state.go('home');
                 }*/);

            $window.location.reload();

        
		}
        
        AuthService.getLoggedUser(
            function(response){
                /*if(!response.data.success){
                    $state.go('home');
                }else{*/
                    console.log(response.data);
                    $scope.logged=response.data;
                    if($scope.logged.role=="KORISNIK"){
        				$scope.showZalbe=false;
        			}else{
        				$scope.showZalbe=true;
        			}
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