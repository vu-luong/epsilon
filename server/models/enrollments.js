var db = require('./db');
var Enrollments = {};

Enrollments.addEnrollment = function(enrollment, cb){
	db.query('INSERT INTO enrollments SET ?', enrollment, function(err, message){
			cb(err, message);
		});
};

Enrollments.getByLearnerId = function(learner_id, cb){
	db.query('SELECT course_id FROM enrollments WHERE learner_id = ?', learner_id, function(err, message){
			cb(err, message);
		});
}

Enrollments.check = function(learner_id, course_id, cb){
	db.query('SELECT * FROM enrollments WHERE learner_id = ? AND course_id = ?',
		[learner_id, course_id] , function(err, message){
			cb(err, message);
		});
}
module.exports = Enrollments;