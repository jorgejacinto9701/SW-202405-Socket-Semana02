package com.semana02.ejercicio06;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.Socket;

public class ClienteZipPais {
    
    private final int PORT = 13;
    private final String HOST = "localhost";

    public ClienteZipPais() {
        GenerarZip gz = new GenerarZip();
        gz.procesar();    

        System.out.println("1 Client started");
        try {
            Socket socket = new Socket(HOST, PORT);
            System.out.println("2 Connected to server");

            //Fllujos para enviar y recibir archivos
            File file = new File("C:/cliente/pais_jacinto_grupo_X.zip");
            FileInputStream fis = new FileInputStream(file);
            DataOutputStream salida = new DataOutputStream(socket.getOutputStream());
            
            //enviar archivo
            int byteLeidos = 0;
            while ((byteLeidos = fis.read()) != -1) {
                salida.write(byteLeidos);
            }
            System.out.println("3 File sent");
            
            fis.close();
            salida.close();
          
            System.out.println("4 Client finished");
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new ClienteZipPais();
    }
}
