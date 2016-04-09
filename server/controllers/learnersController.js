var express = require('express');
var router = express.Router();
var Learners = require('../models/learners');
var hat = require('hat');

router.get('/', function(req, res){
	res.json({
		status: 'success',
		message: 'server tested ok'
	});
});

// create learner
router.post('/', 
	function(req, res){
		Learners.addLearner({
			username: req.body.username,
			password: req.body.password,
			requirement_category: req.body.requirement_category
		}, function(err, message){
			if (err) {
				res.json({
					status: "error",
					message: String(err)
				});
			} else {
				res.json({
					status: "success",
					message: message
				});
			}
		});
	}
);

// log in
router.post('/sessions',
	function(req, res){
		console.log('Login request:');
		console.log(req.body);
		console.log('--------------');
		var username = req.body.username;
		var password = req.body.password;
		Learners.findByUsername(req.body.username, function(err, message){
			if (err === null){
				if (message && message[0] && message[0].password === password){
					var token = hat();
					var learner = message[0];
					delete learner.password;
					Learners.setTokenById({
						id: learner.id,
						token: token
					}, function(err, message){
						if (err === null){
							res.json({
								status: "success",
								message: {
									token: token,
									learner: learner
								}
							});
						} else {
							res.json({
								status: "error",
								message: String(err)
							});
						}
					});
				} else{
					res.json({
						status: "error",
						message: "Wrong username or password"
					});
				}
			} else {
				res.json({
					status: "error",
					message: String(err)
				});
			}
		});
	}
);

router.delete('/sessions', function(req, res){
	var id = req.headers.id;
	Learners.logout(id, function(err, message){
		if (err){
			res.json({
				status: 'error',
				message: String(err)
			});
		} else {
			if (message.affectedRows > 0){
				res.json({
					status: 'success',
					message: 'You have been logged out'
				});
			} else {
				res.json({
					status: 'error',
					message: 'Wrong id'
				});
			}
			
		}
	});
});


module.exports = router;