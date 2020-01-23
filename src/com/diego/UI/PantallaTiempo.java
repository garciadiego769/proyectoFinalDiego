package com.diego.UI;

import com.diego.BD;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.SQLException;

public class PantallaTiempo extends javax.swing.JFrame implements WindowListener {
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
        setSize(600,400);
        add(panel);

        JFrame frame = new JFrame("pantallaTiempo");
      //  frame.addWindowListener(windowClosing());
        frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));

        enviarBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                //guardamos
               BD.guardaDatos.add(tiempoTxt.getText());

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

    @Override
    public void windowOpened(WindowEvent windowEvent) {

    }

    @Override
    public void windowClosing(WindowEvent windowEvent) {
        System.exit(0);
    }

    @Override
    public void windowClosed(WindowEvent windowEvent) {

    }

    @Override
    public void windowIconified(WindowEvent windowEvent) {

    }

    @Override
    public void windowDeiconified(WindowEvent windowEvent) {

    }

    @Override
    public void windowActivated(WindowEvent windowEvent) {

    }

    @Override
    public void windowDeactivated(WindowEvent windowEvent) {

    }
}
