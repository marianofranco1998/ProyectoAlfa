package mx.itam.packages.proyectoalfa.client;

import mx.itam.packages.proyectoalfa.guis.LoginGUI;
import mx.itam.packages.proyectoalfa.interfaces.Authenticate;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.AccessException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class ClientLauncher {
    public static void main(String[] args) {
        System.setProperty("java.security.policy","src/mx/itam/packages/proyectoalfa/client/client.policy");

        if(System.getSecurityManager() == null) {
            System.setSecurityManager(new SecurityManager());
        }

        try {
            String name = "Authenticate";
            Registry registry= LocateRegistry.getRegistry("localhost"); // server's ipaddress
            Authenticate auth= (Authenticate) registry.lookup(name);

            LoginThread login = new LoginThread(auth);

            login.start();


        } catch (NotBoundException | RemoteException e) {
            e.printStackTrace();
        }
    }
}

class LoginThread extends Thread {
    Authenticate auth;

    public LoginThread(Authenticate auth) {
        this.auth = auth;
    }

    public void run() {
        JFrame frame = new JFrame("Proyecto Alfa Login");
        frame.setSize(350, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel panel = new JPanel();
        frame.add(panel);
        placeComponents(panel);
        frame.setVisible(true);
    }

    private void placeComponents(JPanel panel) {
        panel.setLayout(null);
        JLabel userLabel = new JLabel("User");
        userLabel.setBounds(10,20,80,25);
        panel.add(userLabel);

        JTextField userText = new JTextField(20);
        userText.setBounds(100,20,165,25);
        panel.add(userText);

        JLabel passwordLabel = new JLabel("Password");
        passwordLabel.setBounds(10,50,80,25);
        panel.add(passwordLabel);

        JPasswordField passwordText = new JPasswordField(20);
        passwordText.setBounds(100,50,165,25);
        panel.add(passwordText);

        JButton loginButton = new JButton("login");
        loginButton.setBounds(10, 80, 80, 25);
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String user = userText.getText();
                    String password = passwordText.getText();

                    System.out.println("Authenticate #1 " + auth.authenticate(user,password));

                } catch (RemoteException error) {
                    error.printStackTrace();
                }
            }
        });
        panel.add(loginButton);
    }

}
