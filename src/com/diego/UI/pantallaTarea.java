package com.diego.UI;

import com.diego.BD;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;

public class pantallaTarea extends javax.swing.JFrame {

    private JPanel panel;
    private JButton tarea1btn;
    private JButton tarea2btn;
    private JButton tarea3btn;
    private JButton tarea4btn;
    private JButton tarea5btn;

    BD baseDeDatos=new BD();

    public static void main(String[] args) throws SQLException {
        JFrame frame = new JFrame("pantallaTarea");
        frame.setContentPane(new pantallaTarea().panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }


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


        tarea1btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //comprobamos si la tarea tiene una máquina asignada para mostrar un menú u otro
                try {
                    ArrayList maquina=baseDeDatos.verMaquina(tarea1btn.getText());
                    if (maquina.size()>0){
                        //si tenemos datos en el Arraylist, tiene una máquina asignada
                        PantallaMaquina menuMaquina = null;

                        menuMaquina = new PantallaMaquina();
                        menuMaquina.setVisible(true);
                        menuMaquina.setLocationRelativeTo(null);
                    } else{
                        //Si tiene datos, vamos directamente al menú que pide el tiempo
                        PantallaTiempo menuTiempo = null;

                        menuTiempo = new PantallaTiempo();
                        menuTiempo.setVisible(true);
                        menuTiempo.setLocationRelativeTo(null);
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });
        tarea2btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ArrayList maquina= null;
                try {
                    maquina = baseDeDatos.verMaquina(tarea2btn.getText());
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
                assert maquina != null;
                String maquinaResultado= (String) maquina.get(0);
                if (!maquinaResultado.equalsIgnoreCase("null")){
                    //si tenemos datos en el Arraylist, tiene una máquina asignada
                    PantallaMaquina menuMaquina = null;

                    try {
                        menuMaquina = new PantallaMaquina();
                        menuMaquina.setVisible(true);
                        menuMaquina.setLocationRelativeTo(null);
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                    menuMaquina.setVisible(true);
                    menuMaquina.setLocationRelativeTo(null);
                } else{
                    //Si tiene datos, vamos directamente al menú que pide el tiempo
                    PantallaTiempo menuTiempo = null;

                    menuTiempo = new PantallaTiempo();
                    menuTiempo.setVisible(true);
                    menuTiempo.setLocationRelativeTo(null);
                }
            }
        });
        tarea3btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ArrayList maquina= null;
                try {
                    maquina = baseDeDatos.verMaquina(tarea3btn.getText());
                    System.out.println(maquina);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
                if (maquina.size()>0){
                    //si tenemos datos en el Arraylist, tiene una máquina asignada
                    PantallaMaquina menuMaquina = null;

                    try {
                        menuMaquina = new PantallaMaquina();
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                    menuMaquina.setVisible(true);
                    menuMaquina.setLocationRelativeTo(null);
                } else{
                    //Si tiene datos, vamos directamente al menú que pide el tiempo
                    PantallaTiempo menuTiempo = null;

                    menuTiempo = new PantallaTiempo();
                    menuTiempo.setVisible(true);
                    menuTiempo.setLocationRelativeTo(null);
                }
            }
        });
        tarea4btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ArrayList maquina= null;
                try {
                    maquina = baseDeDatos.verMaquina(tarea4btn.getText());
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
                if (maquina.size()>0){
                    //si tenemos datos en el Arraylist, tiene una máquina asignada
                    PantallaMaquina menuMaquina = null;

                    try {
                        menuMaquina = new PantallaMaquina();
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                    menuMaquina.setVisible(true);
                    menuMaquina.setLocationRelativeTo(null);
                } else{
                    //Si tiene datos, vamos directamente al menú que pide el tiempo
                    PantallaTiempo menuTiempo = null;

                    menuTiempo = new PantallaTiempo();
                    menuTiempo.setVisible(true);
                    menuTiempo.setLocationRelativeTo(null);
                }
            }
        });
        tarea5btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ArrayList maquina= null;
                try {
                    maquina = baseDeDatos.verMaquina(tarea5btn.getText());
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
                if (maquina.size()>0){
                    //si tenemos datos en el Arraylist, tiene una máquina asignada
                    PantallaMaquina menuMaquina = null;

                    try {
                        menuMaquina = new PantallaMaquina();
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                    menuMaquina.setVisible(true);
                    menuMaquina.setLocationRelativeTo(null);
                } else{
                    //Si tiene datos, vamos directamente al menú que pide el tiempo
                    PantallaTiempo menuTiempo = null;

                    menuTiempo = new PantallaTiempo();
                    menuTiempo.setVisible(true);
                    menuTiempo.setLocationRelativeTo(null);
                }
            }
        });
    }

}
