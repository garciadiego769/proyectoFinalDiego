package com.diego.UI;

import com.diego.BD;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;

public class PantallaUsuario extends javax.swing.JFrame {
    private JPanel panel;
    private JButton trabajador4btn;
    private JButton trabajador3btn;
    private JButton trabajador2btn;
    private JButton trabajador1btn;

    public static void main(String[] args) throws SQLException {
        JFrame frame = new JFrame("PantallaUsuario");
        frame.setContentPane(new PantallaUsuario().panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    BD baseDeDatos = new BD();

    public PantallaUsuario() throws SQLException {

        //recuperamos el Array con todos los nombres de los trabajadores de la BD
        ArrayList nombres = baseDeDatos.verTrabajadores();
        System.out.println(nombres);

        String trabajador1 = (String) nombres.get(0);
        trabajador1btn.setText(trabajador1);

        String trabajador2 = (String) nombres.get(1);
        trabajador2btn.setText(trabajador2);

        String trabajador3 = (String) nombres.get(2);
        trabajador3btn.setText(trabajador3);

        String trabajador4 = (String) nombres.get(3);
        trabajador4btn.setText(trabajador4);
        trabajador1btn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                //trabajador 1

                //lleva al mismo menú
                pantallaTarea menuTarea = null;
                try {
                    //le pasamos el trabajador que se ha seleccionado
                    baseDeDatos.recogeNombreTrabajador(trabajador1btn.getText());

                    menuTarea = new pantallaTarea();

                    menuTarea.setVisible(true);
                    menuTarea.setLocationRelativeTo(null);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });
        trabajador2btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //trabajador 2

                //lleva al mismo menú
                pantallaTarea menuTarea = null;
                try {
                    //le pasamos el trabajador que se ha seleccionado
                    baseDeDatos.recogeNombreTrabajador(trabajador2btn.getText());

                    menuTarea = new pantallaTarea();
                    menuTarea.setVisible(true);
                    menuTarea.setLocationRelativeTo(null);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });
        trabajador3btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //trabajador 3

                //lleva al mismo menú
                pantallaTarea menuTarea = null;
                try {
                    //le pasamos el trabajador que se ha seleccionado
                    baseDeDatos.recogeNombreTrabajador(trabajador3btn.getText());

                    menuTarea = new pantallaTarea();
                    menuTarea.setVisible(true);
                    menuTarea.setLocationRelativeTo(null);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });
        trabajador4btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //trabajador 4

                //lleva al mismo menú
                pantallaTarea menuTarea = null;
                try {
                    //le pasamos el trabajador que se ha seleccionado
                    baseDeDatos.recogeNombreTrabajador(trabajador4btn.getText());

                    menuTarea = new pantallaTarea();
                    menuTarea.setVisible(true);
                    menuTarea.setLocationRelativeTo(null);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });
    }
}
