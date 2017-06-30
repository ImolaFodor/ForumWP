/**
 * Created by Imola on 6/1/2017.
 */

(function() {
    'use strict';

    angular
        .module('angular')
        .controller('NavbarController', NavbarController);

    NavbarController.$inject = ['$scope', '$state', 'AuthService', '$rootScope'];

    function NavbarController ($scope, $state, AuthService, $rootScope) {

        $scope.logout=function(){
            AuthService.clearStorage();
            $rootScope.user = undefined;
            $state.go("home");
        }

    }


})();