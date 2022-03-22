package mx.itam.packages.proyectoalfa.server;

import mx.itam.packages.proyectoalfa.interfaces.Authenticate;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.UnknownHostException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Date;

public class Deployer {

    public static void main(String[] args) {
        // Creación de Usuarios del Juego
        String[] usernameList;
        String[] passwordList;

        // SETUP
        // Security Policy / Security Manager
        System.setProperty("java.security.policy", "src/mx/itam/packages/proyectoalfa/server/server.policy");
        if (System.getSecurityManager() == null) {
            System.setSecurityManager(new SecurityManager());
        }

        // RMI and Multicast
        MulticastSocket socket = null;
        try {
            Registry registry = LocateRegistry.createRegistry(1099);

            String name = "Authenticate";

            // Multicast things
            String mcIP = "228.5.6.7";
            String mcPort = "49155";

            RMINode engine = new RMINode(mcIP, mcPort);

            Authenticate stub = (Authenticate) UnicastRemoteObject.exportObject(engine, 0);

            registry.rebind(name, stub);

            // Multicast things
            InetAddress group = InetAddress.getByName(mcIP); // destination multicast group
            socket = new MulticastSocket(Integer.parseInt(mcPort));
            socket.joinGroup(group);
            //s.setTimeToLive(10);
            System.out.println("Messages' TTL (Time-To-Live): " + socket.getTimeToLive());

            for (int i = 0; i < 100; i++) {
                String currentDate = (new Date()).toString();
                byte[] m = currentDate.getBytes();
                DatagramPacket messageOut =
                        new DatagramPacket(m, m.length, group, 49155);
                socket.send(messageOut);
                Thread.sleep(2000);
            }
            socket.leaveGroup(group);

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        } finally {
            if (socket != null) socket.close();
        }

    }
}
