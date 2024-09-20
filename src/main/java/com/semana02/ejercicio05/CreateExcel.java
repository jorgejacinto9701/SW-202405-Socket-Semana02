package com.semana02.ejercicio05;

import java.io.FileOutputStream;
import java.util.ArrayList;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class CreateExcel {

    public static void main(String[] args) {
        
        Producto producto1 = new Producto(1, "Laptop", 2500.0, 10);
        Producto producto2 = new Producto(2, "Impresora", 500.0, 5);
        Producto producto3 = new Producto(3, "Mouse", 50.0, 20);
        Producto producto4 = new Producto(4, "Teclado", 100.0, 15);

        ArrayList<Producto> productos = new ArrayList<Producto>();
        productos.add(producto1);
        productos.add(producto2);
        productos.add(producto3);
        productos.add(producto4);

        // Crear un archivo excel
        String[] HEADERs = {"ID", "Nombre", "Precio", "Stock"};
        String SHEET = "Productos";
        int[] HEADERs_WITH = {1000, 10000, 10000, 10000};

        try {
                FileOutputStream fileOut = new FileOutputStream("C:/cliente/productos.xlsx");
                Workbook workbook = new XSSFWorkbook();
                Sheet sheet = workbook.createSheet(SHEET);

                // Crear el encabezado. Tamaño de las columnas
                for (int i = 0; i < HEADERs.length; i++) {
                    sheet.setColumnWidth(i, HEADERs_WITH[i]);
                }

                // Crear el encabezado. Nombre de las columnas
                Row headerRow = sheet.createRow(0);
                for (int i = 0; i < HEADERs.length; i++) {
                    headerRow.createCell(i).setCellValue(HEADERs[i]);
                }

                // Crear las filas
                int rowNum = 1;
                for (Producto producto : productos) {
                    Row row = sheet.createRow(rowNum++);
                    row.createCell(0).setCellValue(producto.getIdProducto());
                    row.createCell(1).setCellValue(producto.getNombre());
                    row.createCell(2).setCellValue(producto.getPrecio());
                    row.createCell(3).setCellValue(producto.getStock());
                }

                workbook.write(fileOut);
                fileOut.close();
                workbook.close();
        } catch (Exception e) {
            e.printStackTrace();
        }            
    }
}
