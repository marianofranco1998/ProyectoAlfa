package mx.itam.packages.proyectoalfa.interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Authenticate extends Remote {

    public boolean authenticate(String user, String password) throws RemoteException;

}
