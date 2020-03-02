/**
 * 
 */
$(document).ready(function() {
 
    var zoomLevel = 4,
        mapCenter = [38, -101];
    
    var options = {
        center: mapCenter,
        zoom: zoomLevel
    };
    
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
        $body.removeClass('loaded');
    	$status.html('finding your location'); 
    	$.ajax({
    		type: 'POST',
    		url: '/User/' + 0 + '/Search',
    		contentType: 'application/json',
    		dataType: 'json',
    		timeout: 3000,
    		data: JSON.stringify({
    			coordinates: ["33.7736219055","-84.4022200578"],
    			radius:"200",
    			//coordinates: [$("#lat").val(), $("#lon").val()],
    			//radius:$("#radius").val(),
    			address: "123123",
    		}),
    		success: function(data) {
    			console.log(data);
    			jQuery.each(data.features, function() {
    				console.log(this.coordinates);
    				var temp = new L.Marker(this.geometry.coordinates).addTo(map);
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
    			success(33.7736219055,-84.4022200578);
    		},
    		error: function(textStatus) {	
    			$body.addClass('loaded');
    			alert(JSON.stringify(textStatus));
    		}
    	})
    });   

    function success(lat, lon) {
        $body.addClass('loaded');
        //var currentPos = [position.coords.latitude,position.coords.longitude];
        currentPos = [33.7736219055,-84.4022200578];
        zoomLevel = 14;
        map.setView(currentPos, zoomLevel);
        var myLocation = L.marker(currentPos)
                            .addTo(map)
                            .bindTooltip("you are here")
                            .openTooltip();
        var circle = L.circle(currentPos, 70, {
            color: 'blue',
            fillColor: 'blue',
            fillOpacity: 0.5
        }).addTo(map);
    };
})