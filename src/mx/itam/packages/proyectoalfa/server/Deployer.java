package mx.itam.packages.proyectoalfa.server;

import mx.itam.packages.proyectoalfa.interfaces.Authenticate;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class Deployer {

    public static void main(String[] args) {
        // Creación de Usuarios del Juego
        String[] usernameList = {"User1", "User2", "User3", "User4", "User5"};
        String[] passwordList = {"User1", "User2", "User3", "User4", "User5"};

        System.setProperty("java.security.policy","src/mx/itam/packages/proyectoalfa/server/server.policy");

        if(System.getSecurityManager() == null) {
            System.setSecurityManager(new SecurityManager());
        }

        try {
            Registry registry = LocateRegistry.createRegistry(1099);

            String name = "Authenticate";

            RMINode engine = new RMINode(usernameList,passwordList);

            Authenticate stub = (Authenticate) UnicastRemoteObject.exportObject(engine, 0);

            registry.rebind(name, stub);


        } catch (RemoteException e) {
            e.printStackTrace();
        }

    }
}
