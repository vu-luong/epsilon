var db = require('../models/db');
var Courses = require('../models/courses');

Courses.getAllCourses(function(err, message){
	if (err){
		console.log(err);
	} else {
		courses = message;
		for (var i = 0; i < courses.length; i++){
			for (var j = 0; j < courses.length; j++){
				if (i == j) continue;
				var data = {
					course1: courses[i].id,
					course2: courses[j].id
				};
				db.query('INSERT INTO supports SET ?', data, function(err, message){
						if (err) {
							console.log(err);
						}
					});
			}
		}
	}
});