package mx.itam.packages.proyectoalfa.server;

import mx.itam.packages.proyectoalfa.classes.Player;
import mx.itam.packages.proyectoalfa.interfaces.Authenticate;

import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.Objects;

public class RMINode implements Authenticate {
    public Player[] playerUsers;
    public HashMap<String,Player> lookup;

    public RMINode(String[] users, String[] passwords) throws RemoteException{
        super();

        this.playerUsers = new Player[users.length];
        this.lookup = new HashMap<String,Player>();
        for(int i = 0; i < users.length; i++) {
            this.playerUsers[i] = new Player(users[i], passwords[i]);
            this.lookup.put(users[i],this.playerUsers[i]);
        }
    }

    @Override
    public boolean authenticate(String user, String password) throws RemoteException {
        Player username = this.lookup.get(user);
        System.out.println(username.getUser() + " " + username.getUser() + " " + username.isPlaying());
        if (username != null ) {
            System.out.println("Pase el primer if");
            System.out.println(username.getPassword() + " " + password);
            if (Objects.equals(username.getPassword(), password) && !username.isPlaying()) {
                System.out.println("Pase el segundo if");
                username.setPlaying(true);
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }
}
