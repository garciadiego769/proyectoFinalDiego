package com.diego.Gestion.Tareas;

import com.diego.BD;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;

public class EliminarTarea {
    public static void main(String[] args) throws SQLException, IOException {
        // write your code here
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BD baseDeDatos = new BD();

        System.out.println("Introduce el código de tarea (OBLIGATORIO):");
        String cod = br.readLine();

        System.out.println("¿Seguro que deseas eliminar la tarea? (s/n):");
        String respuesta = br.readLine();

        if (respuesta.equalsIgnoreCase("s")) {
            baseDeDatos.eliminarTarea(cod);
        } else {
            System.exit(0);
        }
    }
}
