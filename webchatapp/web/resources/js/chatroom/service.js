'use strict';

App.factory("ChatService", function ($q, $timeout) {

    var service = {}, listener = $q.defer(), socket = {
        client: null,
        stomp: null
    };

    service.RECONNECT_TIMEOUT = 30000;
    service.SOCKET_URL = "/webchatapp/chatroom/chat";
    service.CHAT_TOPIC = "/topic/chat";

    service.receive = function () {
        return listener.promise;
    };

    var reconnect = function () {
        $timeout(function () {
            initialize();
        }, this.RECONNECT_TIMEOUT);
    };

    var getMessage = function (data) {
        var message = JSON.parse(data), out = {};
        out.text = message.text;
        out.date = new Date(message.date);
        out.id = message.id;
        out.userName = message.userName;
        return out;
    };

    var startListener = function () {
        socket.stomp.subscribe(service.CHAT_TOPIC, function (data) {
            listener.notify(getMessage(data.body));
        });
    };

    var initialize = function () {
        socket.client = new SockJS(service.SOCKET_URL);
        socket.stomp = Stomp.over(socket.client);
        socket.stomp.connect({}, startListener);
        socket.stomp.onclose = reconnect;
    };

    initialize();
    return service;
});

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
