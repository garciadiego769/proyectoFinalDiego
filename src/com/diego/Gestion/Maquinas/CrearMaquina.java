package com.diego.Gestion.Maquinas;

import com.diego.BD;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;

public class CrearMaquina{
    public static void main(String[] args) throws SQLException, IOException {
        // write your code here
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BD baseDeDatos=new BD();

        System.out.println("Introduce el código de maquina (OBLIGATORIO):");
        String cod=br.readLine();

        System.out.println("Introduce nombre: ");
        String nombre=br.readLine();

        baseDeDatos.insertarMaquina(cod,nombre);
    }
}
