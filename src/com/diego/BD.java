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

    public String[][] verTrabajosPorTrabajador(String codTrabajador, String fechaInicio, String fechaFin) throws SQLException {
        conectar();

        String query = "SELECT DISTINCT * ,IFNULL (tarea.maquina_codMaquina,-1) as maquina_codMaquinaNulo FROM trabajador,trabajo,tarea " +
                "WHERE trabajo.trabajador_dni='" + codTrabajador + "' AND tarea.codTarea=trabajo.tarea_codTarea " +
                "AND trabajador.dni=trabajo.trabajador_dni AND fecha BETWEEN '" + fechaInicio + "' AND '" + fechaFin + "';";
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
        String[][] datos = new String[tamaño + 1][6];

        int cuenta = -1;
        //guardamos todos los datos de la tabla TRABAJO para ese trabajador
        do {
            //sumamos 1 al contador
            cuenta = cuenta + 1;

            nombre = rs.getString("nombre");
            //nombre en la casilla 0,1
            datos[cuenta][0] = nombre;

            apellido = rs.getString("apellido");
            datos[cuenta][1] = apellido;

            String fecha = rs.getString("fecha");
            datos[cuenta][2] = fecha;

            String tiempo = rs.getString("tiempo");
            datos[cuenta][3] = tiempo;

            String descripcion = rs.getString("descripcion");
            datos[cuenta][4] = descripcion;

            String codMaquinas = rs.getString("maquina_codMaquinaNulo");
            // System.out.println(codMaquinas);
            if (codMaquinas.equalsIgnoreCase("-1")) {
                datos[cuenta][5] = "ninguna";
            } else {
                datos[cuenta][5] = codMaquinas;
            }

        }
        while (rs.next());


        String nombreCompleto = nombre + apellido;


        for (int j = 0; j < datos.length; j++) {
            for (int k = 0; k < datos[j].length; k++) {
                System.out.println("Posición :" + j + " " + k);
                System.out.println(datos[j][k]);
            }
        }
        return datos;
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

    //MANTENIMIENTOS
    public String[][] verMantenimientosPorTrabajador(String codTrabajador, String fechaInicio, String fechaFin) throws SQLException {
        conectar();

        String query = "SELECT * FROM trabajador,trabajoMantenimiento,mantenimiento WHERE " +
                "trabajador.dni=trabajoMantenimiento.trabajador_dni AND " +
                "mantenimiento.codMantenimiento=trabajoMantenimiento.mantenimiento_codMantenimiento " +
                "AND trabajador.dni='" + codTrabajador + "'AND fecha BETWEEN '" + fechaInicio + "' AND '" + fechaFin + "';";

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
        String descripcion = "";
        String tiempo = "";

        //array bidimensional con tantas filas como resultados devuelve la consulta y 6 columnas
        String[][] datos = new String[tamaño + 1][2];

        int cuenta = -1;

        do {
            //sumamos 1 al contador
            cuenta = cuenta + 1;

            descripcion = rs.getString("descripcion");
            //nombre en la casilla 0,1
            datos[cuenta][0] = descripcion;

            tiempo = rs.getString("tiempo");
            datos[cuenta][1] = tiempo;
        }
        while (rs.next());


        for (int j = 0; j < datos.length; j++) {
            for (int k = 0; k < datos[j].length; k++) {
                System.out.println("Posición :" + j + " " + k);
                System.out.println(datos[j][k]);
            }
        }
        return datos;
    }


    //VER TRABAJOS Y MANTENIMIENTOS MENSUALES
    public String[][] verTrabajosMantenimientosMensuales(/*String fechaInicio, String fechaFin*/) throws SQLException {
        conectar();

        //Usamos los "as" para evitar ambiguedades ya que muchos campos se llaman igual y poder diferenciarlos

        String query = "SELECT *,IFNULL(maquina_codMaquina,-1) as maquinaNulo, tarea.descripcion as descripcionTrabajo, trabajo.tiempo as tiempoTrabajo, mantenimiento.descripcion as descripcionMantenimiento, TrabajoMantenimiento.tiempo as tiempoMantenimiento, trabajo.fecha as fechaTrabajo FROM trabajo,tarea,mantenimiento,trabajoMantenimiento WHERE trabajo.tarea_codTarea=tarea.codTarea AND trabajoMantenimiento.mantenimiento_codMantenimiento=mantenimiento.codMantenimiento;";

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
        String descripcionTrabajo = "";
        String tiempoTrabajo = "";

        //array bidimensional con tantas filas como resultados devuelve la consulta y 5 columnas
        String[][] datos = new String[tamaño + 1][6];

        int cuenta = -1;
        //guardamos todos los datos de la tabla TRABAJO para ese trabajador
        do {
            //sumamos 1 al contador
            cuenta = cuenta + 1;

            descripcionTrabajo = rs.getString("descripcionTrabajo");
            //nombre en la casilla 0,1
            datos[cuenta][0] = descripcionTrabajo;

            tiempoTrabajo = rs.getString("tiempoTrabajo");
            datos[cuenta][1] = tiempoTrabajo;

            String descripcionMantenimientos = rs.getString("descripcionMantenimiento");
            datos[cuenta][2] = descripcionMantenimientos;

            String tiempo = rs.getString("tiempoMantenimiento");
            datos[cuenta][3] = tiempo;

            String fechaTrabajo = rs.getString("fechaTrabajo");
            datos[cuenta][4] = fechaTrabajo;

            String codMaquinas = rs.getString("maquinaNulo");

            if (codMaquinas.equalsIgnoreCase("-1")) {
                datos[cuenta][5] = "ninguna";
            } else {
                datos[cuenta][5] = codMaquinas;
            }
        }
        while (rs.next());

        return datos;
    }


    //GESTIÓN DE EMPLEADOS
    public void verTrabajadoresConsola() throws SQLException {
        conectar();

        String query = "SELECT * FROM trabajador";
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery(query);

        while (rs.next()) {
            String dni = rs.getString("dni");
            String nombre = rs.getString("nombre");
            String apellido = rs.getString("apellido");
            String apellido2 = rs.getString("apellido2");

            System.out.println("DNI: " + dni + " Nombre: " + nombre + " Apellidos: " + apellido + " " + apellido2);
        }
        desconectar();
    }

    public void actualizarTrabajador(String dni, String nombre) throws SQLException {
        conectar();

        String query = "UPDATE trabajador SET nombre='" + nombre + "' WHERE dni='" + dni + "';";
        PreparedStatement preparedStmt = conn.prepareStatement(query);
        preparedStmt.executeUpdate();

        verTrabajadoresConsola();
        desconectar();

    }

    public void insertarTrabajador(String dni, String Nombre, String apellido, String apellido2) throws SQLException {
        conectar();
        // query con los datos a insertar
        String query = "INSERT INTO trabajador (dni, nombre, apellido, apellido2) VALUES('" + dni + "','" + Nombre + "','" + apellido + "','" + apellido2 + "')";

        preparedStmt = conn.prepareStatement(query);

        // execute the preparedstatement
        preparedStmt.execute();

        verTrabajadoresConsola();
        desconectar();
    }


    public void eliminarTrabajador(String dni) throws SQLException {
        conectar();

        String query = "DELETE FROM trabajador WHERE dni='" + dni + "';";
        PreparedStatement preparedStatmt = conn.prepareStatement(query);

        preparedStatmt.executeUpdate();
        System.out.println("Empleado eliminado");
        verTrabajadoresConsola();
        desconectar();
    }

    //GESTIÓN MANTENIMIENTOS
    public void verMantenimientosConsola() throws SQLException {
        conectar();

        String query = "SELECT * FROM mantenimiento";
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery(query);

        while (rs.next()) {
            String cod = rs.getString("codMantenimiento");
            String nombre = rs.getString("descripcion");

            System.out.println("codigo: " + cod + " Nombre: " + nombre);
        }
        desconectar();
    }

    public void insertarMantenimiento(String codMantenimiento, String Nombre) throws SQLException {
        conectar();
        // query con los datos a insertar
        String query = "INSERT INTO mantenimiento VALUES('" + codMantenimiento + "','" + Nombre + "')";

        preparedStmt = conn.prepareStatement(query);

        // execute the preparedstatement
        preparedStmt.execute();

        verMantenimientosConsola();
        desconectar();
    }


    public void actualizarMantenimiento(String cod, String nombre) throws SQLException {
        conectar();

        String query = "UPDATE mantenimiento SET descripcion='" + nombre + "' WHERE codMantenimiento='" + cod + "';";
        PreparedStatement preparedStmt = conn.prepareStatement(query);
        preparedStmt.executeUpdate();

        verMantenimientosConsola();
        desconectar();

    }

    public void eliminarMantenimiento(String cod) throws SQLException {
        conectar();

        String query = "DELETE FROM mantenimiento WHERE codMantenimiento='" + cod + "';";
        PreparedStatement preparedStatmt = conn.prepareStatement(query);

        preparedStatmt.executeUpdate();
        System.out.println("Mantenimiento eliminado");
        verMantenimientosConsola();
        desconectar();
    }


    //GESTIÓN MAQUINAS
    public void verMaquinasConsola() throws SQLException {
        conectar();

        String query = "SELECT * FROM maquina";
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery(query);

        while (rs.next()) {
            String cod = rs.getString("codMaquina");
            String nombre = rs.getString("descripcion");

            System.out.println("codigo: " + cod + " Nombre: " + nombre);
        }
        desconectar();
    }

    public void insertarMaquina(String cod, String Nombre) throws SQLException {
        conectar();
        // query con los datos a insertar
        String query = "INSERT INTO maquina VALUES('" + cod + "','" + Nombre + "')";

        preparedStmt = conn.prepareStatement(query);

        // execute the preparedstatement
        preparedStmt.execute();

        verMaquinasConsola();
        desconectar();
    }


    public void actualizarMaquina(String cod, String nombre) throws SQLException {
        conectar();

        String query = "UPDATE maquina SET descripcion='" + nombre + "' WHERE codMaquina='" + cod + "';";
        PreparedStatement preparedStmt = conn.prepareStatement(query);
        preparedStmt.executeUpdate();

        verMaquinasConsola();
        desconectar();
    }

    public void eliminarMaquina(String cod) throws SQLException {
        conectar();

        String query = "DELETE FROM maquina WHERE codMaquina='" + cod + "';";
        PreparedStatement preparedStatmt = conn.prepareStatement(query);

        preparedStatmt.executeUpdate();
        System.out.println("Maquina eliminado");
        verMaquinasConsola();
        desconectar();
    }

    //GESTIÓN TAREAS
    public void verTareasConsola() throws SQLException {
        conectar();

        String query = "SELECT * FROM tarea";
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery(query);

        while (rs.next()) {
            String cod = rs.getString("codTarea");
            String nombre = rs.getString("descripcion");
            String codMaquina = rs.getString("maquina_codMaquina");

            System.out.println("codigo: " + cod + " Nombre: " + nombre + " Máquina: " + codMaquina);
        }
        desconectar();
    }

    public void insertarTarea(String cod, String Nombre, String maquina) throws SQLException {
        conectar();
        String query = "";
        //si la máquina está vacía, no la insertamos
        if (maquina.equalsIgnoreCase("")) {
            query = "INSERT INTO maquina (codMaquina,descripcion) VALUES('" + cod + "','" + Nombre + "','')";

        } else {
            query = "INSERT INTO maquina VALUES('" + cod + "','" + Nombre + "','" + maquina + "')";
        }

        // query con los datos a insertar
        preparedStmt = conn.prepareStatement(query);

        // execute the preparedstatement
        preparedStmt.execute();

        verTareasConsola();
        desconectar();
    }


    public void actualizarTarea(String cod, String nombre, String maquina) throws SQLException {
        conectar();
        String query = "";

        //si no tiene maquina asignada, no la insertamos
        if (maquina.equalsIgnoreCase("")) {
            query = "UPDATE tarea (codTarea,descripcion) SET descripcion='" + nombre + "' WHERE codTarea='" + cod + "';";
        } else {
            query = "UPDATE tarea SET descripcion='" + nombre + "', maquina_codMaquina='" + maquina + "' WHERE codTarea='" + cod + "';";
        }
        PreparedStatement preparedStmt = conn.prepareStatement(query);
        preparedStmt.executeUpdate();

        verTareasConsola();
        desconectar();
    }

    public void eliminarTarea(String cod) throws SQLException {
        conectar();

        String query = "DELETE FROM tarea WHERE codTarea='" + cod + "';";
        PreparedStatement preparedStatmt = conn.prepareStatement(query);

        preparedStatmt.executeUpdate();
        System.out.println("Tarea eliminada");
        verTareasConsola();
        desconectar();
    }
}
