/**
 * Created by Imola on 6/1/2017.
 */

(function() {
    'use strict';

    angular
        .module('angular',
            ['ui.router', 'ngDialog'])
        .run(run);


    run.$inject = ['$rootScope', '$state', '$http', 'AuthService'];
    function run($rootScope, $state, $http, AuthService) {

        var token = AuthService.getFromStorage("token");

        if(token === null || token === undefined){
            AuthService.clearStorage();
            $state.go('login');
        }else{
            AuthService.setHeader();
            AuthService.me(
                function(res){
                    $rootScope.user = res.data.user;
                },
                function(res){

                });
        }

    }

})();