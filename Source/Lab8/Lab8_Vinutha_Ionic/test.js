describe('getweather',function(){
    var scope;
    beforeEach(angular.mock.module('starter'));
    beforeEach(angular.mock.inject(function($scope, $ionicModal, $location, $state, $http){
 scope=$rootScope.$new();
        $controller('getweather',{$scope:scope});
        
    }));
    
    it('should have variable text = "Hello UMKC!"', function(){
        expect(scope.text).toBe('Hello UMKC!');
    });
    
});