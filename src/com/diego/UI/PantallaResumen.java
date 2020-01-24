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

        String trabajador="";
        String tarea="";
        String maquinaCortada="";
        String tiempoTarea="";
        String mantenimiento="";
        String tiempoMantenimiento="";

        //Para saber si recuperamos unos datos u otros, comprobamos las posiciones que tiene para ver si han seleccionado una máquina
        for (int i = 0; i < BD.guardaDatos.size(); i++) {
            if (BD.guardaDatos.get(i).contains("maquina:")){
                tieneMaquina=true;
            }
        }
        if (tieneMaquina){
            //rellenamos los datos con lo que hemos ido guardando en el Array
            trabajador=BD.guardaDatos.get(0);
            trabajadorlbl.setText(trabajador);

            tarea=BD.guardaDatos.get(1);
            tarealbl.setText(tarea);

            //Quitamos de la máquina el "maquina:" para quedarnos sólo con el nombre
            maquinaCortada=BD.guardaDatos.get(2).substring(9);
            maquinalbl.setText(maquinaCortada);

            tiempoTarea=BD.guardaDatos.get(3);
            tiempoTarealbl.setText(tiempoTarea);

            mantenimiento=BD.guardaDatos.get(4);
            mantenimientolbl.setText(mantenimiento);

            tiempoMantenimiento=BD.guardaDatos.get(5);
            tiempoMantenimientolbl.setText(tiempoMantenimiento);
        }else {
            //rellenamos los datos con lo que hemos ido guardando en el Array pero SIN LA MAQUINA
            trabajador=BD.guardaDatos.get(0);
            trabajadorlbl.setText(trabajador);

            tarea=BD.guardaDatos.get(1);
            tarealbl.setText(tarea);

            tiempoTarea=BD.guardaDatos.get(2);
            tiempoTarealbl.setText(tiempoTarea);

            mantenimiento=BD.guardaDatos.get(3);
            mantenimientolbl.setText(tiempoMantenimiento);

            tiempoMantenimiento=BD.guardaDatos.get(4);
            tiempoMantenimientolbl.setText(tiempoMantenimiento);
        }


        String finalTrabajador = trabajador;
        String finalTarea = tarea;
        String finalMaquinaCortada = maquinaCortada;
        String finalTiempoTarea = tiempoTarea;
        String finalMantenimiento = mantenimiento;
        String finalTiempoMantenimiento = tiempoMantenimiento;
        registrarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //GUARDAR EN LA BBDD
                BD baseDeDatos=new BD();
                try {
                    baseDeDatos.insertarActividad(finalTrabajador, finalTarea, finalMaquinaCortada, finalTiempoTarea, finalMantenimiento, finalTiempoMantenimiento);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }

            }
        });
    }
}
