var express = require('express');
const app = express();
const http = require('http');
const path = require('path');
const port = process.env.PORT || 8080;

app.use(express.static(__dirname + '/app'));

//const corsMiddleware = cors();
/* app.use(function(req, res, next) {
    res.setHeader('Access-Control-Allow-Origin', '*');
    next()
}); */


app.get('/*', (req, res) => res.sendFile(path.join(__dirname)));

const server = http.createServer(app);

server.listen(8080, () => console.log(`App running on: http://localhost:${port}`));
