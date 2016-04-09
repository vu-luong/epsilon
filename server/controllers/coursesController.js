var express = require('express');
var router = express.Router();
var Courses = require('../models/courses');

router.get('/', function(req, res){
	res.json({
		message: "test"
	});
});

module.exports = router;