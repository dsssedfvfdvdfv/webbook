angular.module('app').service('FormService', function() {
  var formData = {}; // Dữ liệu biểu mẫu sẽ được lưu trữ ở đây

  return {
    getFormData: function() {
      return formData;
    },
    setFormData: function(data) {
      formData = data;
    }
  };
});