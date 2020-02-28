/**
 * 
 */
$(document).ready(function() {
	var sessionID;
	$('#Search').click(function() {	
    	$.ajax({
    		type: 'POST',
    		url: '/User/' + sessionID + '/Search',
    		contentType: 'application/json; charset=utf-8',
    		dataType: 'json',
    		timeout: 3000,
    		data: JSON.stringify({
    			"username": $("#username").val(),
    			"password": $("#password").val(),
    		}),
    		success: function(response) {
    			alert(response);
    			window.location = 'htmls/home.html';
    		},
    		error: function(response) {
    			alert("Invalid user");
    		}
    	})
	});
});