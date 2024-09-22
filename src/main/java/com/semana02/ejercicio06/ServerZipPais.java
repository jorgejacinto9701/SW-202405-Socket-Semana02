package com.semana02.ejercicio06;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerZipPais {

    private final int PORT = 13;

    public ServerZipPais() {
        System.out.println("1 Server started");

        try {
            Socket clientSocket = null;
            ServerSocket serverSocket = new ServerSocket(PORT);
            System.out.println("2 Server waiting for client");
            while (true) {
                System.out.println("2 > A la espera de un cliente");
                clientSocket = serverSocket.accept();
                System.out.println("3 > New client connected: " + clientSocket.getInetAddress().getHostAddress());

                // Fllujos para enviar y recibir archivos
                File file = new File("C:/server/paisComprimidoEnServer.zip");
                FileOutputStream fos = new FileOutputStream(file);
                DataInputStream entrada = new DataInputStream(clientSocket.getInputStream());

                // recibir archivo
                int byteLeidos = 0;
                while ((byteLeidos = entrada.read()) != -1) {
                    fos.write(byteLeidos);
                }
                System.out.println("3 File received");

                fos.close();
                entrada.close();

                clientSocket.close();
                System.out.println("4 Server finished");
            }
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        new ServerZipPais();
    }
}
