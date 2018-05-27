
//Fichero: servidor_1.java

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.lang.Thread;
import java.util.HashMap;
import java.lang.String;

public class servidor_1 extends auxInterfazSS implements interfazCS {

	String segundo_servidor = new String();
	String nombre_interfazss = new String();

	public servidor_1() {

		super();
		this.segundo_servidor = "localhost";
		this.nombre_interfazss = "servidor_2";
		this.nombre_servidor = "S1";
	}

	@Override
	public Integer registrarse(String nombre) {

		Integer result;
		result = 0;

		System.out.println(this.nombre_servidor+" CS P. de registro: "+nombre);

		if (!this.registro.containsKey(nombre)) {

			try {
				Registry registry = LocateRegistry.getRegistry(this.segundo_servidor);

				interfazSS instancia_local = (interfazSS) registry.lookup(this.nombre_interfazss);
				
				if (instancia_local.siRegistradoSS(nombre) == 0) {

					if (instancia_local.cantidadRegistradosSS() < this.registro.size()) {

						result = instancia_local.registraSS(nombre);
					}
					else {

						this.registro.put(nombre, 0);
						result = 1;
					}
				}
			}
			catch (Exception e) {
				System.err.println(this.nombre_servidor+" Excepción:");
				e.printStackTrace();
			}
		}

		System.out.println("Resultado: "+result);
		return result;
	}

	@Override
	public Integer donar(String nombre, int cantidad) {

		Integer result;
		result = 0;

		System.out.println(this.nombre_servidor+" CS P. de donar: "+nombre+" "+cantidad);

		if (this.registro.containsKey(nombre)) {

			this.registro.put(nombre,this.registro.get(nombre)+cantidad);

// EXAMEN ///////////////////////////////////////////////

			this.totaldonado += cantidad;

/////////////////////////////////////////////////////////

			result = 1;
		}
		else {

			try {

				Registry registry = LocateRegistry.getRegistry(this.segundo_servidor);

				interfazSS instancia_local = (interfazSS) registry.lookup(this.nombre_interfazss);

				if (instancia_local.siRegistradoSS(nombre) != 0) {

					result = instancia_local.donaSS(nombre, cantidad);

// EXAMEN //////////////////////////////////////////////////

					this.totaldonado += cantidad;

////////////////////////////////////////////////////////////

				}
			}
			catch (Exception e) {
				System.err.println(this.nombre_servidor+" Excepción:");
				e.printStackTrace();
			}
		}

		System.out.println("Resultado: "+result);
		return result;
	}

	@Override
	public Integer totaldonado(String nombre) {

		Integer result;
		result = 0;

		System.out.println(this.nombre_servidor+" CS P. de totaldonado: "+nombre);

		if (this.registro.containsKey(nombre)) {

// EXAMEN ////////////////////////////////////////////

			if (this.registro.get(nombre) > 0) {

				result = this.totaldonado;
			}

// EXAMEN ////////////////////////////////////////////

		}
		else {

			try {

				Registry registry = LocateRegistry.getRegistry(this.segundo_servidor);

				interfazSS instancia_local = (interfazSS) registry.lookup(this.nombre_interfazss);

				if (instancia_local.siRegistradoSS(nombre) != 0) {

// EXAMEN /////////////////////////////////////////////

					if (instancia_local.getdonado(nombre) > 0) {

						result = this.totaldonado;
					}

//////////////////////////////////////////////////////

				}
			}
			catch (Exception e) {
				System.err.println(this.nombre_servidor+" Excepción:");
				e.printStackTrace();
			}
		}

		System.out.println("Resultado: "+result);
		return result;
	}

	public static void main(String[] args) {
		if(System.getSecurityManager() == null) {
			System.setSecurityManager(new SecurityManager());
		}
		try{
			String nombre_objeto_remoto = "servidor_1";
			interfazCS prueba = new servidor_1();
			interfazCS stub = (interfazCS) UnicastRemoteObject.exportObject(prueba, 0);
			Registry registry = LocateRegistry.getRegistry();
			registry.rebind(nombre_objeto_remoto, stub);

			System.out.println("S1 Corriendo");
		} 
		catch (Exception e) {
			System.err.println("S1 Excepción:");
			e.printStackTrace();
		}
	}
}
