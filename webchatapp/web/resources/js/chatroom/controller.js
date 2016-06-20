'use strict';

App.controller('ChatroomController', ['$scope', 'ChatroomService', function ($scope, ChatroomService) {
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

    }]);
