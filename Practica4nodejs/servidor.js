// José María Gómez García

var persiana = false;
var ac = false;
var nevera = false;

var http = require("http");
var url = require("url");
var fs = require("fs");
var path = require("path");
var socketio = require("socket.io");

var MongoClient = require('mongodb').MongoClient;
var MongoServer = require('mongodb').Server;
var mimeTypes = { "html": "text/html", "jpeg": "image/jpeg", "jpg": "image/jpeg", "png": "image/png", "js": "text/javascript", "css": "text/css", "swf": "application/x-shockwave-flash"};

var httpServer = http.createServer(
	
	function(request, response) {

		var uri = url.parse(request.url).pathname;

		if (uri=="/") uri = "/usuario.html";

		var fname = path.join(process.cwd(), uri);

		fs.exists(fname, function(exists) {

			if (exists) {

				fs.readFile(fname, function(err, data){

					if (!err) {

						var extension = path.extname(fname).split(".")[1];
						var mimeType = mimeTypes[extension];
						
						response.writeHead(200, mimeType);
						response.write(data);
						response.end();
					}
					else {

						response.writeHead(200, {"Content-Type": "text/plain"});
						response.write('Error de lectura en el fichero: '+uri);
						response.end();
					}
				});
			}
			else {

				console.log("Peticion invalida: "+uri);
				response.writeHead(200, {"Content-Type": "text/plain"});
				response.write('404 Not Found\n');
				response.end();
			}
		});
	}
);

MongoClient.connect("mongodb://localhost:27017/", function(err, db) {

	if (!err) {

		console.log("Conectado a Base de Datos");
	}

	var dbo = db.db("baseDatos");
	var msqCliente = null;
	
	httpServer.listen(8080);
	var io = socketio.listen(httpServer);

	dbo.createCollection("registro", function(err, collection) {

		if (!err) {

			console.log("Colección creada en Mongo: " + collection.collectionName);
		}
		else {

			console.log("Error al crear la colección en Mongo: " + collection.collectionName);
		}
	});

	io.sockets.on('connection', function(client) {

		client.on('nueva-temperatura', function(data) {

			dbo.collection("registro").insert(data, {safe:true}, function(err, result) {
					
				if (!err) {

					console.log("Insertar en registro: temperatura = " + data.temp + " date = " + data.d);
				}
				else {

					console.log("Error al insertar en registro");
				}
			});

			io.sockets.emit('actualizar-temperatura', data.temp);
		});

		client.on('nueva-luminosidad', function(data) {

			dbo.collection("registro").insert(data, {safe:true}, function(err, result) {
					
				if (!err) {

					console.log("Insertar en registro: luminosidad = " + data.lumi + " date = " + data.d);
				}
				else {

					console.log("Error al insertar en registro");
				}
			});

			io.sockets.emit('actualizar-luminosidad', data.lumi);
		});

// EXÁMEN /////////////////////////////////////////////////////////////////////

		client.on('nuevo-alimentosnevera', function(data) {

			dbo.collection("registro").insert(data, {safe:true}, function(err, result) {
					
				if (!err) {

					console.log("Insertar en registro: alimentosnevera = " + data.alimnevera + " date = " + data.d);
				}
				else {

					console.log("Error al insertar en registro");
				}
			});

			io.sockets.emit('actualizar-alimentosnevera', data.alimnevera);
		});

///////////////////////////////////////////////////////////////////////////////

		client.on('obtener-persiana', function() {

			client.emit('actualizar-persiana', persiana);
		});

		client.on('obtener-ac', function() {

			client.emit('actualizar-ac', ac);
		});

// EXÁMEN ////////////////////////////////////////////////////////////////////////

		client.on('obtener-nevera', function() {

			client.emit('actualizar-nevera', nevera);
		});

//////////////////////////////////////////////////////////////////////////////////

		client.on('boton-persiana', function() {

			persiana = !persiana;

			io.sockets.emit('actualizar-persiana', persiana);
		});

		client.on('boton-ac', function() {

			ac = !ac;

			io.sockets.emit('actualizar-ac', ac);
		});

// EXÁMEN ////////////////////////////////////////////////////////////////////////

		client.on('boton-nevera', function() {

			nevera = !nevera;

			io.sockets.emit('actualizar-nevera', nevera);
		});

//////////////////////////////////////////////////////////////////////////////////

		client.on('cerrar-persiana', function() {

			if (persiana == true) {

				persiana = false;

				io.sockets.emit('actualizar-persiana', persiana);
			}
		});

// EXÁMEN ///////////////////////////////////////////////////////////////////////

		client.on('nevera-bajoconsumo', function() {

			if (nevera == false) {

				nevera = true;

				io.sockets.emit('actualizar-nevera', nevera);
			}
		});

		client.on('nevera-normal', function() {

			if (nevera == true) {

				nevera = false;

				io.sockets.emit('actualizar-nevera', nevera);
			}
		});

///////////////////////////////////////////////////////////////////////////////////

		client.on('alerta-temperatura', function(data) {

			io.sockets.emit('actualizar-alerta-temperatura', data);
		});

		client.on('alerta-luminosidad', function(data) {

			io.sockets.emit('actualizar-alerta-luminosidad', data);
		});

// EXÁMEN ///////////////////////////////////////////////////////////////////

		client.on('alerta-alimentosnevera', function(data) {

			io.sockets.emit('actualizar-alerta-alimentosnevera', data);
		});

/////////////////////////////////////////////////////////////////////////////

		client.on('temperatura-en-rango', function() {
			
			io.sockets.emit('quitar-alerta-temperatura');
		});

		client.on('luminosidad-en-rango', function() {
			
			io.sockets.emit('quitar-alerta-luminosidad');
		});

// EXÁMEN ////////////////////////////////////////////////////////////////////

		client.on('alimentosnevera-en-rango', function() {
			
			io.sockets.emit('quitar-alerta-alimentosnevera');
		});

///////////////////////////////////////////////////////////////////////////////

	});
});

console.log("Servicio Socket.io iniciado");
