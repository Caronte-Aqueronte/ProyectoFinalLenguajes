/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package intefazYBackend;

import modelos.TransicionSintactica;
import modelos.TransicionLexica;
import java.awt.HeadlessException;
import java.util.ArrayList;

/**
 *
 * @author Luis Monterroso
 */
public class Contenedor extends javax.swing.JFrame {
    private ArrayList<TransicionLexica> tablaDeTransicionesLexicas;
    private ArrayList<String> palabrasReservadas;
    private ArrayList<TransicionSintactica> tablaDeTrasicionesSintacticas;

    public Contenedor(ArrayList<TransicionLexica> tablaDeTransicionesLexicas, ArrayList<String> palabrasReservadas, ArrayList<TransicionSintactica> tablaDeTrasicionesSintacticas) throws HeadlessException {
        this.tablaDeTransicionesLexicas = tablaDeTransicionesLexicas;
        this.palabrasReservadas = palabrasReservadas;
        this.tablaDeTrasicionesSintacticas = tablaDeTrasicionesSintacticas;
        initComponents();
        MenuPrincipal menu = new MenuPrincipal(tablaDeTransicionesLexicas, palabrasReservadas, tablaDeTrasicionesSintacticas);
        jDesktopPane2.setPreferredSize(menu.getPreferredSize());
        this.jDesktopPane2.add(menu);
        menu.setVisible(true);
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jDesktopPane2 = new javax.swing.JDesktopPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout jDesktopPane2Layout = new javax.swing.GroupLayout(jDesktopPane2);
        jDesktopPane2.setLayout(jDesktopPane2Layout);
        jDesktopPane2Layout.setHorizontalGroup(
            jDesktopPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1328, Short.MAX_VALUE)
        );
        jDesktopPane2Layout.setVerticalGroup(
            jDesktopPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jScrollPane1.setViewportView(jDesktopPane2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1170, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 668, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDesktopPane jDesktopPane2;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}