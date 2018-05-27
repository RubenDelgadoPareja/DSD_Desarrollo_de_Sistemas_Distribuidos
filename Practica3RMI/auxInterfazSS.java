
//Fichero: auxInterfazSS.java

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.lang.Thread;
import java.util.HashMap;
import java.lang.String;

public class auxInterfazSS implements interfazSS {

	HashMap<String, Integer> registro = new HashMap<String, Integer>();
	String nombre_servidor = new String();

// EXAMEN //////////////////////////////

	Integer totaldonado;

////////////////////////////////////////

	public auxInterfazSS() {
		super();
		this.nombre_servidor = "S";

// EXAMEN //////////////////////////////

		this.totaldonado = 0;

////////////////////////////////////////
	}

	public Integer siRegistradoSS (String nombre) {

		Integer result;
		result = 0;

		System.out.println("\t"+this.nombre_servidor+" SS P. de siRegistrado: "+nombre);

		if (this.registro.containsKey(nombre)) {
			result = 1;
		}

		System.out.println("\tResultado: "+result);
		return result;
	}

	public Integer cantidadRegistradosSS () {

		Integer result;
		result = 0;

		System.out.println("\t"+this.nombre_servidor+" SS P. de cantidadRegistrados");

		result = this.registro.size();

		System.out.println("\tResultado: "+result);
		return result;
	}

	public Integer registraSS (String nombre) {

		Integer result;
		result = 0;

		System.out.println("\t"+this.nombre_servidor+" SS P. de registra: "+nombre);

		if (!this.registro.containsKey(nombre)) {

			this.registro.put(nombre, 0);
			result = 1;
		}

		System.out.println("\tResultado: "+result);
		return result;
	}

	public Integer donaSS (String nombre, int cantidad) {

		Integer result;
		Integer suma;
		result = 0;
		suma = 0;

		System.out.println("\t"+this.nombre_servidor+" SS P. de dona: "+nombre+" "+cantidad);

		if (this.registro.containsKey(nombre)) {

			suma = this.registro.get(nombre) + cantidad;
			this.registro.put(nombre, suma);
			result = 1;
		}

		System.out.println("\tResultado: "+result);
		return result;
	}

// EXAMEN //////////////////////////////////////

	public Integer gettotaldonadoSS() {

		System.out.println("\t"+this.nombre_servidor+" SS P. de gettotaldonado: ");

		System.out.println("\tResultado: "+this.totaldonado);

		return this.totaldonado;
	}

	public Integer sumtotaldonadoSS(Integer cantidad) {

		System.out.println("\t"+this.nombre_servidor+" SS P. de sumtoatldonado: "+cantidad);

		this.totaldonado += cantidad;

		System.out.println("\tResultado: "+this.totaldonado);

		return 0;
	}

	public Integer getdonado(String nombre) {

		System.out.println("\t"+this.nombre_servidor+" SS P. de getdonado: "+nombre);

		System.out.println("\tResultado: "+this.registro.get(nombre));

		return this.registro.get(nombre);
	}

////////////////////////////////////////////////

}
