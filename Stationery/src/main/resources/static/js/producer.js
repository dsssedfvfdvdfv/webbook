let host="http://localhost:8080/rest";
const app = angular.module("app", []);

app.controller("ctrl",function($scope,$http,$window){
	  $scope.page = 0;
    $scope.size = 5; 
	$scope.form={};
	$scope.items=[];
	$scope.reset=function(){
		 $scope.form = {};
	}
	  $scope.load_all = function () {
        var url = host + "/producers?page=" + $scope.page + "&size=" + $scope.size;
        $http.get(url).then(resp => {
            $scope.items = resp.data;
            console.log("Success", resp);
        }).catch(error => {
            console.log("Error", error);
        });
    };
	
	  $scope.previousPage = function() {
        if ($scope.page > 0) {
            $scope.page--;
            $scope.load_all();
        }
    };


    $scope.nextPage = function() {
        $scope.page++;
        $scope.load_all();
    };
    $scope.load_all();
$scope.edit=function(idnhasx){
		var url=`${host}/producers/${idnhasx}`;
		$http.get(url).then(resp=>{
			$scope.form=resp.data;
			console.log("Success",resp)
		}).catch(error=>{
			console.log("Error",error)
		});
	}
    
	$scope.create = function() {
    var item = angular.copy($scope.form);
    var url = `${host}/producers`;
    $http.post(url, item)
        .then(function(response) {
            $scope.items.push(response.data);
            $scope.reset();
            console.log("Success", response);
        })
        .catch(function(error) {
            console.log("Error", error);
        });
}

});