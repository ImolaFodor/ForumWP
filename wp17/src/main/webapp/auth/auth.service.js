(function() {

    angular
        .module('angular')
        .factory('AuthService', AuthService);

    AuthService.$inject = ['$http'];

    function AuthService($http){

        return{
            login: function(obj, onSuccess, onError){
                var req = {
                    method: 'POST',
                    url: '/api/users/login',
                    data: obj,
                    headers: {
                        'Content-Type': 'application/json',
                    }
                }
                $http(req).then(onSuccess, onError);
            },
            signup: function(obj, onSuccess, onError){
                var req = {
                    method: 'POST',
                    url: '/api/users',
                    data: obj,
                    headers: {
                        'Content-Type': 'application/json',
                    }
                }
                $http(req).then(onSuccess, onError);
            },
            getLoggedUser: function(onSuccess, onError){
                var req = {
                    method: 'GET',
                    url: '/api/users/logged',
                    headers: {
                        'Content-Type': 'application/json',
                    }
                }
                $http(req).then(onSuccess, onError);
            },
            deleteLoggedUser: function(onSuccess, onError){
                var req = {
                    method: 'DELETE',
                    url: '/api/users/logout',
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