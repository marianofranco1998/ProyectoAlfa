package mx.itam.packages.proyectoalfa.client;

import mx.itam.packages.proyectoalfa.guis.LoginGUI;
import mx.itam.packages.proyectoalfa.interfaces.Authenticate;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.UnknownHostException;
import java.rmi.NotBoundException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class ClientLauncher {
    public static void main(String[] args) {

        // SETUP
        // Security Policy / Security Manager
        System.setProperty("java.security.policy","src/mx/itam/packages/proyectoalfa/client/client.policy");

        if(System.getSecurityManager() == null) {
            System.setSecurityManager(new SecurityManager());
        }

        // Initialize values
        String localIP = null;
        try {
            localIP = String.valueOf(InetAddress.getLocalHost());
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        InetAddress group;
        MulticastSocket mcSocket = null;

        // Retrieve data from LoginGUI
        LoginThread logger = new LoginThread();
        try {
            logger.start();
            logger.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String[] UserIP = {logger.getUser(), logger.getIp()};

        // RMI
        String[] mcData;
        try {
            String name = "Authenticate";
            Registry registry= LocateRegistry.getRegistry(Integer.parseInt(UserIP[1]));
            Authenticate auth= (Authenticate) registry.lookup(name);

            mcData = auth.register_authenticate(UserIP[0],UserIP[1]);

            // Multicast Setup
            group = InetAddress.getByName(mcData[0]);
            mcSocket = new MulticastSocket(Integer.parseInt(mcData[1]));
            mcSocket.joinGroup(group);

            // Game begins
            byte[] buffer = new byte[1000];
            System.out.println("Waiting for messages");
            DatagramPacket messageIn = new DatagramPacket(buffer, buffer.length);
            mcSocket.receive(messageIn);
            System.out.println("Message: " + new String(messageIn.getData()).trim() + " from: " + messageIn.getAddress());
            mcSocket.leaveGroup(group);

        } catch (NotBoundException | IOException e) {
            e.printStackTrace();
        } finally {
            if (mcSocket != null) mcSocket.close();
        }


    }
}

class LoginThread extends Thread {
    String user;
    String ip;
    LoginGUI logui;

    public String getUser() {
        return user;
    }

    public String getIp() {
        return ip;
    }

    public LoginThread() {
        logui = new LoginGUI();
    }

    public void run() {
        while (logui.getUser() == null) {
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        this.user = logui.getUser();
        this.ip = logui.getIp();
    }
}
