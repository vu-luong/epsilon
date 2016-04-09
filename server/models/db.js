var mysql = require('mysql');
var host = '127.0.0.1';
var port = 3306;
var user = 'root';
var password = '1';

var connection = mysql.createPool({
    host     : host,
    port     : port,
    user     : user,
    password : password,
    database  	: 'epsilon',
    timezone: 'gmt'
});


connection.query('SET NAMES `UTF8`', function(err, message){
	if (err) 
		console.log(err);
	else 
		console.log('Database encoding: UTF8');
});

module.exports = connection;