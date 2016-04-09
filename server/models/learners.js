var db = require('./db');
var Learners = {};

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