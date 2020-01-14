package com.diego;

import java.sql.*;
import java.util.ArrayList;

public class BD {
    Connection conn = null;
    PreparedStatement preparedStmt;

    //Método conectar
    public void conectar() throws SQLException {
        String driver = "com.mysql.cj.jdbc.Driver";
        String bd = "proyectoFinal";
        String hostname = "localhost";
        String port = "3306";

        //cadena de conexión
        String url = "jdbc:mysql://" + hostname + ":" + port + "/" + bd + "?allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=UTC";

        String usuario = "root";

        String pass = "12345Abcde";

        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(url, usuario, pass);
            if (conn != null) {
                //mensaje de conexión
                System.out.println("Conexión establecida");
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            System.out.println("Error al conectar");
        }
    }

    //DESCONECTAR
    public void desconectar() throws SQLException {
        conn.close();
        System.out.println("Conexión cerrada");

    }


    //TRABAJADORES

    public ArrayList<String> verTrabajadores() throws SQLException {
        conectar();

        String query = "SELECT * FROM trabajador";
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery(query);

        ArrayList<String> nombres = new ArrayList<String>();

        while (rs.next()) {
            // String dni = rs.getString("dni");
            String nombre = rs.getString("nombre");

            //añadimos lel nombre que hemos recuperado al Array y lo separamos con un |
            nombres.add(nombre);

            // String apellido = rs.getString("apellido");
            //String apellido2 = rs.getString("apellido2");
            //String foto = rs.getString("foto");

            //  System.out.println("DNI: " + dni + " Nombre: " + nombre + " Apellidos: " + apellido +" "+apellido2);
        }
        desconectar();
        return nombres;
    }


}
