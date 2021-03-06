/**
 * Created by Imola on 6/3/2017.
 */

/**
 * Created by Imola on 6/3/2017.
 */

(function() {

    angular
        .module('angular')
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
            getTopic: function(subforum,name, onSuccess, onError){
                var req = {
                    method: 'GET',
                    url: '/api/topic/topicdetail/'+subforum+'/'+name,
                    headers: {
                        'Content-Type': 'application/json',
                    }
                }
                $http(req).then(onSuccess, onError);
            },
            getTopicFromProfile: function(name, onSuccess, onError){
                var req = {
                    method: 'GET',
                    url: '/api/topic/topicfromprofile/'+name,
                    headers: {
                        'Content-Type': 'application/json',
                    }
                }
                $http(req).then(onSuccess, onError);
            },
            addTopic: function(obj, onSuccess, onError){
                var req = {
                    method: 'POST',
                    url: '/api/topic/newtopic',
                    data: obj,
                    headers: {
                        'Content-Type': 'application/json',
                    }
                }
                $http(req).then(onSuccess, onError);
            },
            editTopic: function(obj, onSuccess, onError){
                var req = {
                    method: 'PUT',
                    url: '/api/topic/',
                    data: obj,
                    headers: {
                        'Content-Type': 'application/json',
                    }
                }
                $http(req).then(onSuccess, onError);
            },
            saveTopic: function(obj, onSuccess, onError){
                var req = {
                    method: 'PUT',
                    url: '/api/topic/save',
                    data: obj,
                    headers: {
                        'Content-Type': 'application/json',
                    }
                }
                $http(req).then(onSuccess, onError);
            },
            deleteTopic: function(obj, onSuccess, onError){
                var req = {
                    method: 'DELETE',
                    url: '/api/topic/',
                    data: obj,
                    headers: {
                        'Content-Type': 'application/json',
                    }
                }
                $http(req).then(onSuccess, onError);
            },
            giveRating: function(obj, onSuccess, onError){
                var req = {
                    method: 'POST',
                    url: '/api/topic/rating',
                    data: obj,
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