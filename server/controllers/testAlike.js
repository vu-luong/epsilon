var Courses = require('../models/courses');
var Learners = require('../models/learners');
var alike = require('alike');
var Recommender = require('../utils/recommendEngine');

var rawCourses;

var requirement = {
	it: 9,
	business: 0,
	english: 9,
	skill: 0,
	family: 0,
	health: 0,
	art: 0,
	office: 0
}

Learners.getCommonCoursesOfKnn(9, function(err, message){

});

return;



Recommender.getRecommendations(requirement, function(err, message){
	console.log(err);
	console.log(message);
});
return;
//
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
			english: 0,
			skill: 0,
			family: 0,
			health: 0,
			art: 0,
			office: 0
		}

		var requirement9 = {
			it: 9,
			business: 9,
			english: 0,
			skill: 9,
			family: 9,
			health: 9,
			art: 9,
			office: 9
		}

		var requirement5 = {
			it: 5,
			business: 5,
			english: 5,
			skill: 5,
			family: 5,
			health: 5,
			art: 5,
			office: 5
		}

		var options = {
		  k: 15,
		  debug: false,
		  standardize: false
		}

		var res = alike(requirement9, courses, options);
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