var db = require('./db');
var Enrollments = {};

Enrollments.addEnrollment = function(enrollment, cb){
	db.query('INSERT INTO enrollments SET ?', enrollment, function(err, message){
			cb(err, message);
		});
};

module.exports = Enrollments;