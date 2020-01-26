package com.diego.UI;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

public class PantallaFinal extends javax.swing.JFrame{

    private JPanel panel;
    private JLabel mensajeLbl;


    public PantallaFinal(String mensaje){
        add(panel);
        setSize(400, 400);

        mensajeLbl.setText(mensaje);
    }
}
