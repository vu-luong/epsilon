var TOP_COURSES_NUMBER = 5;
var db = require('./db');
var alike = require('alike');
var Courses = {};

// test Alike
Courses.getAllCourses = function(cb){
	db.query('SELECT * FROM courses', function(err, message){
			cb(err, message);
		});
}

Courses.getCourse = function(id, cb){
	db.query('SELECT * FROM courses WHERE id = ?', id, function(err, message){
			cb(err, message);
		});
}

Courses.getTop = function(cb){
	db.query('SELECT * FROM courses ORDER BY learners DESC LIMIT ?', TOP_COURSES_NUMBER, 
		function(err, message){
			cb(err, message);
		});
}

module.exports = Courses;