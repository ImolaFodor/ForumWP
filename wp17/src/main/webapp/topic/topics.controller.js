/**
 * Created by Imola on 6/3/2017.
 */

/**
 * Created by Imola on 6/3/2017.
 */

/**
 * Created by Imola on 6/1/2017.
 */

(function() {
    'use strict';

    angular
        .module('bpm_app')
        .controller('TopicController', TopicController);

    TopicController.$inject = ['$scope', '$state', 'AuthService', '$rootScope', '$stateParams', 'TopicService'];

    function TopicController($scope, $state, AuthService, $rootScope, $stateParams, TopicService) {
        $scope.query = {};
        $scope.queryBy = '$';

        TopicService.getTopics($stateParams.name,
            function(response){
                /*if(!response.data.success){
                 $state.go('home');
                 }else{*/
                console.log(response.data);
                $scope.topics=response.data;
                //}
            }/*,
             function(response){
             $state.go('home');
             }*/);




    }
})();
