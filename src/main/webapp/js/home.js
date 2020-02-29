/**
 * 
 */
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
    			coordinates: ["33.7736219055","-84.4022200578"],
    			radius:"200",
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