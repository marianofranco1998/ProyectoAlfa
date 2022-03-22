package mx.itam.packages.proyectoalfa.guis;

import mx.itam.packages.proyectoalfa.client.TCPClient;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class GameGUI extends JFrame implements ActionListener {

    TCPClient clientPlayer;
    String[] server;

    public GameGUI(String[] server){
        super("Alpha Game");
        this.server = server;
        this.clientPlayer = new TCPClient();
        placeComponents();
    }

    private void placeComponents() {
        setSize(300, 300);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel grid = new JPanel(new GridLayout(0,3,10,10));
        add(grid);

        for(int i = 1; i < 10; i++){
            JCheckBox c = new JCheckBox("Celda " + String.valueOf(i));
            c.addActionListener(this);
            c.setActionCommand(String.valueOf(i));
            grid.add(c);
        }
        setVisible(true);
    }

    private void placeComponents(int j) {
        setSize(300, 300);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel grid = new JPanel(new GridLayout(0,3,10,10));
        add(grid);

        for(int i = 1; i < 10; i++){
            JCheckBox c = new JCheckBox("Celda " + String.valueOf(i),i==j);
            c.addActionListener(this);
            c.setActionCommand(String.valueOf(i));
            grid.add(c);
        }
        setVisible(true);
    }

    public static void main(String[] args) {
        String[] argument = new String[]{"localhost","49152"};
        GameGUI go = new GameGUI(argument);
        go.placeComponents(2);
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        go.placeComponents(2);
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        go.placeComponents(3);
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        go.placeComponents(9);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for(int i = 1; i < 10; i++) {
            if(Objects.equals(String.valueOf(e.getActionCommand()), String.valueOf(i))) {
                System.out.println("Mandamos "+ i + " por TCP a " + server[0] + ", Port: " + server[1]);
                clientPlayer.execute(String.valueOf(i),server);
            }
        }
    }
}
