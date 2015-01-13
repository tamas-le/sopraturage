$(document).ready(function () {

	var directionsDisplay,
		directionsService = new google.maps.DirectionsService(),
		map,
		data_home_profile_lat = $("#map-canvas").attr("data-home-profile-lat"),
		data_home_profile_lng = $("#map-canvas").attr("data-home-profile-lng"),
		data_workplace_profile_lat = $("#map-canvas").attr("data-workplace-profile-lat"),
		data_workplace_profile_lng = $("#map-canvas").attr("data-workplace-profile-lng"),
		home_profile = new google.maps.LatLng(parseFloat(data_home_profile_lng), parseFloat(data_home_profile_lat)),
		workplace_profile = new google.maps.LatLng(parseFloat(data_workplace_profile_lng), parseFloat(data_workplace_profile_lat));


	function initialize() {
	  directionsDisplay = new google.maps.DirectionsRenderer();
	  var toulouse = new google.maps.LatLng(43.604462, 1.444247),

		  mapOptions = {
		    zoom:9,
		    // waypoints: waypts,
		    // optimizeWaypoints: true,
		    center: toulouse
	  	  }

	  map = new google.maps.Map(document.getElementById("map-canvas"), mapOptions);


	  directionsDisplay.setMap(map);
	  calcRoute(home_profile, workplace_profile);


	  var myMarker = new google.maps.Marker({
			// Coordonnées du cinéma
			position: new google.maps.LatLng(43.604462, 1.444247),
			map: map,
			title: "Cinéma Pathé Bellecour"
		});

        google.maps.event.addListener(map, 'click', function(event){
          this.setOptions({scrollwheel:true});
        });

        google.maps.event.addListener(map, 'mouseout', function(event){
         this.setOptions({scrollwheel:false});  
        });


	}

	function calcRoute(start, end) {
	  var request = {
	    origin: start,
	    destination: end,
	    travelMode: google.maps.TravelMode.DRIVING
	  };
	  directionsService.route(request, function(result, status) {
	    if (status == google.maps.DirectionsStatus.OK) {
	      directionsDisplay.setDirections(result);
	      //routes 0 car pas d'alternative possible
	      //legs 0 car pas de waypoint, s'il y avait 1 wyapoint => 2 legs
	    }
	  });
	}

	initialize();

});