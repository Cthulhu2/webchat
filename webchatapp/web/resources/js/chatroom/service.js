'use strict';

App.factory('ChatroomService', ['$http', '$q', function ($http, $q) {

        return {
            fetchAllMessages: function () {
                return $http.get('chatroom/messages')
                        .then(
                                function (response) {
                                    return response.data;
                                },
                                function (errResponse) {
                                    console.error('Error while fetching messages');
                                    return $q.reject(errResponse);
                                }
                        );
            },
            sendMessage: function (message) {
                return $http.post('chatroom/send', message)
                        .then(
                                function (response) {
                                    return response.data;
                                },
                                function (errResponse) {
                                    console.error('Error while sending message');
                                    return $q.reject(errResponse);
                                }
                        );
            },
            deleteMessage: function (id) {
                return $http.delete('chatroom/remove', id)
                        .then(
                                function (response) {
                                    return response.data;
                                },
                                function (errResponse) {
                                    console.error('Error while delete message');
                                    return $q.reject(errResponse);
                                }
                        );
            }
        };

    }]);
