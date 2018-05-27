//Fichero: interfazSS.java

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface interfazSS extends Remote {

	public Integer siRegistradoSS(String nombre) throws RemoteException;

	public Integer cantidadRegistradosSS() throws RemoteException;

	public Integer registraSS(String nombre) throws RemoteException;

	public Integer donaSS(String nombre, int cantidad) throws RemoteException;

// EXAMEN /////////////////////////////////////

	public Integer getdonado(String nombre) throws RemoteException;

	public Integer gettotaldonadoSS() throws RemoteException;

	public Integer sumtotaldonadoSS(Integer cantidad) throws RemoteException;

////////////////////////////////////////////////
}
