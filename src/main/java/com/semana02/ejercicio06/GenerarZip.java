package com.semana02.ejercicio06;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;


import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class GenerarZip {


    public static void main(String[] args) {
        GenerarZip gz = new GenerarZip();
        gz.procesar();
    }

    public void procesar() {
        
        List<Pais> paises = new ArrayList<Pais>();
        
        System.out.println("1 >> Traer data de la BD");
        Connection con = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        try {
            con = MySqlDBConexion.getConexion();
            String sql = "SELECT * FROM pais";
            pstm = con.prepareStatement(sql);
            rs = pstm.executeQuery();
            while (rs.next()) {
                Pais pais = new Pais();
                pais.setIdPais(rs.getInt(1));
                pais.setIso(rs.getString(2));
                pais.setNombre(rs.getString(3));
                paises.add(pais);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                rs.close();
                pstm.close();
                con.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        //System.out.println(paises);
        System.out.println("2 >> Generar el JSON");

        FileWriter fileWriter  = null;
        try {
            File file = new File("C:/cliente/pais_jacinto_grupo_X.json");
            fileWriter = new FileWriter(file);
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            gson.toJson(paises, fileWriter);
        } catch (Exception e) {
           e.printStackTrace();
        } finally {
            try {
                fileWriter.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        System.out.println("3 >> Generar el XML");

        FileWriter fileWriterXML  = null;
        try {
            File file = new File("C:/cliente/pais_jacinto_grupo_X.xml");
            fileWriterXML = new FileWriter(file);
            XmlMapper xmlMapper = new XmlMapper();
            xmlMapper.enable(SerializationFeature.INDENT_OUTPUT);
            xmlMapper.writeValue(fileWriterXML, paises);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                fileWriterXML.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        System.out.println("4 >> Generar el ZIP");
         try {

                String[] archivos = { "C:/cliente/pais_jacinto_grupo_X.json", "C:/cliente/pais_jacinto_grupo_X.xml"};
                ZipOutputStream zipOutputStream = new ZipOutputStream(new FileOutputStream("C:/cliente/pais_jacinto_grupo_X.zip"));
                for (String ruta : archivos) {
                    System.out.println("Archivo: " + ruta);
                    File archivoZip = new File(ruta);
                    FileInputStream fis = new FileInputStream(archivoZip);    
                    ZipEntry zipEntry = new ZipEntry(archivoZip.getName());
                    zipOutputStream.putNextEntry(zipEntry);
                    int byteLeidos = 0;
                    while ((byteLeidos = fis.read()) != -1) {
                        zipOutputStream.write(byteLeidos);
                    }
                    fis.close();
                    zipOutputStream.closeEntry();
                }
                zipOutputStream.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
          
    }
}
