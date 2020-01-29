package com.diego.Gestion.Empleados;

import com.diego.BD;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;

public class ActualizarEmpleado {

    //indicar el nº de empleado a actualizar e introducir el nuevo nombre

    public static void main(String[] args) throws SQLException, IOException {
        // write your code here
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BD baseDeDatos=new BD();

        System.out.println("Introduce el nº de trabajador (OBLIGATORIO):");
        String codTrabajador=br.readLine();

        System.out.println("Introduce el nuevo nombre: ");
        String nombre=br.readLine();

        baseDeDatos.actualizarTrabajador(codTrabajador,nombre);
    }
}
