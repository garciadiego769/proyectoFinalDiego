package com.diego;

import com.diego.UI.PantallaUsuario;

import java.sql.SQLException;

public class Inicio {
    public static void main(String[] args) throws SQLException {
        // write your code here

        BD baseDeDatos=new BD();
        baseDeDatos.conectar();

        //baseDeDatos.verTrabajadores();

        PantallaUsuario p=new PantallaUsuario();

    }
}
