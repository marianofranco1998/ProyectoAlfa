package mx.itam.packages.proyectoalfa.client;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;

public class TCPClient {

    public void execute(String cell, String[] server) {

        Socket s = null;

        try {
            s = new Socket(server[0], Integer.parseInt(server[1]));
            DataOutputStream out = new DataOutputStream(s.getOutputStream());

            out.writeUTF(cell);            // UTF is a string

        } catch (UnknownHostException e) {
            System.out.println("Sock:" + e.getMessage());
        } catch (EOFException e) {
            System.out.println("EOF:" + e.getMessage());
        } catch (IOException e) {
            System.out.println("IO:" + e.getMessage());
        } finally {
            if (s != null) try {
                s.close();
            } catch (IOException e) {
                System.out.println("close:" + e.getMessage());
            }
        }
    }
}
