var db = require('./db');
var Blacklist = {};

Blacklist.add = function(course1, course2, cb){
	var data = {
		course1: course1,
		course2: course2
	}
	db.query('INSERT INTO blacklist SET ?', data, function(err, message){
			cb(err, message);
		});
};

Blacklist.getAll = function(cb){
	db.query('SELECT * FROM blacklist', function(err, message){
			cb(err, message);
		});
};

module.exports = Blacklist;