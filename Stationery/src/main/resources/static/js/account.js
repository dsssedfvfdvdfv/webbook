let host="http://localhost:8080/rest";
const app = angular.module("app", []);
app.controller("ctrl",function($scope,$http){
	$scope.form={};
	$scope.items=[];
	$scope.reset=function(){
		 $scope.form = {};
	}
	$scope.load_all=function(){
		var url = host + "/customers";
		$http.get(url).then(resp=>{
			$scope.items=resp.data;
			console.log("Success",resp)
		}).catch(error=>{
			console.log("Error",error)
		});	
	}
	$scope.load_all();
});