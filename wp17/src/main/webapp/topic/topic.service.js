/**
 * Created by Imola on 6/3/2017.
 */

/**
 * Created by Imola on 6/3/2017.
 */

(function() {

    angular
        .module('bpm_app')
        .factory('TopicService', TopicService);

    TopicService.$inject = ['$http'];

    function TopicService($http){

        return{
            getTopics: function(name, onSuccess, onError){
                var req = {
                    method: 'GET',
                    url: '/api/topic/'+name,
                    headers: {
                        'Content-Type': 'application/json',
                    }
                }
                $http(req).then(onSuccess, onError);
            },
            getTopic: function(name, onSuccess, onError){
                var req = {
                    method: 'GET',
                    url: '/api/topic/topicdetail/'+name,
                    headers: {
                        'Content-Type': 'application/json',
                    }
                }
                $http(req).then(onSuccess, onError);
            },
            me: function(onSuccess, onError){
                var req = {
                    method: 'GET',
                    url: '/api/user/me',
                    headers: {
                        'Content-Type': 'application/json',
                    }
                }
                $http(req).then(onSuccess, onError);
            },
            saveToStorage(id, val){
                localStorage.setItem(id, val);
            },
            getFromStorage(something){
                return localStorage.getItem(something);
            },
            clearStorage(){
                localStorage.clear();
                $http.defaults.headers.common.Authorization = undefined;
            },
            setHeader(){
                $http.defaults.headers.common.Authorization = localStorage.getItem("token");;
            }
        }

    }

})();