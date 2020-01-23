package com.diego.UI;

import com.diego.BD;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class PantallaResumen extends javax.swing.JFrame {
    private JPanel panel;
    private JLabel titulotrabajadorlbl;
    private JLabel trabajadorlbl;
    private JLabel tarealbl;
    private JLabel maquinalbl;
    private JLabel tiempoTarealbl;
    private JLabel mantenimientolbl;
    private JLabel tiempoMantenimientolbl;
    private JButton registrarButton;
    private JButton eliminarButton;


    public static void main(String[] args) throws SQLException {
        JFrame frame = new JFrame("pantallaResumen");
        frame.setContentPane(new PantallaResumen().panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    public PantallaResumen() {
        setSize(700, 300);
        add(panel);
        Boolean tieneMaquina=false;

        //Para saber si recuperamos unos datos u otros, comprobamos las posiciones que tiene para ver si han seleccionado una máquina
        for (int i = 0; i < BD.guardaDatos.size(); i++) {
            if (BD.guardaDatos.get(i).contains("maquina:")){
                tieneMaquina=true;
            }
        }
        if (tieneMaquina){
            //rellenamos los datos con lo que hemos ido guardando en el Array
            trabajadorlbl.setText(BD.guardaDatos.get(0));
            tarealbl.setText(BD.guardaDatos.get(1));
            maquinalbl.setText(BD.guardaDatos.get(2));
            tiempoTarealbl.setText(BD.guardaDatos.get(3));
            mantenimientolbl.setText(BD.guardaDatos.get(4));
            tiempoMantenimientolbl.setText(BD.guardaDatos.get(5));
        }else {
            //rellenamos los datos con lo que hemos ido guardando en el Array pero SIN LA MAQUINA
            trabajadorlbl.setText(BD.guardaDatos.get(0));
            tarealbl.setText(BD.guardaDatos.get(1));
            tiempoTarealbl.setText(BD.guardaDatos.get(2));
            mantenimientolbl.setText(BD.guardaDatos.get(3));
            tiempoMantenimientolbl.setText(BD.guardaDatos.get(4));
        }


        registrarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //GUARDAR EN LA BBDD

            }
        });
    }
}
