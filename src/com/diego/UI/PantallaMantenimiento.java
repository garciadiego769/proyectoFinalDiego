package com.diego.UI;

import com.diego.BD;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;

public class PantallaMantenimiento extends javax.swing.JFrame {
    private JTextField tiempoTxt;
    private JPanel panel;
    private JButton registrarBtn;
    private JRadioButton mantenimiento1Radiobtn;
    private JRadioButton mantenimiento2Radiobtn;
    private JRadioButton mantenimiento3Radiobtn;
    private JLabel mensajeLabel;

    ButtonGroup grupoBotones = new ButtonGroup();


    BD baseDeDatos = new BD();
    String seleccion;

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

        //linkamos los 3 radiobuttons para que no sean independientes y sólo se pueda marcar uno
        grupoBotones.add(mantenimiento1Radiobtn);
        grupoBotones.add(mantenimiento2Radiobtn);
        grupoBotones.add(mantenimiento3Radiobtn);

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
                //se han introducido datos?
                if (tiempoTxt.getText().isEmpty()) {
                    //no hacemos nada
                    mensajeLabel.setText("¡Se debe introducir el tiempo!");
                } else {


                    //revisamos qué radiobutton se ha marcado
                    if (mantenimiento1Radiobtn.isSelected()) {
                        seleccion = mantenimiento1Radiobtn.getText();
                    } else if (mantenimiento2Radiobtn.isSelected()) {
                        seleccion = mantenimiento2Radiobtn.getText();
                    } else if (mantenimiento3Radiobtn.isSelected()) {
                        seleccion = mantenimiento3Radiobtn.getText();
                    } //si no es ninguno de los 3 se deja en blanco
                    else {
                        seleccion = "";
                    }
                    System.out.println(seleccion);
                    //guardamos
                    BD.guardaDatos.add(seleccion);

                    //guardamos el texto
                    BD.guardaDatos.add(tiempoTxt.getText());

                    //mostramos todo
                    System.out.println(BD.guardaDatos);

                    //abrimos el último menú
                    PantallaResumen menuResumen = null;
                    menuResumen = new PantallaResumen();
                    menuResumen.setVisible(true);
                    menuResumen.setLocationRelativeTo(null);


                }


            }
        });
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
