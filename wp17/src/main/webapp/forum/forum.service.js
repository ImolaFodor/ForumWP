/**
 * Created by Imola on 6/3/2017.
 */

(function() {

    angular
        .module('bpm_app')
        .factory('ForumService', ForumService);

    ForumService.$inject = ['$http'];

    function ForumService($http){

        return{
            getSubForums: function(onSuccess, onError){
                var req = {
                    method: 'GET',
                    url: '/api/forum',
                    headers: {
                        'Content-Type': 'application/json',
                    }
                }
                $http(req).then(onSuccess, onError);
            },
            deleteSubForum: function(obj,onSuccess, onError){
                var req = {
                    method: 'DELETE',
                    url: '/api/forum',
                    data: obj,
                    headers: {
                        'Content-Type': 'application/json',
                    }
                }
                $http(req).then(onSuccess, onError);
            },
            addSubForum: function(obj,onSuccess, onError){
                var req = {
                    method: 'POST',
                    url: '/api/forum',
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