package com.diego;

import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class BD {
    Connection conn = null;
    PreparedStatement preparedStmt;

    //Array donde se irán guardando todas las opciones seleccionadas
    public static ArrayList<String> guardaDatos = new ArrayList<String>();

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


    //INSERTAR
    public void insertarActividad(String trabajador, String actividad, String maquina, String tiempo, String mantenimiento, String tiempoMantenimiento) throws SQLException {
        conectar();

      /*  FECHA
      DateFormat formatter = new SimpleDateFormat("dd-MM-YYY");
        Date myDate = formatter.parse(date);
        java.sql.Date sqlDate = new java.sql.Date(myDate.getTime());
*/
        //Vamos haciendo las consultas para obtener los datos necesarios para insertar en la tabla
        //Conseguimos el DNI
        String queryTrabajador = "SELECT * FROM trabajador WHERE nombre='" + trabajador + "'";
        Statement stTrabajador = conn.createStatement();
        ResultSet rsTrabajador = stTrabajador.executeQuery(queryTrabajador);

        String dniTrabajador = new String();
        while (rsTrabajador.next()) {
            String nombre = rsTrabajador.getString("dni");
            dniTrabajador = nombre;
        }

        //TAREA
        String queryTarea = "SELECT * FROM tarea WHERE descripcion='" + actividad + "'";
        Statement stTarea = conn.createStatement();
        ResultSet rsTarea = stTarea.executeQuery(queryTarea);

        String codTarea = new String();
        while (rsTarea.next()) {
            String codigo = rsTarea.getString("codTarea");
            codTarea = codigo;
        }

        //MANTENIMIENTO
        String queryMantenimiento = "SELECT * FROM mantenimiento WHERE descripcion='" + mantenimiento + "'";
        Statement stMantenimiento = conn.createStatement();
        ResultSet rsMantenimiento = stMantenimiento.executeQuery(queryMantenimiento);

        String codMantenimiento = new String();
        while (rsMantenimiento.next()) {
            String codigo = rsMantenimiento.getString("codMantenimiento");
            codMantenimiento = codigo;
        }

        //MAQUINA
        String queryMaquina = "SELECT * FROM mquina WHERE descricpcion='" + maquina + "'";
        Statement stMaquina = conn.createStatement();
        ResultSet rsMaquina = stMaquina.executeQuery(queryMaquina);

        String codMaquina = new String();
        while (rsMaquina.next()) {
            String codigo = rsMaquina.getString("codMaquina");
            codMaquina = codigo;
        }

        //SI NO hay maquina hacemos una inserción
        if (maquina.isEmpty()) {
            // query con los datos a insertar

            String query = "INSERT INTO trabajo VALUES('" + dniTrabajador + "','" + codTarea + "','" + "20190101" + "','" + tiempo + "');";

            preparedStmt = conn.prepareStatement(query);

            // execute the preparedstatement
            preparedStmt.execute();
        } else {
            //SI HAY datos hacemos otra inserción

        }

        desconectar();


    }


}
