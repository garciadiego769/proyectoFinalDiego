package com.diego;

import java.sql.*;
import java.util.ArrayList;

public class BD {
    Connection conn = null;
    PreparedStatement preparedStmt;

    //Array donde se irán guardando todas las opciones seleccionadas
    public static ArrayList<String> guardaDatos=new ArrayList<String>();

    //Método conectar
    public void conectar() throws SQLException {
        String driver = "com.mysql.cj.jdbc.Driver";
        String bd = "proyectoFinal";
        String hostname = "localhost";

        //casa
         String port = "3306";
         //trabajo
        //String port = "3308";


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
            String nombre = rs.getString("nombre");

            nombres.add(nombre);
        }
        desconectar();
        return nombres;
    }

    //TAREAS

    public ArrayList<String> verTareas() throws SQLException {
        conectar();

        String query = "SELECT * FROM tarea";
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery(query);

        ArrayList<String> nombres = new ArrayList<String>();

        while (rs.next()) {
            String nombre = rs.getString("descripcion");
            nombres.add(nombre);
        }
        desconectar();
        return nombres;
    }

    //MAQUINA TAREA
    public int verNumeroMaquinaDisponible(String codMaquina) throws SQLException {
        conectar();

        String query = "SELECT COUNT(maquina_codMaquina) FROM tarea WHERE descripcion='" + codMaquina + "'";
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery(query);

        int cantidad = 0;
        //si hay datos, lo devolvemos
        while (rs.next()) {
            // String dni = rs.getString("dni");
            cantidad = rs.getInt("COUNT(maquina_codMaquina)");
        }
        desconectar();
        return cantidad;
    }


    public ArrayList<String> verMaquina(String nombreTarea) throws SQLException {
        String nombre = "";
        conectar();

        String query1 = "SELECT * FROM tarea WHERE descripcion='" + nombreTarea + "'";
        Statement st1 = conn.createStatement();
        ResultSet rs1 = st1.executeQuery(query1);

        while (rs1.next()) {
            //guardamos el nº de maquina para la tarea
            nombre = rs1.getString("maquina_codMaquina");
        }
        desconectar();

        //ahora consultamos la tabla maquina con el nº de maquna que hemos recuperado
        conectar();

        String query = "SELECT * FROM maquina WHERE codMaquina='" + nombre + "'";
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery(query);

        ArrayList<String> nombreMaquina = new ArrayList<String>();

        while (rs.next()) {
            String nombreMaquina1 = rs.getString("descripcion");
            nombreMaquina.add(nombreMaquina1);
        }
        desconectar();
        return nombreMaquina;
    }

    //MANTENIMIENTOS

    public ArrayList<String> verMantenimientos() throws SQLException {
        conectar();

        String query = "SELECT * FROM mantenimiento";
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery(query);

        ArrayList<String> nombres = new ArrayList<String>();

        while (rs.next()) {
            String nombre = rs.getString("descripcion");
            nombres.add(nombre);
        }
        desconectar();
        return nombres;
    }
}
