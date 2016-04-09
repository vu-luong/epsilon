var db = require('./db');
var Supports = {};
var Learners = require('../models/learners');

Supports.increaseBothAndOrder = function(first_id, second_id, cb){
	db.query('SELECT * FROM supports WHERE course1 = ? AND course2 = ?', 
		[first_id, second_id], function(err, message){
			if (err){
				cb(err, null);
			} else {
				if (message.length > 0){
					var id = message[0].id;
					var both = message[0].both;
					var order = message[0].order;
					both = both + 1;
					order = order + 1;
					prior = order * 100 / both;
					var support = {};
					support.both = both;
					support.order = order;
					support.prior = prior;
					db.query('UPDATE supports SET ? WHERE ?',
					        [support, {id: id}], function(err, message){
							cb(err, message);
					    });
				} else {
					cb('support not found 2', null);
				}
			}
		});
}

Supports.increaseBoth = function(first_id, second_id, cb){
	db.query('SELECT * FROM supports WHERE course1 = ? AND course2 = ?', 
		[first_id, second_id], function(err, message){
			if (err){
				cb(err, null);
			} else {
				if (message.length > 0){
					var id = message[0].id;
					var both = message[0].both;
					var order = message[0].order;
					both = both + 1;
					prior = order * 100 / both;
					var support = {};
					support.both = both;
					support.prior = prior;
					db.query('UPDATE supports SET ? WHERE ?',
					        [support, {id: id}], function(err, message){
							cb(err, message);
					    });
				} else {
					cb('support not found 1', null);
				}
			}
		});
}

Supports.update = function(first_id, second_id, cb){

	Supports.increaseBothAndOrder(first_id, second_id, function(err, message){
		cb(err, message);
	});
	Supports.increaseBoth(second_id, first_id, function(err, message){
		cb(err, message);
	});
}

Supports.enroll = function(learner_id, new_course_id, cb){
	Learners.getHistory(learner_id, function(err, message){
		if (err){
			cb(err, null);
		} else {
			var courses = message;
			for (var i = 0; i < courses.length; i++){
				var course = courses[i];
				if (course.id != new_course_id){
					Supports.update(course.id, new_course_id, function(err, message){
						cb(err, message);
					});
				}
			}
		}
	});
}

module.exports = Supports;
