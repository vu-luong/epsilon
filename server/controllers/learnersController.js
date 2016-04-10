var express = require('express');
var router = express.Router();
var Learners = require('../models/learners');
var Enrollments = require('../models/enrollments');
var Supports = require('../models/supports');
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
			if (err) {
				console.log("Error when add learner");
				console.log(err);			
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
					res.json({
						status: "success",
						message: {
							id: learner.id
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
	res.json({
		status: 'success',
		message: 'Đăng xuất thành công'
	});

	// Learners.logout(id, function(err, message){
	// 	if (err){
	// 		console.log("Error when log in");
	// 		console.log(err);	
	// 		res.json({
	// 			status: 'error',
	// 			error: "Lỗi không xác định"
	// 		});
	// 	} else {
	// 		if (message.affectedRows > 0){
	// 			res.json({
	// 				status: 'success',
	// 				message: 'Đăng xuất thành công'
	// 			});
	// 		} else {
	// 			res.json({
	// 				status: 'error',
	// 				error: 'Id người dùng sai'
	// 			});
	// 		}
			
	// 	}
	// });
});

router.get('/recommendations', function(req, res){
	var id = req.headers.id;
	Learners.getRecommendations(id, function(err, message){
		if (err){
			console.log('Error when get recommendations');
			console.log(err);
			res.json({
				status: 'error',
				error: "Lỗi không xác định"
			});
		} else {
			res.json({
				status: 'success',
				message: message
			});
		}
	});
});

router.get('/recommendations_user', function(req, res){
	var id = req.headers.id;
	Learners.getCommonCoursesOfKnn(id, function(err, message){
		if (err){
			console.log('Error when get recommendations');
			console.log(err);
			res.json({
				status: 'error',
				error: "Lỗi không xác định"
			});
		} else {
			res.json({
				status: 'success',
				message: message
			});
		}
	});
});

router.post('/enroll', function(req, res){
	var learner_id = req.headers.id;
	var course_id = req.body.course_id;
	var curTime = new Date();
	var month = curTime.getMonth() + 1;
	//if (parseInt(month) <= 9) month = "0" + month;
	var date = curTime.getDate();
	//if (parseInt(date) <= 9) date = "0" + date;
	var hour = curTime.getHours();
	//if (parseInt(hour) <= 9) hour = "0" + hour;
	var minute = curTime.getMinutes();
	//if (parseInt(minute) <= 9) minute = "0" + minute;
	var sec = curTime.getSeconds();
	//if (parseInt(sec) <= 9) sec = "0" + sec;

	var datetime = curTime.getFullYear() + '-' + month + '-' +
					date + ' ' + hour + ':' + minute +
					':' + sec;

	console.log(datetime);
	console.log(curTime);
	var enrollment = {
		learner_id: learner_id,
		course_id: course_id,
		datetime: datetime
	}
	Enrollments.addEnrollment(enrollment, function(err, message){
		if (err){
			console.log('Error when add enrollment');
			console.log(err);
			res.json({
				status: 'error',
				error: "Lỗi không xác định"
			});
		} else {
			res.json({
				status: 'success',
				message: message
			});
			Supports.enroll(learner_id, course_id, function(err, message){
				if (err){
					console.log('error when update support');
					console.log(err);
				}
			});
			//
			Learners.updateCombinedCategory(course_id, learner_id, function(err, message){
				if (err){
					console.log('error when update combined category');
					console.log(err);
				}
			});
		}
	});
});


router.get('/history', function(req, res){
	var id = req.headers.id;
	Learners.getHistory(id, function(err, message){
		if (err){
			console.log('Error when get history');
			console.log(err);
			res.json({
				status: 'error',
				error: "Lỗi không xác định"
			});
		} else {
			res.json({
				status: 'success',
				message: message
			});
		}
	});
});

module.exports = router;