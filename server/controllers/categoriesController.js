var express = require('express');
var router = express.Router();
var Categories = require('../models/categories');

router.get('/', function(req, res){
	Categories.getAllCategories(function(err, message){
		if (err){
			console.log("error when get categories");
			console.log(err);
			res.json({
				status: "error",
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

router.get('/:id/all', function(req, res){
	var id = req.params.id;
	Categories.getAllCourses(id, function(err, message){
		if (err){
			console.log('Error when get all courses of category');
			res.json({
				status: "error",
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