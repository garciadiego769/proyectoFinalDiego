package com.diego.Gestion.Empleados;

import com.diego.BD;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;

public class EliminarEmpleado {

    public static void main(String[] args) throws SQLException, IOException {
        // write your code here
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BD baseDeDatos=new BD();

        System.out.println("Introduce el nº de trabajador (OBLIGATORIO):");
        String codTrabajador=br.readLine();

        System.out.println("¿Seguro que deseas eliminar al empleado? (s/n):");
        String respuesta=br.readLine();

        if (respuesta.equalsIgnoreCase("s")){
            baseDeDatos.eliminarTrabajador(codTrabajador);
        }else{
            System.exit(0);
        }
    }
}
