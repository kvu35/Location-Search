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
    			coordinates: [$("#lat").val(), $("#lon").val()],
    			radius:$("#radius").val(),
    			address: "123123",
    		}),
    		success: function(data) {
    			console.log(data);
    			POIs = L.geoJson(data, {
    				pointToLayer : function(feature, latlng) {
    					return L.circleMarker(latlng, {
    						stroke : false,
    						fillColor : 'orange',
    						fillOpacity : 1,
    						radius: 2
    					});
    				}
    			}).addTo(map); 
    			success();
    			console.log(JSON.stringify(response));
    		},
    		error: error()
    	})
    });   

    function success(position) {
        $body.addClass('loaded');
        //var currentPos = [position.coords.latitude,position.coords.longitude];
        currentPos = [33, -88];
        zoomLevel = 14;
        map.setView(currentPos, zoomLevel);
        var myLocation = L.marker(currentPos)
                            .addTo(map)
                            .bindTooltip("you are here")
                            .openTooltip();
        $findNearest.fadeIn()
            .on('click', function(e) {
                $findNearest.fadeOut();
                $status.html('finding your nearest locations')
                queryFeatures(currentPos, 5);
                myLocation.unbindTooltip();
        });

    };

    function error() {
        $body.addClass('loaded');
        alert("Error");
    };
     
    function queryFeatures(currentPos, numResults) {
        
        var distances = [];
        
        stations.eachLayer(function(l) {
            
            var distance = L.latLng(currentPos).distanceTo(l.getLatLng())/1000;
            
            distances.push(distance);

        });
        
        distances.sort(function(a, b) {
            return a - b;
        });
        
        var stationsLayer = L.featureGroup();
            

        stations.eachLayer(function(l) {
            
            var distance = L.latLng(currentPos).distanceTo(l.getLatLng())/1000;
            
            if(distance < distances[numResults]) {
                
                l.bindTooltip(distance.toLocaleString() + ' km from current location.');
                
                L.polyline([currentPos, l.getLatLng()], {
                    color : 'orange',
                    weight : 2,
                    opacity: 1,
                    dashArray : "5, 10"
                }).addTo(stationsLayer);
                
            }
        });
        
        map.flyToBounds(stationsLayer.getBounds(), {duration : 3, easeLinearity: .1 });
        
        map.on('zoomend', function() {
          
            map.addLayer(stationsLayer);
        })
      
    }
})