package com.diego.Gestion.Tareas;

import com.diego.BD;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;

public class CrearTarea{
    public static void main(String[] args) throws SQLException, IOException {
        // write your code here
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BD baseDeDatos=new BD();

        System.out.println("Introduce el código de la tarea (OBLIGATORIO):");
        String cod=br.readLine();

        System.out.println("Introduce nombre: ");
        String nombre=br.readLine();

        System.out.println("Introduce código de la máquina asignada a esta tarea. Si no la hay déjalo vacío: ");
        String maquina=br.readLine();

        baseDeDatos.insertarTarea(cod,nombre,maquina);
    }
}
