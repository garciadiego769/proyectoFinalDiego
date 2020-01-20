package com.diego.UI;

import com.diego.BD;

import javax.swing.*;
import java.sql.SQLException;
import java.util.ArrayList;

public class pantallaTarea extends javax.swing.JFrame {

    private JPanel panel;
    private JButton tarea1btn;
    private JButton tarea2btn;
    private JButton tarea3btn;
    private JButton tarea4btn;
    private JButton tarea5btn;

    BD baseDeDatos;

    //    ArrayList nombres=baseDeDatos.verTareas();
    //
    //        //recuperamos el Array con todos los nombres de las tareas de la BD
    //        System.out.println(nombres);
    //
    //        String tarea1 = (String) nombres.get(0);
    //        tarea1btn.setText(tarea1);
    public pantallaTarea() throws SQLException {
        setSize(400,400);
        add(panel);

        ArrayList nombres=baseDeDatos.verTareas();

        //recuperamos el Array con todos los nombres de las tareas de la BD
        System.out.println(nombres);

        String tarea1 = (String) nombres.get(0);
        tarea1btn.setText(tarea1);

        String tarea2 = (String) nombres.get(1);
        tarea2btn.setText(tarea2);

        String tarea3 = (String) nombres.get(2);
        tarea3btn.setText(tarea3);

        String tarea4 = (String) nombres.get(3);
        tarea4btn.setText(tarea4);

        String tarea5 = (String) nombres.get(4);
        tarea5btn.setText(tarea5);

    }

}
