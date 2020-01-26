package com.diego.Informes;

import java.io.*;

import com.diego.BD;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.usermodel.HSSFRow;

public class GeneradorInformes {

    BD baseDeDatos=new BD();


    public void generaInformes(String fechaDesde, String fechaHasta) {
        try {

            String filename = "/Users/diego/Documents/Clase/Proyecto Final/proyectoFinalDiego/ResumenDiario.xls";
            HSSFWorkbook workbook = new HSSFWorkbook();

            //ver nº de trabajadores
            int numTrabajadores=baseDeDatos.verTrabajadores().size();

            //por cada uno, una hoja
            for (int i = 0; i < numTrabajadores; i++) {
                //nombre de la hoja con el nombre del trabajador
                HSSFSheet hoja = workbook.createSheet(baseDeDatos.verTrabajadores().get(i));

                HSSFRow rowhead = hoja.createRow((short) 0);

                rowhead.createCell(0).setCellValue(baseDeDatos.verTrabajadores().get(i));
                rowhead.createCell(1).setCellValue("1");
                rowhead.createCell(2).setCellValue("2");
                rowhead.createCell(3).setCellValue("3");


                HSSFRow row = hoja.createRow((short) 1);
                row.createCell(0).setCellValue(baseDeDatos.ver);
                row.createCell(1).setCellValue("Sankumarsingh");
                row.createCell(2).setCellValue("India");
                row.createCell(3).setCellValue("sankumarsingh@gmail.com");
            }



            FileOutputStream fileOut = new FileOutputStream(filename);
            workbook.write(fileOut);
            fileOut.close();
            workbook.close();
            System.out.println("Your excel file has been generated!");

        } catch (Exception ex) {
            System.out.println(ex);
        }
    }
}
