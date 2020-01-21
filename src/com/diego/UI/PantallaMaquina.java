package com.diego.UI;

import com.diego.BD;

import javax.swing.*;
import java.sql.SQLException;
import java.util.ArrayList;

public class PantallaMaquina extends javax.swing.JFrame{
    private JButton maquinabtn;
    private JPanel panel;

    BD baseDeDatos=new BD();

    public static void main(String[] args) throws SQLException {
        JFrame frame = new JFrame("pantallaMaquina");
        frame.setContentPane(new PantallaMaquina().panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }


    public PantallaMaquina() throws SQLException {
        setSize(400, 400);
        add(panel);

        //buscamos qué máquina tiene asociada esa tarea

       /*ArrayList nombres=baseDeDatos.verMaquina();

        //recuperamos el Array con todos los nombres de las tareas de la BD
        System.out.println(nombres);

        String maquina = (String) nombres.get(0);
        maquinabtn.setText(maquina);
*/
    }

}
