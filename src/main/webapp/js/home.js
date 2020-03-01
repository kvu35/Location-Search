/**
 * 
 */
$(document).ready(function() {
	var sessionID = sessionStorage.getItem('sessionID');
	$('#Search').click(function() {	
    	$.ajax({
    		type: 'POST',
    		url: '/User/' + sessionID + '/Search',
    		contentType: 'application/json',
    		//dataType: 'text',
    		timeout: 3000,
    		data: JSON.stringify({
    			coordinates: ["33.7736219055","-84.4022200578"],
    			radius:"200",
    			address: "123123",
    		}),
    		success: function(response) {
    			alert("get response");
    			console.log("we good");
    			console.log(response);
    		},
    		error: function(response, textStatus) {
    			console.log("logging reponse");
    			console.log(response);
    			console.log(textStatus);
    			alert("Error");
    		}
    	})
	});
});