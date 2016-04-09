var Courses = require('../models/courses');
var alike = require('alike');

var rawCourses;
Courses.getAllCourses(function(err, message){
	if (err){
		console.log(err);
	} else {
		rawCourses = message;
		// build tree
		var courses = [];
		for (var i = 0; i < rawCourses.length; i++){
			courses.push({
				id: rawCourses[i].id,
				it: parseInt(rawCourses[i].category.charAt(0)),
				business: parseInt(rawCourses[i].category.charAt(1)),
				english: parseInt(rawCourses[i].category.charAt(2)),
				skill: parseInt(rawCourses[i].category.charAt(3)),
				family: parseInt(rawCourses[i].category.charAt(4)),
				health: parseInt(rawCourses[i].category.charAt(5)),
				art: parseInt(rawCourses[i].category.charAt(6)),
				office: parseInt(rawCourses[i].category.charAt(7))
			});
		}
		var requirement = {
			it: 0,
			business: 0,
			english: 5,
			skill: 0,
			family: 0,
			health: 6,
			art: 0,
			office: 0
		}

		var options = {
		  k: 5,
		  debug: false,
		  standardize: false
		}

		var res = alike(requirement, courses, options);
		console.log("Top " + options.k + " courses for you:");
		var course;
		for (var i = 0; i < res.length; i++){
			course = res[i];
			Courses.getCourse(course.id, function(err, message){
				console.log(message[0]);
			});
		}
	}
});