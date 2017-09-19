/**
 * Created by Imola on 6/1/2017.
 */

(function() {
    'use strict';

    angular
        .module('angular')
        .controller('SignUpController', SignUpController);

    SignUpController.$inject = ['$scope', '$state', 'AuthService', '$rootScope'];

    function SignUpController($scope, $state, AuthService, $rootScope) {

        $scope.user = {};
        $scope.user.role="";
        $scope.user.name="";
        $scope.user.lastName="";
        $scope.user.phone="";
        $scope.user.email="";
        
        $scope.roles=["KORISNIK", "MODERATOR", "ADMIN"];

        $scope.signup = function(){
			$scope.user.role=$scope.selectedRole;
			if($scope.user.password==$scope.user.rpassword && !$scope.user.username==""){
				AuthService.signup(
                $scope.user,
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
                    alert("Korisnik je dodat!");
                    $state.go("home");
                },
                function(res){
                    if(res.data){
                        alert("Postoji korisnik sa unetim korisnickim imenom!");
                    }
                    console.log(res);
                });
			}else if($scope.user.password!=$scope.user.rpassword)
			{
				alert("Ne podudaraju se lozinke!");
			}else if(!$scope.user.username){
				alert("Morate uneti korisnicko ime!");
			}
            
        }

    }
})();
