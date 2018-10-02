var createError = require('http-errors');
var express = require('express');
var path = require('path');
var cookieParser = require('cookie-parser');
const morgan = require('morgan');
const bodyParser = require('body-parser');
const mongoose = require('mongoose');


var indexRouter = require('./routes/index');
var usersRouter = require('./routes/users');

var app = express();

try{
    mongoose.connect(
        'mongodb+srv://amad_user:adminPwd@amad-customer-jvsta.mongodb.net/test?retryWrites=true',
        {
            useNewUrlParser:true
        }
    ).then(()=>{
      console.log("Mongo DB connected successfully...");
    });
    mongoose.set('useCreateIndex', true);
}catch(error){
    return res.status(401).json({
        message : 'Server connect fail'
    });
}

//Applying the the morgan and body parser here.enabling url-encoded and json formats
app.use(morgan('dev'));
app.use(bodyParser.urlencoded({extended: false}));
app.use(bodyParser.json());

//Applying CORS- giving access to any client
app.use((req,res,next) => {
    res.header('Access-Control-Allow-Origin','*');
    res.header('Access-Control-Allow-Origin','Origin, X-Requested-With, Content-Type, Accept, Authorization');
    if(req.method === 'OPTIONS'){
        req.header('Access-Control-Allow-Methods', 'PUT, POST, PATCH, DELETE, GET, OPTIONS');
        return res.status(200).json({})
    }
    next();
});
//Closing CORS. Always add before the routes.


// view engine setup
app.set('views', path.join(__dirname, 'views'));
app.set('view engine', 'pug');

app.use(express.json());
app.use(express.urlencoded({ extended: false }));
app.use(cookieParser());
app.use(express.static(path.join(__dirname, 'public')));

app.use('/', indexRouter);
app.use('/users', usersRouter);

// catch 404 and forward to error handler
app.use(function(req, res, next) {
  next(createError(404));
});

// error handler
app.use(function(err, req, res, next) {
  // set locals, only providing error in development
  res.locals.message = err.message;
  res.locals.error = req.app.get('env') === 'development' ? err : {};

  // render the error page
  res.status(err.status || 500);
  res.render('error');
});

module.exports = app;
