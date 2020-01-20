package com.diego;

import com.diego.UI.PantallaUsuario;

import java.sql.SQLException;
import java.util.ArrayList;

public class Inicio {
    public static void main(String[] args) throws SQLException {
        // write your code here

        BD baseDeDatos=new BD();
        baseDeDatos.conectar();

        baseDeDatos.verTareas();
        //baseDeDatos.verTrabajadores();

        ArrayList nombres=baseDeDatos.verTareas();

        //recuperamos el Array con todos los nombres de las tareas de la BD
        System.out.println(nombres);

        String tarea1 = (String) nombres.get(0);
        System.out.println(tarea1);
    }
}
