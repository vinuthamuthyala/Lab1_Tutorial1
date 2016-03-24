var imageApp=angular.module('starter', ['ionic','ngCordova','firebase','ui.router']);

var fb = new Firebase("https://vinusamplecameraapp.firebaseio.com/"); //ur firebase url

imageApp.run(function($ionicPlatform) {
  $ionicPlatform.ready(function() {
    if(window.cordova && window.cordova.plugins.Keyboard) {
      cordova.plugins.Keyboard.hideKeyboardAccessoryBar(true);
    }
    if(window.StatusBar) {
      StatusBar.styleDefault();
    }
  });
});

imageApp.config(function($stateProvider, $urlRouterProvider) {
    $stateProvider
        .state("firebase", {
            url: "/firebase",
            templateUrl: "templates/firebase.html",
            controller: "FirebaseController",
            cache: false
        })
       
    .state('EnterCity', {
    url: '/EnterCity',
    templateUrl: 'templates/EnterCity.html'
//        controller:"entercity"
  })
     .state("secure", {
            url: "/secure",
            templateUrl: "templates/secure.html",
            controller: "SecureController"
        })
  .state("register", {
            url: "/register",
            templateUrl: "templates/register.html",
            controller: "RegisterController"
        })
    .state('Home', {
    url: '/Home',
    templateUrl: 'templates/Home.html',
      controller:'getweather'
  })
    $urlRouterProvider.otherwise('/EnterCity');
});

imageApp.controller("FirebaseController", function($scope, $state, $firebaseAuth) {

    var fbAuth = $firebaseAuth(fb);
    
    $scope.login = function(username, password) {
        fbAuth.$authWithPassword({
            email: username,
            password: password
        }).then(function(authData) {
            $state.go("EnterCity");
        }).catch(function(error) {
            console.error("ERROR: " + error);
        });
    }

    $scope.register = function(username, password) {
        fbAuth.$createUser({email: username, password: password}).then(function(userData) {
            return fbAuth.$authWithPassword({
                email: username,
                password: password
            });
        }).then(function(authData) {
            $state.go("EnterCity");

        }).catch(function(error) {
            console.error("ERROR: " + error);
        });
    }
$scope.gotoregister=function(){
   $state.go("register"); 
};
    
});

//secure controller

imageApp.controller('RegisterController', function($scope, $ionicHistory, $firebaseArray, $cordovaCamera, $ionicModal, $state) {

    $ionicHistory.clearHistory();  //for clearing user login history

    $scope.images = [];

    var fbAuth = fb.getAuth();
    if(fbAuth) {
        var userReference = fb.child("users/" + fbAuth.uid);   //capture the user reference in data structure ,it navigates to specific user page in freebase
        var syncArray = $firebaseArray(userReference.child("images"));  //binding specific node in firebase to an array object in angularjs
        $scope.images = syncArray;
    } else {
        $state.go("firebase");  //directs to firebase page
    }

    $scope.upload = function() {
        var options = {
            quality : 75,
            destinationType : Camera.DestinationType.DATA_URL,
            sourceType : Camera.PictureSourceType.CAMERA,
            allowEdit : true,
            encodingType: Camera.EncodingType.JPEG,
            targetWidth: 500,
            targetHeight: 500,
            popoverOptions: CameraPopoverOptions,
            saveToPhotoAlbum: false,
            correctOrientation:true
        };
        
        
        
        $cordovaCamera.getPicture(options).then(function(imageData) {
             $scope.img22= "data:image/jpeg;base64," + imageData;
      //var image = document.getElementById('myPic');
      //image.src = "data:image/jpeg;base64," + imageData;
             alert("Image has been uploaded");
        }, function(error) {
            console.error(error);
        });
    }

});

imageApp.controller('getweather', function ($scope, $ionicModal, $location, $state, $http,$cordovaSms) {
    
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
    
//    $scope.sendsms=function(){
//        console.log("staaaaaartttt")
////        document.addEventListener("deviceready", function () {
//            var phone=document.getElementById('phone').value;
//            var message=document.getElementById('phone').value;
//            console.log("Message"+message+"Phone"+phone);
//             var options = {
//            replaceLineBreaks: false, // true to replace \n by a new line, false by default
//    android: {
//      intent: ''
//    }
//        };
//        $scope.sms = {
//    number: phone,
//    message: message
//  };
//console.log("cordostart");
//    $cordovaSms
//      .send(phone, message, options)
//      .then(function() {
//        console.log("vinuthaaasssmmsss");
//         alert("sms has been sent");
//        // Success! SMS was sent
//      }, function(error) {
//        console.log("errrrrrr");
//        console.error(error);
//        // An error occurred
//      });
//
////  });
//    };
    
})