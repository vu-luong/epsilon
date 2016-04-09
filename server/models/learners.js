var db = require('./db');
var Learners = {};
var Recommender = require('../utils/recommendEngine');

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

Learners.calRequirementRcms = function(initCategory){
	// scale
	var TRIGGER_DEVIATION = 8; // TODO improve it
	var deviation = 0;
	var avg = 0;
	for (var i = 0; i < initCategory.length; i++){
		avg += parseInt(initCategory.charAt(i));
	}
	avg /= initCategory.length;
	for (var i = 0; i < initCategory.length; i++){
		var value = parseInt(initCategory.charAt(i));
		deviation += (value - avg) * (value - avg);
	}
	if (deviation >= TRIGGER_DEVIATION){
		var requirement {
			it: (parseInt(initCategory.charAt(0)) >= avg)?9:0,
			business: (parseInt(initCategory.charAt(1)) >= avg)?9:0,
			english: (parseInt(initCategory.charAt(2)) >= avg)?9:0,
			skill: (parseInt(initCategory.charAt(3)) >= avg)?9:0,
			family: (parseInt(initCategory.charAt(4)) >= avg)?9:0,
			health: (parseInt(initCategory.charAt(5)) >= avg)?9:0,
			art: (parseInt(initCategory.charAt(6)) >= avg)?9:0,
			office: (parseInt(initCategory.charAt(7)) >= avg)?9:0
		}
		Recommender.getRecommendations(requirement, function(err, message){
			if (err){
				console.log('error when get all courses');
				console.log(err);
			} else {

			}
		});
	} else {
		return null;
	}
}

Learners.findByUsername = function(username, cb){
	db.query('SELECT * FROM learners WHERE username = ?', username, function(err, message){
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


module.exports = Learners;