$(document).ready(function() {
	const login = document.querySelector('.login');
	const signup = document.querySelector('.signup');
	const formLogin = document.querySelector('.login-form');
	const formSignup = document.querySelector('.signup-form');
	
	function loginForm() {
		login.classList.add('active');
		login.classList.remove('inactive');
		signup.classList.add('inactive');
		signup.classList.remove('active');
		formLogin.classList.remove('hide');
		formSignup.classList.add('hide');
    	//$.ajax({
    	//	type: 'POST',
    	//	url: '/login/authenticate',
    	//	contentType: 'application/json; charset=utf-8',
    	//	dataType: 'text',
    	//	timeout: 3000,
    	//	data: JSON.stringify({
    	//		"username": $("#username").val(),
    	//		"password": $("#password").val(),
    	//	}),
    	//	success: function(response) {
    	//		alert("success");
    	//		window.location = 'htmls/home.html';
    	//		sessionStorage.setItem('sessionID', response);
    	//	},
    	//	error: function(response, textStatus) {
    	//		alert("failed");
    	//		console.log(textStatus);
    	//	}
    	//})
	}
	
	function signupForm() {
		login.classList.add('inactive');
		login.classList.remove('active');
		signup.classList.add('active');
		signup.classList.remove('inactive');
		formLogin.classList.add('hide');
		formSignup.classList.remove('hide');
	}
	
	login.addEventListener('click', loginForm);
	signup.addEventListener('click', signupForm);
	$('#Login2').click(function() {
		if ($('#username').val() == "" || $('#password').val() == "") {
			alert("Please input valid user!");
			return;
		}
    	$.ajax({
    		type: 'POST',
    		url: '/login/authenticate',
    		contentType: 'application/json; charset=utf-8',
    		dataType: 'text',
    		timeout: 3000,
    		data: JSON.stringify({
    			username: $("#username").val(),
    			password: $("#password").val(),
    		}),
    		success: function(response) {
    			window.location = 'htmls/nearby/index.html';
    			sessionStorage.setItem('sessionID', response);
    		},
    		error: function(response, textStatus) {
    			alert("Something went wrong!");
    		}
    	})
	});
})