  app.controller('ctrl', function($scope, $http) {
        $scope.form = {};   
        var data = localStorage.getItem('dataToPass');
        if (data) {
            $scope.form = JSON.parse(data);       
            localStorage.removeItem('dataToPass');
        }
    });