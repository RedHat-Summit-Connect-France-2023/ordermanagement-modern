var express = require('express');
const cors = require('cors');
var app = express();

app.use(express.static("app")); // myApp will be the same folder name.
const corsMiddleware = cors();
app.use(function(req, res, next) {
    corsMiddleware(req, res, next);
});
app.get('/', function (req, res,next) {
 res.redirect('/'); 
});
app.listen(8080, 'localhost');
console.log("frontend Server is Listening on port 8080");