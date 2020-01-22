package com.diego.UI;

import com.diego.BD;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;

public class PantallaMantenimiento extends javax.swing.JFrame{
    private JTextField tiempoTxt;
    private JPanel panel;
    private JButton registrarBtn;
    private JRadioButton mantenimiento1Radiobtn;
    private JRadioButton mantenimiento2Radiobtn;
    private JRadioButton mantenimiento3Radiobtn;

    BD baseDeDatos = new BD();

    public static void main(String[] args) throws SQLException {
        JFrame frame = new JFrame("pantallaMantenimiento");
        frame.setContentPane(new PantallaMantenimiento().panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    public PantallaMantenimiento() throws SQLException {
        setSize(600, 600);
        add(panel);

        ArrayList nombres = baseDeDatos.verMantenimientos();

        //recuperamos el Array con todos los nombres de las tareas de la BD
        String mantenimiento1 = (String) nombres.get(0);
        mantenimiento1Radiobtn.setText(mantenimiento1);

        String mantenimiento2 = (String) nombres.get(1);
        mantenimiento2Radiobtn.setText(mantenimiento2);

        String mantenimiento3 = (String) nombres.get(2);
        mantenimiento3Radiobtn.setText(mantenimiento3);

        registrarBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                //guardamos

            }
        });
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
