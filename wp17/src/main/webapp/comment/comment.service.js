
/**
 * Created by Imola on 6/3/2017.
 */

(function() {

    angular
        .module('angular')
        .factory('CommentService', CommentService);

    CommentService.$inject = ['$http'];

    function CommentService($http){

        return{
            getComments: function(name, onSuccess, onError){
                var req = {
                    method: 'GET',
                    url: '/api/comments/'+name,
                    headers: {
                        'Content-Type': 'application/json',
                    }
                }
                $http(req).then(onSuccess, onError);
            },
            writeComment: function(obj, onSuccess, onError){
                var req = {
                    method: 'POST',
                    url: '/api/comments',
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
                    url: '/api/comments/rating',
                    data: obj,
                    headers: {
                        'Content-Type': 'application/json',
                    }
                }
                $http(req).then(onSuccess, onError);
            },
            saveComment: function(obj, onSuccess, onError){
                var req = {
                    method: 'PUT',
                    url: '/api/comments/save',
                    data: obj,
                    headers: {
                        'Content-Type': 'application/json',
                    }
                }
                $http(req).then(onSuccess, onError);
            },
            deleteComment: function(obj, onSuccess, onError){
                var req = {
                    method: 'DELETE',
                    url: '/api/comments',
                    data: obj,
                    headers: {
                        'Content-Type': 'application/json',
                    }
                }
                $http(req).then(onSuccess, onError);
            },
            editComment: function(obj, onSuccess, onError){
                var req = {
                    method: 'PUT',
                    url: '/api/comments',
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