'use strict';
App.controller('ChatroomController', ['$scope', 'ChatroomService', 'ChatService',
    function ($scope, ChatroomService, ChatService) {
        var self = this;
        self.messages = [];
        $scope.textareaMessageText = "";
        self.fetchAllMessages = function () {
            ChatroomService.fetchAllMessages()
                    .then(
                            function (d) {
                                self.messages = d;
                            },
                            function (errResponse) {
                                console.error('Error while fetching Messages');
                            }
                    );
        };
        self.sendMessage = function () {
            ChatroomService.sendMessage($scope.textareaMessageText)
                    .then(
                            function (d) {
                                $scope.textareaMessageText = "";
                            },
                            function (errResponse) {
                                console.error('Error while sending Message.');
                            }
                    );
        };
        self.deleteMessage = function (id) {
            ChatroomService.deleteMessage(id)
                    .then(
                            function (errResponse) {
                                console.error('Error while deleting message.');
                            }
                    );
        };
        self.fetchAllMessages();
        ChatService.receive().then(null, null, function (message) {
            self.messages.push(message);
        });
    }]);
