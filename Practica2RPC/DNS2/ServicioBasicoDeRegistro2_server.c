//José María Gómez García A2 DSD

/*
 * This is sample code generated by rpcgen.
 * These are only templates and you can use them
 * as a guideline for developing your own functions.
 */

#include "ServicioBasicoDeRegistro2.h"
#include <vector>
#include <string>
#include <iostream>
#include <sstream>

using namespace std;

char subRed = '2';
vector<pair<string, string> > direcciones;

string createIP() {

	string ip;
	int sub = direcciones.size() + 1;
	stringstream ss;
	ss << sub;

	ip.clear();
	ip += subRed;
	ip += '.';
	ip += ss.str();

	return ip;
}

char **
registra_2_svc(char *arg1,  struct svc_req *rqstp)
{
	static char *  result;
	int existe;

	string nombre(arg1);
	string ip("unknown");
	existe = 0;

	for (int i = 0; i < direcciones.size(); i++) { // or

		if (direcciones[i].first == arg1) {
			existe = 1;
			ip = direcciones[i].second;
		}
	}

	if (existe == 0) {

		ip = createIP();
		direcciones.push_back(pair<string,string>(nombre,ip));
	}

	result = strdup(ip.c_str());

	cout << "Registra " << direcciones.size() << " " << result << ": " << nombre << ", " << ip << endl;

	return &result;
}

int *
borra_2_svc(char *arg1,  struct svc_req *rqstp)
{
	static int  result;
	int donde;
	int encontrado;

	result = 0;
	donde = 0;
	encontrado = 0;

	for (int i = 0; i < direcciones.size(); i++) {
		if (direcciones[i].first == arg1) {
			encontrado = 1;
			donde = i;
		}
	}

	if (encontrado == 1) {
		direcciones.erase(direcciones.begin()+donde);
		result = 1;
	}

	cout << "Borra " << arg1 << ": " << result << endl;

	return &result;
}

char **
buscaip_2_svc(char *arg1,  struct svc_req *rqstp)
{
	static char * result;
	const string str = "No se ha encontrado";
	int encontrado;

	result = strdup(str.c_str());
	encontrado = 0;

	for (int i = 0; i < direcciones.size(); i++) {

		if (direcciones[i].first == arg1) {
			strcpy(result, direcciones[i].second.c_str());
			encontrado = 1;
		}
	}

	cout << "Buscaip " << arg1 << ": " << result << endl;

	return &result;
}

// EXÁMEN /////////////////////////////////////////////////////////////

char **
visualizartabladns_2_svc(struct svc_req *rqstp)
{
	static char * result;
	string tabla;

	tabla.clear();

	if (direcciones.size() > 0) {

		for (int i = 0; i < direcciones.size(); i++) {

		
			tabla += '\n';
			tabla += direcciones[i].first;	
			tabla += '\t';
			tabla += direcciones[i].second;
		}
	}
	else tabla = "no hay registros";

	result = strdup(tabla.c_str());

	return &result;
}

///////////////////////////////////////////////////////////////////////