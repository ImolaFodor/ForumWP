/**
 * Created by Imola on 6/29/2017.
 */

(function() {

    angular
        .module('angular')
        .factory('MessageService', MessageService);

    MessageService.$inject = ['$http'];

    function MessageService($http){

        return{
            getMessagesByRecipient: function(obj, onSuccess, onError){
                var req = {
                    method: 'GET',
                    url: '/api/message/',
                    data: obj,
                    headers: {
                        'Content-Type': 'application/json',
                    }
                }
                $http(req).then(onSuccess, onError);
            },
            addMessage: function(obj,onSuccess, onError){
                var req = {
                    method: 'POST',
                    url: '/api/message',
                    data: obj,
                    headers: {
                        'Content-Type': 'application/json',
                    }
                }
                $http(req).then(onSuccess, onError);
            },
            editMessage: function(obj, onSuccess, onError){
                var req = {
                    method: 'PUT',
                    url: '/api/message',
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