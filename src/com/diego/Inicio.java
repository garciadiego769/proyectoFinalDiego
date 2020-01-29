package com.diego;

import com.diego.UI.PantallaUsuario;

import java.sql.SQLException;
import java.util.ArrayList;

public class Inicio {
    public static void main(String[] args) throws SQLException {
        // write your code here

        BD baseDeDatos = new BD();
        baseDeDatos.conectar();

    }
}
