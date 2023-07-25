let host="http://localhost:8080/rest";
const app = angular.module("app", []);
app.contronller("ctrl",function($scope,$http){
	$scope.login=function(){
		var url=host+"/login";
		$http.get(url).then(function(resp){
			
		})
	}
})