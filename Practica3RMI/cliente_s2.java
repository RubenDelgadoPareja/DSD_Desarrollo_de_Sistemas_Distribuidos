
// Fichero: cliente_s2.java

// Código del cliente 

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class cliente_s2 {

	public static void main(String args[]) {

		Integer result = 0;

		if (System.getSecurityManager() == null) {
			System.setSecurityManager(new SecurityManager());
		}
		try{
			String nombre_objeto_remoto = "servidor_2";
			System.out.println("Buscando el objeto remoto");
			Registry registry = LocateRegistry.getRegistry(args[0]);
			interfazCS instancia_local = (interfazCS) registry.lookup(nombre_objeto_remoto);
			System.out.println("Invocando el objeto remoto");

			switch(args[1]) {

				case "registra":

					System.out.println("CS2 Registro: "+args[2]);
					result = instancia_local.registrarse(args[2]);
					if (result == 0) {
						System.out.println("Ya está registrado");
					}
					else System.out.println("Éxito");
					break;

				case "dona":

					System.out.println("CS2 Dona: "+args[2]);
					result = instancia_local.donar(args[2], Integer.parseInt(args[3]));
					if (result == 0) {
						System.out.println("No está registrado");
					}
					else System.out.println("Éxito");
					break;

				case "totaldonado":

					System.out.println("CS2 Totaldonado: "+args[2]);
					result = instancia_local.totaldonado(args[2]);
					if (result == 0) {
						System.out.println("No está registrado ó no ha donado nada");
					}
					else System.out.println("Éxito: "+result);
					break;

				default:

					System.out.println("CS2 Operación no permitida: "+args[1]);
					break;

			}
		
		} catch (Exception e) {
			System.err.println("CS2 Excepción:");
			e.printStackTrace();
		}
	}
}
