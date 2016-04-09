var express = require('express');
var router = express.Router();
var Courses = require('../models/courses');
var Enrollments = require('../models/enrollments');

router.get('/:id', function(req, res){
	var id = req.params.id;
	var learner_id = req.headers.learner_id;
	Courses.getCourse(id, function(err, message){
		if (err){
			console.log('error when get course by id 1');
			console.log(err);
			res.json({
				status: 'error',
				error: "Lỗi không xác định"
			});
		} else {
			if (message.length < 1){
				res.json({
					status: 'error',
					error: "Khoá học không tồn tại"
				});
			} else {
				var course = message[0];
				Enrollments.check(learner_id, id, function(err, message){
					if (err){
						console.log('error when get course by id 2');
						console.log(err);
						res.json({
							status: 'error',
							error: "Lỗi không xác định"
						});
					} else {
						var learned = false;
						if (message.length > 0) {
							learned = true;
						}
						course.learned = learned;
						res.json({
							status: 'success',
							message: course
						});
					}
				});
			}
		}
	});
});

module.exports = router;