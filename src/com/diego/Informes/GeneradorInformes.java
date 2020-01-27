package com.diego.Informes;

import java.io.*;
import java.sql.*;
import java.util.ArrayList;

import com.diego.BD;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.usermodel.HSSFRow;

public class GeneradorInformes {
    public static void main(String[] args) throws SQLException {

    }

    BD baseDeDatos = new BD();
    Connection conn = null;
    PreparedStatement preparedStmt;

    public GeneradorInformes(String fechaDesde, String fechaHasta) {
        try {
            //casa
            //  String filename = "/Users/diego/Documents/Clase/Proyecto Final/proyectoFinalDiego/ResumenDiario.xls";
            //trabajo
            String filename = "C:\\Users\\dgarcia\\Desktop\\Diego\\Clase\\ProyectoFinal\\proyectoFinalDiego.xls";

            HSSFWorkbook workbook = new HSSFWorkbook();

            //ver nº de trabajadores
            int numTrabajadores = baseDeDatos.verTrabajadores().size();
            String codTrabajador = "";
            String nombre = "";
            String apellido = "";
            String fecha = "";
            String tiempo = "";
            String descripcion = "";
            String maquina = "";

            //por cada uno, una hoja
            for (int i = 0; i < numTrabajadores; i++) {
                //EN CADA HOJA
                //nombre de la hoja con el nombre del trabajador
                HSSFSheet hoja = workbook.createSheet(baseDeDatos.verTrabajadores().get(i));

                //conseguir nº de trabajador
                codTrabajador = baseDeDatos.verCodTrabajadorPorNombre(baseDeDatos.verTrabajadores().get(i));
                // ArrayList resultadoConsulta = baseDeDatos.verTrabajosPorTrabajador(codTrabajador);
                // System.out.println(resultadoConsulta);
                String[][] resultadoConsulta = baseDeDatos.verTrabajosPorTrabajador(codTrabajador);

                //por cada resultado
                for (int k = 0; k < resultadoConsulta[0].length; k++) {
                    nombre = resultadoConsulta[i][0];
                    apellido = resultadoConsulta[i][1];
                    fecha = resultadoConsulta[i][2];
                    tiempo = resultadoConsulta[i][3];
                    descripcion = resultadoConsulta[i][4];
                    maquina = resultadoConsulta[i][5];

                    HSSFRow rowhead = hoja.createRow((short) 0);

                    rowhead.createCell(0).setCellValue(nombre);
                    rowhead.createCell(1).setCellValue("1");
                    rowhead.createCell(2).setCellValue("2");
                    rowhead.createCell(3).setCellValue("3");


                    //  FILAS
                    HSSFRow row = hoja.createRow((short) 1);
                    //  row.createCell(0).setCellValue(baseDeDatos.ver);
                    row.createCell(0).setCellValue(descripcion);
                    row.createCell(1).setCellValue(tiempo);

                    row = hoja.createRow((short) 2);
                    row.createCell(0).setCellValue("Maquina " + maquina);
                    // row.createCell(3).setCellValue("sankumarsingh@gmail.com");

                }


                FileOutputStream fileOut = new FileOutputStream(filename);
                workbook.write(fileOut);
                fileOut.close();
                workbook.close();
                System.out.println("Your excel file has been generated!");

            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void generaInformes(String fechaDesde, String fechaHasta) throws SQLException {

        try {
            //casa
            String filename = "/Users/diego/Documents/Clase/Proyecto Final/proyectoFinalDiego/ResumenDiario.xls";
            //trabajo
            // String filename = "/Users/diego/Documents/Clase/Proyecto Final/proyectoFinalDiego/ResumenDiario.xls/ResumenDiario.xls";

            HSSFWorkbook workbook = new HSSFWorkbook();

            //ver nº de trabajadores
            int numTrabajadores = baseDeDatos.verTrabajadores().size();
            String codTrabajador = "";

            //por cada uno, una hoja
            for (int i = 0; i < numTrabajadores; i++) {
                //nombre de la hoja con el nombre del trabajador
                HSSFSheet hoja = workbook.createSheet(baseDeDatos.verTrabajadores().get(i));

                //conseguir nº de trabajador
                codTrabajador = baseDeDatos.verCodTrabajadorPorNombre(baseDeDatos.verTrabajadores().get(i));
                //ArrayList resultadoConsulta = baseDeDatos.verTrabajosPorTrabajador(codTrabajador);
                String[][] resultadoConsulta = baseDeDatos.verTrabajosPorTrabajador(codTrabajador);

                String nombre = "";
                String apellido = "";
                String fecha = "";
                String tiempo = "";
                String descripcion = "";
                String maquina = "";

                //por cada resultado
                for (int k = 0; k < resultadoConsulta[0].length; k++) {
                    nombre = resultadoConsulta[i][0];
                    apellido = resultadoConsulta[i][1];
                    fecha = resultadoConsulta[i][2];
                    tiempo = resultadoConsulta[i][3];
                    descripcion = resultadoConsulta[i][4];
                    maquina = resultadoConsulta[i][5];

                    HSSFRow rowhead = hoja.createRow((short) 0);

                    rowhead.createCell(0).setCellValue(baseDeDatos.verTrabajadores().get(i));
                    rowhead.createCell(1).setCellValue("1");
                    rowhead.createCell(2).setCellValue("2");
                    rowhead.createCell(3).setCellValue("3");


                    HSSFRow row = hoja.createRow((short) 1);
                    //  row.createCell(0).setCellValue(baseDeDatos.ver);
                    row.createCell(1).setCellValue("Sankumarsingh");
                    row.createCell(2).setCellValue("India");
                    row.createCell(3).setCellValue("sankumarsingh@gmail.com");

                }
                //SEPARAMOS LOS DATOS DEL ARRAY
                // System.out.println(resultadoConsulta);

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
