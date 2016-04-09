var express = require('express');
var router = express.Router();
var Learners = require('../models/learners');
var hat = require('hat');

router.get('/', function(req, res){
	res.json({
		status: 'success',
		message: 'server tested ok'
	});
});

module.exports = router;