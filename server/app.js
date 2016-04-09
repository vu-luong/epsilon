var express = require('express');
var path = require('path');

// middleware between request and response
var logger = require('morgan');
var bodyParser = require('body-parser');

var app = express();


// add middleware
app.use(logger('dev'));
app.use(bodyParser.json());
app.use(bodyParser.urlencoded({extended: false}));

// declare controllers
var learnersController = require('./controllers/learnersController');
var coursesController = require('./controllers/coursesController');
var categoriesController = require('./controllers/categoriesController');

// cross domain config
app.use(function (req, res, next) {
    console.log('Client request:');
    console.log(req.body);
    console.log('--------------');
    res.header("Access-Control-Allow-Origin", "*");
    res.header("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept");
    next();
});

// TODO:add controller
app.use('/learners', learnersController);
app.use('/courses', coursesController);
app.use('/categories', categoriesController);

// Web-client
app.use('/', express.static(path.join(__dirname, 'client')));

// if request doesn't get routed, create a 404 and forward to error handler
app.use(function (req, res, next) {
    var err = new Error('Not Found');
    err.status = 404;
    next(err);
});

if (app.get('env') === 'development') {
    app.use(function (err, req, res, next) {
        console.log("Dev");
        res.status(err.status || 500);
        res.json({
            status: "error",
            message: err.status + " " + String(err)
        });
    });
} else {
    // production error handler
    app.use(function (err, req, res, next) {
        res.status(err.status || 500);
        res.send("Not found");
    });
}

module.exports = app;
