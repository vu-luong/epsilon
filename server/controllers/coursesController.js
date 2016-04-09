var express = require('express');
var router = express.Router();
var Courses = require('../models/courses');

router.get('/', function(req, res){
	Courses.getTop(function(err, message){
		if (err){
			console.log(err);
		} else {
			res.json({
				status: 'success',
				message: message
			});
		}
	});
});

module.exports = router;