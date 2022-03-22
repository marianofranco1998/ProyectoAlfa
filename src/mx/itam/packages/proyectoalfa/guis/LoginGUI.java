package mx.itam.packages.proyectoalfa.guis;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginGUI extends JFrame {
    private String user = null;
    private String ip;

    public LoginGUI() {
        super("Alpha Login");
        placeComponents();
    }

    private void placeComponents() {
        setSize(350, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel panel = new JPanel();
        add(panel);

        panel.setLayout(null);
        JLabel userLabel = new JLabel("Usuario:");
        userLabel.setBounds(10,20,80,25);
        panel.add(userLabel);

        JTextField userText = new JTextField(20);
        userText.setBounds(100,20,165,25);
        panel.add(userText);

        JLabel ipLabel = new JLabel("IP: ");
        ipLabel.setBounds(10,50,80,25);
        panel.add(ipLabel);

        JTextField ipText = new JTextField(20);
        ipText.setBounds(100,50,165,25);
        panel.add(ipText);

        JButton loginButton = new JButton("login");
        loginButton.setBounds(10, 80, 80, 25);
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loginSet(e, userText.getText(), ipText.getText());
            }
        });

        panel.add(loginButton);
        setVisible(true);
    }

    private void loginSet(ActionEvent e, String user, String ip) {
            this.user = user;
            this.ip = ip;
    }

    public String getUser() {
        return user;
    }

    public String getIp() {
        return ip;
    }
}
