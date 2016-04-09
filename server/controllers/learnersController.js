var express = require('express');
var router = express.Router();
var Learners = require('../models/learners');
var hat = require('hat');

router.post('/check', function(req, res){
	var username = req.body.username;
	console.log(username);
	Learners.checkValidUsername(username, function(err, message){
		if (err){
			console.log("Error when check valid username");
			console.log(err);
			res.json({
				status: "error",
				error: "Lỗi không xác định"
			});
		} else {
			if (message.length > 0){
				res.json({
					status: "error",
					error: "Tên người dùng đã tồn tại"
				});
			} else {
				res.json({
					status: "success",
					message: "valid"
				});
			}
		}
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
			console.log("Error when add learner");
			console.log(err);		
			if (err) {
				res.json({
					status: "error",
					error: "Lỗi không xác định"
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
									id: learner.id
								}
							});
						} else {
							console.log("Error when log in 1");
							console.log(err);	
							res.json({
								status: "error",
								error: "Lỗi không xác định"
							});
						}
					});
				} else{
					res.json({
						status: "error",
						error: "Tên tài khoản hoặc mật khẩu sai"
					});
				}
			} else {
				console.log("Error when log in 2");
				console.log(err);	
				res.json({
					status: "error",
					error: "Lỗi không xác định"
				});
			}
		});
	}
);

router.delete('/sessions', function(req, res){
	var id = req.headers.id;
	Learners.logout(id, function(err, message){
		if (err){
			console.log("Error when log in");
			console.log(err);	
			res.json({
				status: 'error',
				error: "Lỗi không xác định"
			});
		} else {
			if (message.affectedRows > 0){
				res.json({
					status: 'success',
					message: 'Đăng xuất thành công'
				});
			} else {
				res.json({
					status: 'error',
					error: 'id người dùng sai'
				});
			}
			
		}
	});
});


module.exports = router;