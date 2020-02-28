//(function ($) {
//    "use strict";
//
//    /*==================================================================
//    [ Focus input ]*/
//    $('.input100').each(function(){
//        $(this).on('blur', function(){
//            if($(this).val().trim() != "") {
//                $(this).addClass('has-val');
//            }
//            else {
//                $(this).removeClass('has-val');
//            }
//        })    
//    })
//  
//  
//    /*==================================================================
//    [ Validate ]*/
//    var input = $('.validate-input .input100');
//
//    $('.validate-form').on('submit',function(){
//    	/*
//        var check = true;
//        
//        for(var i=0; i<input.length; i++) {
//            if(validate(input[i]) == false){
//                showValidate(input[i]);
//                check=false;
//            }
//        }*/
//    	$.ajax({
//    		type: 'GET',
//    		url: '/login',
//    		contentType: 'application/json',
//    		data: {
//    			'username': $('#username').val(),
//    			'password': $('#password').val(),
//    			'test': 'hello',
//    		},
//    		success: function(result) { // send to the main webpage
//    			alert('This is it');
//    		},
//    		error: function(result) {
//    			alert('Invalid User Login');
//    		}
//    	})
//
//        return check;
//    });
//
//
//    $('.validate-form .input100').each(function(){
//        $(this).focus(function(){
//           hideValidate(this);
//        });
//    });
//
//    function validate (input) {
//        if($(input).attr('type') == 'email' || $(input).attr('name') == 'email') {
//            if($(input).val().trim().match(/^([a-zA-Z0-9_\-\.]+)@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.)|(([a-zA-Z0-9\-]+\.)+))([a-zA-Z]{1,5}|[0-9]{1,3})(\]?)$/) == null) {
//                return false;
//            }
//        }
//        else {
//            if($(input).val().trim() == ''){
//                return false;
//            }
//        }
//    }
//
//    function showValidate(input) {
//        var thisAlert = $(input).parent();
//
//        $(thisAlert).addClass('alert-validate');
//    }
//
//    function hideValidate(input) {
//        var thisAlert = $(input).parent();
//
//        $(thisAlert).removeClass('alert-validate');
//    }
//    
//    /*==================================================================
//    [ Show pass ]*/
//    var showPass = 0;
//    $('.btn-show-pass').on('click', function(){
//        if(showPass == 0) {
//            $(this).next('input').attr('type','text');
//            $(this).addClass('active');
//            showPass = 1;
//        }
//        else {
//            $(this).next('input').attr('type','password');
//            $(this).removeClass('active');
//            showPass = 0;
//        }
//        
//    });
//
//
//})(jQuery);

function startSession(sessionID) {
	$.ajax({
		type: 'GET',
		url: "localhost:8080/User/" + sessionID + "/Home/",
		data: JSON.stringify({"sessionID": sessionID}),
		dataType: 'html',
		timeout: 3000,
		success: function(response) {
		},
		error: function(reponse) {
			alert("Oh no, couldn't get you to your home page");
		}
	});
}

$(document).ready(function() {
	$('#Login').click(function() {	
    	$.ajax({
    		type: 'POST',
    		url: '/login/authenticate',
    		contentType: 'application/json; charset=utf-8',
    		dataType: 'json',
    		timeout: 3000,
    		data: JSON.stringify({
    			"username": $("#username").val(),
    			"password": $("#password").val(),
    		}),
    		success: function(response) {
    			startSession(sessionID);
    		},
    		error: function(response) {
    			window.location = window.location.pathname;
    		}
    		/*
    		statusCode: {
    			404: 	
    		}*/
    	})
	});
});