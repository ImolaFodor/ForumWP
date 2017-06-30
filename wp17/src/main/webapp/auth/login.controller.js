/**
 * Created by Imola on 6/1/2017.
 */

(function() {
    'use strict';

    angular
        .module('angular')
        .controller('LoginController', LoginController);

    LoginController.$inject = ['$scope', '$state', 'AuthService', '$rootScope'];

    function LoginController($scope, $state, AuthService, $rootScope) {

        $scope.user = {};
        $scope.user.id="";
        $scope.user.password="";

        $scope.login = function(){
            AuthService.login(
                $scope.user,
                function(res){
                    /*AuthService.saveToStorage("token", res.data.token);
                    AuthService.setHeader();
                    AuthService.me(
                        function(res){
                            $rootScope.user = res.data.user;
                        },
                        function(res){

                        });*/
                    alert("Registrovan korisnik!");
                    $state.go("home");
                },
                function(res){
                    alert("Neregistrovan korisnik!");
                    console.log(res);
                });
        }


    $scope.signup= function() {
            $state.go("signup");
        }

    }
})();
