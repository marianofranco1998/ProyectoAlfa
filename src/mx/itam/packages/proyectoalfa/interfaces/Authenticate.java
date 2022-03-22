package mx.itam.packages.proyectoalfa.interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Authenticate extends Remote {

    public String[] register_authenticate(String user, String ip) throws RemoteException;

}
