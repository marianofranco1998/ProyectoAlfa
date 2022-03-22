package mx.itam.packages.proyectoalfa.server;

import mx.itam.packages.proyectoalfa.classes.Player;
import mx.itam.packages.proyectoalfa.interfaces.Authenticate;

import java.rmi.RemoteException;
import java.util.HashMap;

public class RMINode implements Authenticate {
    public Player[] playerUsers;
    public HashMap<String,Player> lookup;
    public String[] mcIPandPort;

    public RMINode(String mcIP, String mcPort) throws RemoteException{
        super();
        this.playerUsers = new Player[10];
        this.lookup = new HashMap<>();
        this.mcIPandPort =  new String[]{mcIP, mcPort};
    }

    @Override
    public String[] register_authenticate(String user, String ip) throws RemoteException {
        Player username = this.lookup.get(user);    // Look for user
        if (username != null ) {                    // If user already in Hashmap then
            if (!username.isPlaying()) {                // If user isn't playing
                username.setPlaying(true);                      // Set user as playing
                return this.mcIPandPort;

            } else {
                return new String[]{null, null};
            }

        } else {                                    // If user not in Hashmap then
            this.lookup.put(user, new Player(user, ip, true, 0));   // We add new a Player object to Hashmap
            return this.mcIPandPort;
        }
    }
}
