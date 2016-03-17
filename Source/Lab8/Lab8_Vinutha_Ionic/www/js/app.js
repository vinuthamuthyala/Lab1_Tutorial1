// Ionic Starter App

// angular.module is a global place for creating, registering and retrieving Angular modules
// 'starter' is the name of this angular module example (also set in a <body> attribute in index.html)
// the 2nd parameter is an array of 'requires'
angular.module('starter', ['ionic', 'ui.router'])

.run(function($ionicPlatform) {
  $ionicPlatform.ready(function() {
    if(window.cordova && window.cordova.plugins.Keyboard) {
      // Hide the accessory bar by default (remove this to show the accessory bar above the keyboard
      // for form inputs)
      cordova.plugins.Keyboard.hideKeyboardAccessoryBar(true);

      // Don't remove this line unless you know what you are doing. It stops the viewport
      // from snapping when text inputs are focused. Ionic handles this internally for
      // a much nicer keyboard experience.
      cordova.plugins.Keyboard.disableScroll(true);
    }
    if(window.StatusBar) {
      StatusBar.styleDefault();
    }
  });
})
.config(function($stateProvider, $urlRouterProvider, $locationProvider) {
  $urlRouterProvider.otherwise('/')

  $stateProvider.state('EnterCity', {
    url: '/',
    templateUrl: 'views/EnterCity.html'
  })
  
  .state('Home', {
    url: '/Home',
    templateUrl: 'views/Home.html',
      controller:'getweather'
  })
})

.controller('getweather', function ($scope, $ionicModal, $location, $state, $http) {
    
    $scope.text="Hello UMKC!";

    city = document.getElementById('cityname').value;
        $scope.cityinput='Name: '+city;
    var weather = { };
   
    $http.jsonp('http://api.openweathermap.org/data/2.5/weather?q='+city+'&appid=379ebdd71e548870edfa66f91e5eeecc&callback=JSON_CALLBACK').then(function(data) {
                       console.log(data);
        $scope.x=data;
        $scope.temp='Temperature : '+data.data.main.temp;
        $scope.desc='Description: '+data.data.weather[0].description;
        
                    //console.log(x);
                                       //         $scope.main=data.main;
        //console.log(main);
                                                //$scope.temp = data.main.temp;
                                            })
    
    $http.get('https://en.wikipedia.org/w/api.php?format=json&action=query&prop=extracts&exintro=&explaintext=&titles='+city).then(function(data2) {
        console.log(data2);
        $scope.x=data2.data.query.pages;
        var sa=$scope.x;
        console.log("vinuthaaa");
        
//        
      console.log(data2.data.query.pages.extract);
        $scope.infomtn=' About '+city+' : '+data2.data.query.pages.toString();
//            +data2.data.results[0].place_id+' (from google maps api)';
       })
    $scope.getdata = function () {
      $location.path('/Home');
     $state.go('Home');  
    };
    
    
})