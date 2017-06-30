/**
 * Created by Imola on 6/29/2017.
 */

(function() {

    angular
        .module('angular')
        .factory('UserService', UserService);

    UserService.$inject = ['$http'];

    function UserService($http){

        return{
            getUsers: function(onSuccess, onError){
                var req = {
                    method: 'GET',
                    url: '/api/users',
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