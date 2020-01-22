package com.diego.UI;

import com.diego.BD;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class PantallaTiempo extends javax.swing.JFrame{
    private JPanel panel;
    private JTextField tiempoTxt;
    private JButton enviarBtn;

    public static void main(String[] args) throws SQLException {
        JFrame frame = new JFrame("pantallaTiempo");
        frame.setContentPane(new PantallaTiempo().panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    public PantallaTiempo() {
        setSize(400,400);
        add(panel);

        enviarBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                //guardamos
               // BD.guardaDatos.add(tiempoTxt.getText());

                PantallaMantenimiento menuMantenimiento = null;
                try {
                    menuMantenimiento = new PantallaMantenimiento();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                menuMantenimiento.setVisible(true);
                menuMantenimiento.setLocationRelativeTo(null);
            }
        });
    }
}
