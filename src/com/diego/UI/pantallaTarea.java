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

    BD baseDeDatos = new BD();

    public static void main(String[] args) throws SQLException {
        JFrame frame = new JFrame("pantallaTarea");
        frame.setContentPane(new pantallaTarea().panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }


    public pantallaTarea() throws SQLException {
        setSize(400, 400);
        add(panel);

        ArrayList nombres = baseDeDatos.verTareas();

        //recuperamos el Array con todos los nombres de las tareas de la BD
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
                //guardamos la tarea en el array de las opciones seleccionadas
                BD.guardaDatos.add(tarea1btn.getText());

                System.out.println(baseDeDatos.guardaDatos);
                //comprobamos si la tarea tiene una máquina asignada para mostrar un menú u otro
                try {

                    int numeroMaquina = baseDeDatos.verNumeroMaquinaDisponible(tarea1btn.getText());

                    if (numeroMaquina > 0) {
                        //si tenemos datos en el Arraylist, tiene una máquina asignada
                        PantallaMaquina menuMaquina = null;

                        menuMaquina = new PantallaMaquina(tarea1btn.getText());
                        menuMaquina.setVisible(true);
                        menuMaquina.setLocationRelativeTo(null);
                    } else {
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
                //guardamos la tarea en el array de las opciones seleccionadas
                BD.guardaDatos.add(tarea2btn.getText());

                int numeroMaquina = 0;
                try {
                    numeroMaquina = baseDeDatos.verNumeroMaquinaDisponible(tarea2btn.getText());
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
                if (numeroMaquina > 0) {
                    //si tenemos datos en el Arraylist, tiene una máquina asignada
                    PantallaMaquina menuMaquina = null;

                    //Le pasamos la tarea que estamos haciendo para que vea qué máquina tiene asociada

                    try {
                        menuMaquina = new PantallaMaquina(tarea2btn.getText());
                        menuMaquina.setVisible(true);
                        menuMaquina.setLocationRelativeTo(null);
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                    menuMaquina.setVisible(true);
                    menuMaquina.setLocationRelativeTo(null);
                } else {
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
                //guardamos la tarea en el array de las opciones seleccionadas
                BD.guardaDatos.add(tarea3btn.getText());
                int numeroMaquina = 0;
                try {
                    numeroMaquina = baseDeDatos.verNumeroMaquinaDisponible(tarea3btn.getText());

                } catch (SQLException ex) {
                    ex.printStackTrace();
                }

                if (numeroMaquina > 0) {
                    //si tenemos datos en el Arraylist, tiene una máquina asignada
                    PantallaMaquina menuMaquina = null;

                    try {
                        menuMaquina = new PantallaMaquina(tarea3btn.getText());
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }

                    menuMaquina.setVisible(true);
                    menuMaquina.setLocationRelativeTo(null);
                } else {
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
                //guardamos la tarea en el array de las opciones seleccionadas
                BD.guardaDatos.add(tarea4btn.getText());
                System.out.println(baseDeDatos.guardaDatos);

                int numeroMaquina = 0;
                try {
                    numeroMaquina = baseDeDatos.verNumeroMaquinaDisponible(tarea4btn.getText());
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }

                if (numeroMaquina > 0) {
                    //si tenemos datos en el Arraylist, tiene una máquina asignada
                    PantallaMaquina menuMaquina = null;
                    try {
                        menuMaquina = new PantallaMaquina(tarea4btn.getText());
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                    menuMaquina.setVisible(true);
                    menuMaquina.setLocationRelativeTo(null);
                } else {
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
                //guardamos la tarea en el array de las opciones seleccionadas
                BD.guardaDatos.add(tarea5btn.getText());

                int numeroMaquina = 0;
                try {
                    numeroMaquina = baseDeDatos.verNumeroMaquinaDisponible(tarea5btn.getText());
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }

                if (numeroMaquina > 0) {
                    //si tenemos datos en el Arraylist, tiene una máquina asignada
                    PantallaMaquina menuMaquina = null;

                    try {
                        menuMaquina = new PantallaMaquina(tarea5btn.getText());
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                    menuMaquina.setVisible(true);
                    menuMaquina.setLocationRelativeTo(null);
                } else {
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
