/**
 * Created by Imola on 6/1/2017.
 */

(function() {
    'use strict';

    angular
        .module('bpm_app')
        .controller('TopicDetailController', TopicDetailController);

    TopicDetailController.$inject = ['$scope', '$state', 'AuthService', '$rootScope', '$stateParams', 'TopicService'];

    function TopicDetailController($scope, $state, AuthService, $rootScope, $stateParams, TopicService) {
        TopicService.getTopic($stateParams.name,
            function(response){
                /*if(!response.data.success){
                 $state.go('home');
                 }else{*/
                console.log(response.data);
                $scope.topicdetail=response.data;
                //}
            }/*,
             function(response){
             $state.go('home');
             }*/);

    }
})();
