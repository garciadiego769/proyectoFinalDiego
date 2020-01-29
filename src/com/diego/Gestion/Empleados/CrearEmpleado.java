package com.diego.Gestion.Empleados;

import com.diego.BD;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;

public class CrearEmpleado {

    public static void main(String[] args) throws SQLException, IOException {
        // write your code here
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BD baseDeDatos=new BD();

        System.out.println("Introduce el DNI del trabajador (OBLIGATORIO):");
        String codTrabajador=br.readLine();

        System.out.println("Introduce el nuevo nombre: ");
        String nombre=br.readLine();

        System.out.println("Introduce el primer apellido: ");
        String apellido=br.readLine();

        System.out.println("Introduce el segundo apellido: ");
        String apellido2=br.readLine();

        baseDeDatos.insertarTrabajador(codTrabajador,nombre,apellido,apellido2);
    }
}
