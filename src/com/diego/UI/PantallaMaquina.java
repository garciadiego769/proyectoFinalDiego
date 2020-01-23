package com.diego.UI;

import com.diego.BD;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;

public class PantallaMaquina extends javax.swing.JFrame {
    private JButton maquinabtn;
    private JPanel panel;

    BD baseDeDatos = new BD();

   /* public static void main(String[] args) throws SQLException {
        JFrame frame = new JFrame("pantallaMaquina");
        frame.setContentPane(new PantallaMaquina().panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
*/

    public PantallaMaquina(String nombreTarea) throws SQLException {
        setSize(400, 400);
        add(panel);

        //buscamos qué máquina tiene asociada esa tarea

        ArrayList nombres = baseDeDatos.verMaquina(nombreTarea);

        //recuperamos el Array con todos los nombres de las tareas de la BD
        String maquina = (String) nombres.get(0);
        maquinabtn.setText(maquina);

        maquinabtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                //añadimos la opción a todas las anteriores
                BD.guardaDatos.add("maquina: " + maquinabtn.getText()); //para pdoer distinguir si tiene máquina o no

                //Al seleccionarla abrimos el menú que pide el tiempo
                PantallaTiempo menuTiempo = null;
                menuTiempo = new PantallaTiempo();
                menuTiempo.setVisible(true);
                menuTiempo.setLocationRelativeTo(null);
                
            }
        });
    }


}
