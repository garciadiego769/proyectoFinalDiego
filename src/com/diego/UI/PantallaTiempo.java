package com.diego.UI;

import javax.swing.*;
import java.awt.*;
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

    }
}
