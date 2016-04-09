var express = require('express');
var router = express.Router();
var Categories = require('../models/categories');

router.get('/', function(req, res){
	Categories.getAllCategories(function(err, message){
		if (err){
			res.json({
				status: 'error',
				message: String(err)
			});
		} else {
			res.json({
				status: 'success',
				message: message
			});
		}
	});
});

router.get('/:id/all', function(req, res){
	var id = req.params.id;
	Categories.getAllCourses(id, function(err, message){
		if (err){
			res.json({
				status: 'error',
				message: String(err)
			});
		} else {
			res.json({
				status: 'success',
				courses: message
			});
		}
	});
});

module.exports = router;