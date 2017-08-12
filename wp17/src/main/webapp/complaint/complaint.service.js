/**
 * Created by Imola on 6/30/2017.
 */

(function() {

    angular
        .module('angular')
        .factory('ComplaintService', ComplaintService);

    ComplaintService.$inject = ['$http'];

    function ComplaintService($http){

        return{
            getComplaints: function(onSuccess, onError){
                var req = {
                    method: 'GET',
                    url: '/api/complaint',
                    headers: {
                        'Content-Type': 'application/json',
                    }
                }
                $http(req).then(onSuccess, onError);
            },
            addComplaint: function(obj,onSuccess, onError){
                var req = {
                    method: 'POST',
                    url: '/api/complaint',
                    data: obj,
                    headers: {
                        'Content-Type': 'application/json',
                    }
                }
                $http(req).then(onSuccess, onError);
            },
            deleteComplaint: function(cId, onSuccess, onError){
                var req = {
                    method: 'DELETE',
                    url: '/api/complaint/'+cId,
                    headers: {
                        'Content-Type': 'application/json',
                    }
                }
                $http(req).then(onSuccess, onError);
            },
            deleteEntity: function(cId, onSuccess, onError){
                var req = {
                    method: 'DELETE',
                    url: '/api/complaint/entity/'+cId,
                    headers: {
                        'Content-Type': 'application/json',
                    }
                }
                $http(req).then(onSuccess, onError);
            },
            warnAuthor: function(cId, onSuccess, onError){
                var req = {
                    method: 'POST',
                    url: '/api/complaint/warning/'+cId,
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