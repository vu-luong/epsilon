var db = require('./db');
var Learners = {};
var Recommender = require('../utils/recommendEngine');
var Courses = require('../models/courses');
var Blacklist = require('../models/blacklist');
var Enrollments = require('../models/enrollments');

Learners.checkValidUsername = function(username, cb){
	db.query('SELECT * FROM learners WHERE username = ?', username, function(err, message){
			cb(err, message);
		});
}

Learners.addLearner = function (learner, cb){
	db.query('INSERT INTO learners SET ?', learner, function(err, message){
		cb(err, message);
	});
}

Learners.calRequirementRcms = function(learner_id, category, cb){
	// scale

	var TRIGGER_DEVIATION = 8; // TODO improve it
	var deviation = 0;
	var avg = 0;
	for (var i = 0; i < category.length; i++){
		avg += parseInt(category.charAt(i));
	}
	avg /= category.length;
	for (var i = 0; i < category.length; i++){
		var value = parseInt(category.charAt(i));
		deviation += (value - avg) * (value - avg);
	}
	if (deviation >= TRIGGER_DEVIATION){
		var requirement = {
			it: (parseInt(category.charAt(0)) >= avg)?9:0,
			business: (parseInt(category.charAt(1)) >= avg)?9:0,
			english: (parseInt(category.charAt(2)) >= avg)?9:0,
			skill: (parseInt(category.charAt(3)) >= avg)?9:0,
			family: (parseInt(category.charAt(4)) >= avg)?9:0,
			health: (parseInt(category.charAt(5)) >= avg)?9:0,
			art: (parseInt(category.charAt(6)) >= avg)?9:0,
			office: (parseInt(category.charAt(7)) >= avg)?9:0
		}
		Learners.getAdvanceRecommendations(learner_id, requirement, function(err, message){
			cb(err, message);
		});
	} else {
		Courses.getTop(function(err, message){
			cb(err, message);
		});
	}
}

Learners.getRecommendations = function(id, cb){
	Learners.findById(id, function(err, message){
		if (err){
			cb(err, null);
		} else {
			var requirementStr = message[0].requirement_category;
			Learners.calRequirementRcms(id, requirementStr, function(err, message){
				cb(err, message);
			});
		}
	});
}

Learners.findByUsername = function(username, cb){
	db.query('SELECT * FROM learners WHERE username = ?', username, function(err, message){
		cb(err, message);
	});
}

Learners.findById = function(id, cb){
	db.query('SELECT * FROM learners WHERE id = ?', id, function(err, message){
		cb(err, message);
	});
}

Learners.setTokenById = function(learner, cb){
	db.query('UPDATE learners SET ? WHERE ?',
	        [{token: learner.token}, {id: learner.id}], function(err, message){
	        	cb(err, message);
	    });
}

Learners.logout = function(id, cb){
	db.query('UPDATE learners SET ? WHERE ?',
	        [{token: null}, {id: id}], function(err, message){
	        	cb(err, message);
	    });
}

Learners.getHistory = function(id, cb){
	Enrollments.getByLearnerId(id, function(err, message){
		if (err){
			cb(err, null);
		} else {
			var idList = message;
			Courses.getAllCourses(function(err, message){
				if (err){
					cb(err, null);
				} else {
					var res = [];
					rawCourses = message;
					var courseHasID = {};
					// build tree
					var courses = [];
					for (var i = 0; i < rawCourses.length; i++){
						courseHasID[rawCourses[i].id] = rawCourses[i];
					}

					for (var i = 0; i < idList.length; i++){
						res.push(courseHasID[idList[i].course_id]);
					}
					cb(null, res);
				}
			});
		}
	});
}

Learners.getAdvanceRecommendations = function (learner_id, requirement, cb){
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

module.exports = Learners;