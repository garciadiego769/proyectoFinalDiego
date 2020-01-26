package com.diego.UI.Informes;

import com.diego.Informes.GeneradorInformes;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InicioInformes extends javax.swing.JFrame{
    private JPanel panel;
    private JTextField fechaDesdeTxt;
    private JTextField fechaHastaTxt;
    private JLabel fechaDesdelbl;
    private JLabel fechaHastalbl;
    private JButton generarInformesbtn;
    private JLabel mensajelbl;

    public InicioInformes() throws HeadlessException {
        add(panel);

        generarInformesbtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //comprobamos que han introducido datos y no hay campos en blanco
                if (fechaDesdeTxt.getText().isEmpty() || fechaHastaTxt.getText().isEmpty()){
                    mensajelbl.setText("¡Es obligatorio introducir las fechas!");
                }else{
                    //llamamos a la clase que genera los informes
                    GeneradorInformes generaInformes=new GeneradorInformes(fechaDesdeTxt.getText(),fechaHastaTxt.getText());
                }
            }
        });
    }
}
