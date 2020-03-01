/**
 * 
 */
var mymap = L.map('mapid').setView([51.505, -0.09], 13);
L.tileLayer('https://api.mapbox.com/styles/v1/{id}/tiles/{z}/{x}/{y}?access_token={accessToken}', {
    attribution: 'Map data &copy; <a href="https://www.openstreetmap.org/">OpenStreetMap</a> contributors, <a href="https://creativecommons.org/licenses/by-sa/2.0/">CC-BY-SA</a>, Imagery © <a href="https://www.mapbox.com/">Mapbox</a>',
    maxZoom: 18,
    id: 'mapbox/streets-v11',
    tileSize: 512,
    zoomOffset: -1,
    accessToken: 'sk.eyJ1IjoiYWlkYW5tdWxhb2thciIsImEiOiJjazc4NDZnZHkwZXF3M2RvNWdxdDQ4ZTJoIn0.e6Ju1epFeqXzMh_cvkiQlw'
}).addTo(mymap);

$(document).ready(function() {
	var sessionID = sessionStorage.getItem('sessionID');
	$('#Search').click(function() {	
		alert(sessionID);
    	$.ajax({
    		type: 'POST',
    		url: '/User/' + sessionID + '/Search',
    		contentType: 'application/json',
    		dataType: 'json',
    		timeout: 3000,
    		data: JSON.stringify({
//    			coordinates: ["33.7736219055","-84.4022200578"],
//    			radius:"200",
    			coordinates: [$("#latitude").val(),$("#longitude").val()],
    			radius:$("#radius").val(),
    			address: "123123",
    		}),
    		success: function(response) {
    		},
    		error: function(response) {
    			alert("Invalid user");
    		}
    	})
	});
});