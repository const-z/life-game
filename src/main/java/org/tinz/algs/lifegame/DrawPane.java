/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.tinz.algs.lifegame;

import java.awt.Color;
import java.awt.Graphics;
import org.tinz.algs.lifegame.LifeGame.*;

/**
 *
 * @author tinz
 */
public class DrawPane extends javax.swing.JPanel {

    public static int CELL_SIZE = 2;
    private int uniWidth;
    private int uniHeight;
    private Cell[] population;
    public final Color COLOR_BACKGROUD = Color.black;
    public final Color COLOR_1_FACTION = Color.green;
    public final Color COLOR_2_FACTION = Color.red;

    /**
     * Creates new form DrawPane
     */
    public DrawPane() {
        initComponents();
    }

    public DrawPane(int uniWidth, int uniHeight) {
        initComponents();
        this.uniWidth = uniWidth;
        this.uniHeight = uniHeight;
    }

    public void printPopulation(Graphics g) {
        if (population == null) {
            return;
        }
        g.setColor(COLOR_BACKGROUD);
        g.fillRect(0, 0, g.getClipBounds().width, g.getClipBounds().height);
        for (int i = 0; i < population.length; i++) {
            int y = (i) / uniWidth;
            int x = (i) % uniWidth;
            if (population[i].getStatus() == Status.LIVE) {
                if (population[i].getFaction() == 0) {
                    g.setColor(COLOR_1_FACTION);
                } else if (population[i].getFaction() == 1) {
                    g.setColor(COLOR_2_FACTION);
                }
                if (population[i].getAge() < 2) {
                    g.setColor(g.getColor().darker().darker());
                } else if (population[i].getAge() < 5) {
                    g.setColor(g.getColor().darker());
                }
                g.fillRect(x * CELL_SIZE, y * CELL_SIZE, CELL_SIZE, CELL_SIZE);
            }
        }
    }

    public Cell[] getPopulation() {
        return population;
    }

    public void setPopulation(Cell[] population) {
        this.population = population;
    }

    @Override
    protected void paintComponent(Graphics g) {
//        super.paintComponent(g);
        //g.fillRect(20, 20, 100, 200);
        printPopulation(g);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
