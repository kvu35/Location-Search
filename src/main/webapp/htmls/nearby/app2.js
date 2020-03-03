/**
 * 
 */
$(document).ready(function() {
	var sessionID = sessionStorage.getItem('sessionID');
    var zoomLevel = 4,
        mapCenter = [38, -101];
    
    var options = {
        center: mapCenter,
        zoom: zoomLevel
    };
    
    var currentPos;
    var radius;
    
    var map = L.map('map', options);
    
    L.tileLayer('http://{s}.basemaps.cartocdn.com/light_all/{z}/{x}/{y}.png', {
        attribution: '&copy; <a href="http://www.openstreetmap.org/copyright">OpenStreetMap</a> &copy; <a href="http://cartodb.com/attributions">CartoDB</a>',
        subdomains: 'abcd',
        maxZoom: 19
    }).addTo(map);
    
    var POIs,
        $body = $('body'),
        $locate = $('#locate'),
        $findNearest = $('#find-nearest'),
        $status = $('#status');

    $body.addClass('loaded');
    
    $locate.fadeIn().on('click', function(e) {
    	var currentPos = [$("#lat").val(), $("#lon").val()];
    	var searchRadius = Math.imul(parseInt($("#radius").val(), 10), 1000); // km to meters
        $body.removeClass('loaded');
    	$status.html('finding your location').delay(3000); 
    	$.ajax({
    		type: 'POST',
    		url: '/User/' + sessionID + '/Search',
    		contentType: 'application/json',
    		dataType: 'json',
    		timeout: 3000,
    		data: JSON.stringify({
    			coordinates: currentPos,
    			radius: searchRadius,
    			address: "123123",
    		}),
    		success: function(data) {
    			console.log(data);
    			jQuery.each(data.features, function() {
    				console.log(this.coordinates);
    				var temp = new L.Marker(this.geometry.coordinates, {
    					riseOnHover	: true
    				}).addTo(map);
    			});
    			//POIs = L.geoJson(data, {
    			//	onEachFeature: function (feature, layer) {
    			//	    layer.bindPopup(feature.properties.NAME);
    			//	},
    			//	pointToLayer : function(feature, latlng) {
    			//		return L.circleMarker(latlng, {
    			//			stroke : false,
    			//			fillColor : 'orange',
    			//			fillOpacity : 1,
    			//			radius: 2
    			//		});
    			//	}
    			//}).addTo(map); 
    			success(currentPos, searchRadius);
    		},
    		error: function(textStatus) {	
    			$body.addClass('loaded');
    			alert(JSON.stringify(textStatus));
    		}
    	})
    });   

    function success(currentPos, searchRadius) {
        zoomLevel = 14;
        map.setView(currentPos, zoomLevel);
        var myLocation = L.marker(currentPos)
                            .addTo(map)
                            .bindTooltip("you are here")
                            .openTooltip();
        var circle = L.circle(currentPos, searchRadius, {
            color: 'red',
            fillColor: 'red',
            fillOpacity: 0.3
        }).addTo(map);
        $body.addClass('loaded');
    };
})