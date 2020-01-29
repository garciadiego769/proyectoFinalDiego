package com.diego.Gestion.Tareas;

import com.diego.BD;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;

public class ActualizarTarea {
    //indicar el nº de empleado a actualizar e introducir el nuevo nombre

    public static void main(String[] args) throws SQLException, IOException {
        // write your code here
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BD baseDeDatos=new BD();

        System.out.println("Introduce el nº de tarea (OBLIGATORIO):");
        String codigo=br.readLine();

        System.out.println("Introduce el nuevo nombre: ");
        String nombre=br.readLine();

        System.out.println("Introduce código de la máquina asignada a esta tarea. Si no la hay déjalo vacío: ");
        String maquina=br.readLine();

        baseDeDatos.actualizarTarea(codigo,nombre,maquina);
    }
}
