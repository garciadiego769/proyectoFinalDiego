package com.diego;

import com.diego.UI.PantallaFinal;

import java.sql.*;
import java.util.ArrayList;

public class BD {
    Connection conn = null;
    PreparedStatement preparedStmt;
    PreparedStatement preparedStmtTrabajoMantenimiento;


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
    public void insertarActividad(String trabajador, String actividad, String tiempo, String mantenimiento, String tiempoMantenimiento) throws SQLException {
        conectar();

        String mensaje;

        java.util.Date fecha = new java.util.Date();
        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd");
        String fechaHoy = sdf.format(fecha);

        try {
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

            //<-------- Recuperar último código de mantenimiento y de trabajo --------->
            // CODTRABAJO
            //recuperamos el último nº de trabajo para añadir el siguiente
            String queryCodTrabajo = "SELECT * FROM trabajo ORDER BY codTrabajo DESC LIMIT 1";
            Statement stCodTrabajo = conn.createStatement();
            ResultSet rsCodTrabajo = stCodTrabajo.executeQuery(queryCodTrabajo);

            int codTrabajo = 0;
            while (rsCodTrabajo.next()) {
                int codigo = rsCodTrabajo.getInt("codTrabajo");
                codTrabajo = codigo;
            }

            //le sumamos uno al último codTrabajo
            codTrabajo += 1;

            //CODTRABAJOMANTENIMIENTO
            //recuperamos el último nº de mantenimiento para añadir el siguiente
            String queryCodTrabajoMantenimiento = "SELECT * FROM trabajoMantenimiento ORDER BY codTrabajoMantenimiento DESC LIMIT 1";
            Statement stCodTrabajoMantenimiento = conn.createStatement();
            ResultSet rsCodTrabajoMantenimiento = stCodTrabajoMantenimiento.executeQuery(queryCodTrabajoMantenimiento);

            int codTrabajoMantenimiento = 0;
            while (rsCodTrabajoMantenimiento.next()) {
                int codigo = rsCodTrabajoMantenimiento.getInt("codTrabajoMantenimiento");
                codTrabajoMantenimiento = codigo;
            }

            //le sumamos uno al último codTrabajoMantenimiento
            codTrabajoMantenimiento += 1;

            //INSERCIONES

            // Query para TRABAJO
            String query = "INSERT INTO trabajo VALUES('" + dniTrabajador + "','" + codTarea + "','" + fechaHoy + "','" + tiempo + "','" + codTrabajo + "');";
            //Query para TrabajoMantenimiento
            String queryTrabajoMantenimiento = "INSERT INTO trabajoMantenimiento VALUES('" + codMantenimiento + "','" + dniTrabajador + "','" + fechaHoy + "','" + tiempo + "','" + codTrabajoMantenimiento + "');";

            //se insertan ambos
            preparedStmt = conn.prepareStatement(query);
            preparedStmt.execute();

            preparedStmtTrabajoMantenimiento = conn.prepareStatement(queryTrabajoMantenimiento);
            preparedStmtTrabajoMantenimiento.execute();

            mensaje = "Datos guardados correctamente";

            desconectar();
        }
        //controlamos las excepciones y mostramos un mensaje u otro en la pantalla final
        catch (SQLException e) {
            mensaje = "Ha ocurrido un error a la hora de guardar datos";
        }
        //Abrimos la pantalla final
        PantallaFinal pantallaFinal = null;
        pantallaFinal = new PantallaFinal(mensaje);
        pantallaFinal.setVisible(true);
        pantallaFinal.setLocationRelativeTo(null);
    }

    //TRABAJOS

    public String[][] verTrabajosPorTrabajador(String codTrabajador, String fechaInicio,String fechaFin) throws SQLException {
        conectar();

        String query = "SELECT DISTINCT * ,IFNULL (tarea.maquina_codMaquina,-1) as maquina_codMaquinaNulo FROM trabajador,trabajo,tarea " +
                "WHERE trabajo.trabajador_dni='" + codTrabajador + "' AND tarea.codTarea=trabajo.tarea_codTarea " +
                "AND trabajador.dni=trabajo.trabajador_dni AND fecha BETWEEN '"+fechaInicio+"' AND '"+fechaFin+"';";
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery(query);

        //conseguir tamaño resultSet, nº de resultados
        int tamaño = 0;
        while (rs.next()) {
            tamaño++;    // moves cursor to the last row
        }
        //mover el cursor de vuelta a la primera posición
        rs.first();

        tamaño = tamaño - 1; //para que el array empiece en la 0
        String nombre = "";
        String apellido = "";

        //array bidimensional con tantas filas como resultados devuelve la consulta y 6 columnas
        String[][] prueba = new String[tamaño + 1][6];

        ArrayList<String> fechas = new ArrayList<String>();
        ArrayList<String> tiempos = new ArrayList<String>();
        ArrayList<String> descripciones = new ArrayList<String>();
        ArrayList<String> maquinas = new ArrayList<String>();
        ArrayList<String> todo = new ArrayList<String>();

        int cuenta = -1;
        //guardamos todos los datos de la tabla TRABAJO para ese trabajador
        do {
            //sumamos 1 al contador
            cuenta = cuenta + 1;

            nombre = rs.getString("nombre");
            todo.add(nombre);
            //nombre en la casilla 0,1
            prueba[cuenta][0] = nombre;

            apellido = rs.getString("apellido");
            todo.add(apellido);
            prueba[cuenta][1] = apellido;

            String fecha = rs.getString("fecha");
            fechas.add(fecha);
            todo.add(fecha);
            prueba[cuenta][2] = fecha;

            String tiempo = rs.getString("tiempo");
            tiempos.add(tiempo);
            todo.add(tiempo);
            prueba[cuenta][3] = tiempo;

            String descripcion = rs.getString("descripcion");
            descripciones.add(descripcion);
            todo.add(descripcion);
            prueba[cuenta][4] = descripcion;

            String codMaquinas = rs.getString("maquina_codMaquinaNulo");
            // System.out.println(codMaquinas);
            if (codMaquinas.equalsIgnoreCase("-1")) {
                maquinas.add("ninguna");
                prueba[cuenta][5] = "ninguna";
            } else {
                maquinas.add(codMaquinas);
                prueba[cuenta][5] = codMaquinas;
            }

            todo.add(maquinas + "|"); //separador

        }
        while (rs.next());


        String nombreCompleto = nombre + apellido;


        for (int j = 0; j < prueba.length; j++) {
            for (int k = 0; k < prueba[j].length; k++) {
                System.out.println("Posición :" + j + " " + k);
                System.out.println(prueba[j][k]);
            }
        }
        return prueba;
    }

    //VER TRABAJOS DE TODOS LOS TRABAJADORES
    public int verTrabajosPorTodosLosTrabajadores() throws SQLException {
        conectar();

        String query = "SELECT DISTINCT * ,IFNULL (tarea.maquina_codMaquina,-1) as maquina_codMaquinaNulo FROM trabajador,trabajo,tarea WHERE tarea.codTarea=trabajo.tarea_codTarea AND trabajador.dni=trabajo.trabajador_dni;";
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery(query);

        //conseguir tamaño resultSet, nº de resultados
        int tamaño = 0;
        while (rs.next()) {
            tamaño++;    // moves cursor to the last row
        }

        return tamaño;
    }

    public String verCodTrabajadorPorNombre(String nombre) throws SQLException {
        conectar();
        //Vamos haciendo las consultas para obtener los datos necesarios para insertar en la tabla
        //Conseguimos el DNI
        String queryTrabajador = "SELECT * FROM trabajador WHERE nombre='" + nombre + "'";
        Statement stTrabajador = conn.createStatement();
        ResultSet rsTrabajador = stTrabajador.executeQuery(queryTrabajador);

        String dniTrabajador = new String();
        while (rsTrabajador.next()) {
            String nombreTrabajador = rsTrabajador.getString("dni");
            dniTrabajador = nombreTrabajador;
        }
        return dniTrabajador;
    }
}
