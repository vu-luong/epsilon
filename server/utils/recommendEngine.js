var RETURNED_RECOMMENDATIONS = 5;

var alike = require('alike');
var Courses = require('../models/courses');
var Learners = require('../models/learners');
var Blacklist = require('../models/blacklist');
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

Recommender.getBasicRecommendations = function (allCourses, requirement, cb){
			var rawCourses = allCourses;
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
			console.log('allCourses length: ' + res.length);
			for (var j = 0; j < res.length; j++){
				console.log(res[j].id);
			}
}

Recommender.getAdvanceRecommendations = function (learner_id, requirement, cb){
	Courses.getAllCourses(function(err, message){
		if (err){
			cb(err, null);
		} else {
			var subId = {};
			var allCourses = message;
			Learners.getHistory(learner_id, function(err, message){
				if (err){
					cb(err, null);
				} else {
					var history = message;
					Blacklist.getAll(function(err, message){
						if (err){
							cb(err, null);
						} else {
							var blacklist = message;
							// todo
							for (var i = 0; i < history.length; i++){
									hId = history[i].id;
									subId[hId] = true;
								}
							for (var k = 0; k < blacklist.length; k++){
								if (blacklist[k].course2 in subId){
									subId[blacklist[k].course1] = true;
								}
							}
							for (var j = allCourses.length - 1; j >= 0; j--){
								var course = allCourses[j];
								if (course.id in subId){
									allCourses.splice(j, 1);
								}
							}
							// console.log('allCourses length: ' + allCourses.length);
							// for (var j = 0; j < allCourses.length; j++){
							// 	console.log(allCourses[j].id);
							// }
							Recommender.getBasicRecommendations(allCourses, requirement, function(err, message){
								cb(err, message);
							});
						}
					});
				}
			});	
		}
	});
}



module.exports = Recommender;