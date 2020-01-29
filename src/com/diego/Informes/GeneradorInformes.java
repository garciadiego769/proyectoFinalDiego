package com.diego.Informes;

import java.io.*;
import java.sql.*;
import java.util.ArrayList;

import com.diego.BD;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.usermodel.HSSFRow;

public class GeneradorInformes {

    BD baseDeDatos = new BD();
    Connection conn = null;
    PreparedStatement preparedStmt;

    public GeneradorInformes(String fechaDesde, String fechaHasta) {
        //INFORME RESUMEN DIARIO
        try {
            //casa
            String filename = "/Users/diego/Documents/Clase/Proyecto Final" +
                    "/proyectoFinalDiego/ResumenDiario.xls";
            //trabajo
            //String filename = "C:\\Users\\dgarcia\\Desktop\\Diego\\Clase\\ProyectoFinal\\proyectoFinalDiego.xls";
            String ficheroMensual = "/Users/diego/Documents/Clase/Proyecto Final" +
                    "/proyectoFinalDiego/ResumenMensual.xls";

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

            //valores mantenimientos
            String descripcionMantenimiento = "";
            String tiempoMantenimiento = "";

            //por cada uno, una hoja
            for (int i = 0; i < numTrabajadores; i++) {
                //EN CADA HOJA
                //nombre de la hoja con el nombre del trabajador
                HSSFSheet hoja = workbook.createSheet(baseDeDatos.verTrabajadores().get(i));

                //conseguir nº de trabajador
                codTrabajador = baseDeDatos.verCodTrabajadorPorNombre(baseDeDatos.verTrabajadores().get(i));

                String[][] resultadoConsulta = baseDeDatos.verTrabajosPorTrabajador(codTrabajador, fechaDesde, fechaHasta);
                //int resultadoConsultaTOTAL=baseDeDatos.verTrabajosPorTodosLosTrabajadores();

                int numero = resultadoConsulta.length;
                // numero=numero-1;
                System.out.println(numero);

                //nombre en celda A1
                HSSFRow cabeceraColumna = hoja.createRow((short) 0);

                //por cada resultado
                int numFila = 1;

                String dia;
                String diaMemoria = "";

                //Conseguir mantenimientos
                String[][] resultadoMantenimientos = baseDeDatos.verMantenimientosPorTrabajador(codTrabajador, fechaDesde, fechaHasta);


                for (int k = 0; k < numero; k++) {
                    nombre = resultadoConsulta[k][0];
                    apellido = resultadoConsulta[k][1];
                    fecha = resultadoConsulta[k][2];

                    //conseguir el día separándolo de la fecha
                    dia = fecha.substring(8, 10);

                    tiempo = resultadoConsulta[k][3];
                    descripcion = resultadoConsulta[k][4];
                    maquina = resultadoConsulta[k][5];

                    //cabeceraColumna = hoja.createRow((short) 0);

                    cabeceraColumna.createCell(0).setCellValue(nombre + " " + apellido);

                    //dia en la columna que toque

                    //Si el día NO es el mismo que el que hemos escrito, creamos una columna nueva
                    //if (!dia.equalsIgnoreCase(diaMemoria)) {
                    cabeceraColumna.createCell(1).setCellValue(dia);
                    // }

                    //cabeceraColumna.createCell(3).setCellValue("3");

                    //  FILAS
                    HSSFRow fila = hoja.createRow((short) numFila);
                    //  row.createCell(0).setCellValue(baseDeDatos.ver);
                    fila.createCell(0).setCellValue(descripcion);
                    fila.createCell(1).setCellValue(tiempo);

                    numFila++;

                    fila = hoja.createRow((short) numFila);
                    fila.createCell(0).setCellValue("Maquina: " + maquina);
                    numFila++;
                    diaMemoria = dia;
                }
                //cuando acaben los trabajos, vamos con los mantenimientos
                for (int j = 0; j < resultadoMantenimientos.length; j++) {
                    descripcionMantenimiento = resultadoMantenimientos[j][0];
                    tiempoMantenimiento = resultadoMantenimientos[j][1];

                    //añadir al final del excel
                    HSSFRow filaMantenimiento = hoja.createRow((short) numFila);

                    filaMantenimiento = hoja.createRow((short) numFila);

                    filaMantenimiento.createCell(0).setCellValue("--- Mantenimientos ---");
                    //descripcion
                    filaMantenimiento.createCell(0).setCellValue("Mantenimiento: " + descripcionMantenimiento);
                    //tiempo, en la columna 1
                    filaMantenimiento.createCell(1).setCellValue(tiempoMantenimiento);
                }

            }
            FileOutputStream fileOut = new FileOutputStream(filename);
            workbook.write(fileOut);
            fileOut.close();
            workbook.close();
            System.out.println("Archivo excel RESUMEN DIARIO generado!");


            //RESUMEN MENSUAL

            //generar fichero
            HSSFWorkbook libro = new HSSFWorkbook();

            String descripcionTrabajo = "";
            String tiempoTrabajo = "";
            String codMaquina = "";
            String descripcionMantenimientos = "";
            String tiempoMantenimientos = "";
            String maquinaMantenimientos = "";
            String fechaTrabajo = "";

            String[][] resultadoTrabajosMantenimientos = baseDeDatos.verTrabajosMantenimientosMensuales();

            //HOJA
            //nombre de la hoja con el nombre del trabajador
            HSSFSheet hoja1 = workbook.createSheet("RESUMEN MENSUAL");

            //nombre en celda A1
            HSSFRow cabeceraColumna = hoja1.createRow((short) 0);

            int numFila = 1;

            //recorrer Array de resultados de la consulta e ir asignando valores
            for (int k = 0; k < resultadoTrabajosMantenimientos.length; k++) {
                descripcionTrabajo = resultadoTrabajosMantenimientos[k][0];
                tiempoTrabajo = resultadoTrabajosMantenimientos[k][1];
                descripcionMantenimientos = resultadoTrabajosMantenimientos[k][2];
                tiempoMantenimientos = resultadoTrabajosMantenimientos[k][3];
                fechaTrabajo = resultadoTrabajosMantenimientos[k][4];
                maquinaMantenimientos = resultadoTrabajosMantenimientos[k][5];

                String dia = fechaTrabajo.substring(8, 10);

                //cabeceraColumna = hoja.createRow((short) 0);

                cabeceraColumna.createCell(0).setCellValue("Resumen");

                //dia en la columna que toque

                //Si el día NO es el mismo que el que hemos escrito, creamos una columna nueva
                //if (!dia.equalsIgnoreCase(diaMemoria)) {
                cabeceraColumna.createCell(1).setCellValue("Tiempo");
                // }

                //cabeceraColumna.createCell(3).setCellValue("3");

                //  FILAS
                HSSFRow fila = hoja1.createRow((short) numFila);
                //  row.createCell(0).setCellValue(baseDeDatos.ver);
                fila.createCell(0).setCellValue(descripcionTrabajo);
                fila.createCell(1).setCellValue(tiempoTrabajo);

                numFila++;

                fila = hoja1.createRow((short) numFila);
                fila.createCell(0).setCellValue("Mantenimiento: " + descripcionMantenimientos);

                fila.createCell(1).setCellValue(tiempoMantenimientos);

                numFila++;
                fila = hoja1.createRow((short) numFila);

                fila.createCell(0).setCellValue("Maquina: " + maquinaMantenimientos);

                numFila++;
            }
            //Generar fichero mensual
            FileOutputStream fos = new FileOutputStream(ficheroMensual);
            workbook.write(fos);
            fos.close();
            libro.close();

            System.out.println("Archivo excel RESUMEN MENSUAL generado!");

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
