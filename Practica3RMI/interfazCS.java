
//Fichero: interfazCS.java

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface interfazCS extends Remote {

	public Integer registrarse(String nombre) throws RemoteException;

	public Integer donar(String nombre, int cantidad) throws RemoteException;

	public Integer totaldonado(String nombre) throws RemoteException;

}
