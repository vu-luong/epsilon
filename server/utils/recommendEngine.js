var RETURNED_RECOMMENDATIONS = 5;

var alike = require('alike');
var Courses = require('../models/courses');
var db = require('../models/db');
var Recommender = {};

Recommender.getRecommendations = function (requirement, cb){
	Courses.getAllCourses(function(err, message){
		if (err){
			cb(err, null);
		} else {
			rawCourses = message;
			var courseHasID = {};
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
				courseHasID[rawCourses[i].id] = rawCourses[i];
			}
			var options = {
			  k: RETURNED_RECOMMENDATIONS, 
			  debug: false,
			  standardize: false
			}
			var knn = alike(requirement, courses, options);
			var res = [];
			for (var i = 0; i < knn.length; i++){
				res.push(courseHasID[knn[i].id]);
			}
			cb(null, res);
		}
	});
}

module.exports = Recommender;