var db = require('./db');
var Categories = {};
var Courses = require('../models/courses');
var TRIGGER_VALUE = 5;

Categories.getAllCategories = function(cb){
	db.query('SELECT * FROM categories', function(err, message){
			cb(err, message);
		});
}

Categories.getAllCourses = function(id, cb){
	if (id < 1 || id > 8){
		cb('Invalid category id', null);
		return;
	}
	Courses.getAllCourses(function(err, message){
		if (err){
			cb(err, null);
		} else {
			var result = [];
			var courses = message;
			var category;
			for (var i = 0; i < courses.length; i++){
				category = courses[i].category;
				if (!category) continue;
				var value = category.charAt(id - 1);
				if (value >= TRIGGER_VALUE) result.push(courses[i]);
			}
			cb(null, result);
		}
	});
}

module.exports = Categories;