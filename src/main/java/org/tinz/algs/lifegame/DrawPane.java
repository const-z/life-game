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

//    private LifeGame.Universe universe;
    private static int fontSize = 10;
    public static final int CELL_SIZE = 7;
    private int uniWidth;
    private int uniHeight;
    private Cell[] population;

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
//        g.setColor(this.getBackground());
        g.setColor(Color.black);
        g.fillRect(0, 0, g.getClipBounds().width, g.getClipBounds().height);
//        g.fillRect(0, 0, uniWidth * CELL_SIZE, uniHeight * CELL_SIZE+CELL_SIZE);//g.getClipBounds().width, g.getClipBounds().height);
//        GraphicsEnvironment e = GraphicsEnvironment.getLocalGraphicsEnvironment();
//        Font[] fonts = e.getAllFonts(); // Get the fonts
//        Font font = new Font("Courier New", 0, 12);
//        for (Font f : fonts) {
////            System.out.println(f.getFontName());
//            if (f.getFontName().equals("Symbol")) {
//                font= f;
//            }
//        }

//        g.setFont(new Font("Webdings", 0, 12));
//        g.setColor(Color.red);
        for (int i = 0; i < population.length; i++) {
            int y = (i) / uniWidth;
            int x = (i) % uniWidth;
//            System.out.println("id = "+i+"  x="+x+"  y="+y);
//            LifeGame.Cell cell = universe.population[i];
//            try{
//                Status s = cell.getStatus();
//            } catch (Exception e){
//                System.err.println("------------------population = "+universe.population[i].getStatus()+"   cell="+cell+"  i = "+i);
////                e.printStackTrace();
//                
//            }
//            if (population[i]==null){
//                System.out.println("p = null "+i);
//            }
            if (population[i].getStatus() == Status.LIVE) {
                if (population[i].getFaction() == 0) {
                    g.setColor(Color.green);
                } else if (population[i].getFaction() == 1) {
                    g.setColor(Color.red);
                }
                g.fillOval(x * CELL_SIZE, y * CELL_SIZE, CELL_SIZE, CELL_SIZE);
//                g.fillRect(x * CELL_SIZE, y * CELL_SIZE, CELL_SIZE, CELL_SIZE);
//                g.setColor(Color.black);
//                g.drawRoundRect(x * CELL_SIZE, y * CELL_SIZE, CELL_SIZE, CELL_SIZE, CELL_SIZE/8, CELL_SIZE/8);
//                g.fillOval(0, 0, CELL_SIZE-5, CELL_SIZE-5);
//                g.drawString("asdfghjkl;ASDXCVBNM<>)(*&^%$#@!123", 0, CELL_SIZE);
//                g.drawString("" + i, x * CELL_SIZE, y * CELL_SIZE+CELL_SIZE);
            } else {
//                g.setColor(Color.gray);
            }
//            g.drawString("" + new Character(i), x * CELL_SIZE, y * CELL_SIZE+CELL_SIZE);
        }

        /////////////////////////

//        for (int j = 0; j < universe.getHeight(); j++) {
////            int live = 0;
//            for (int i = 0; i < universe.getWidth(); i++) {
//                LifeGame.Cell cell = universe.getCellByCoordinates(i, j, 0);
//                if (cell != null) {
//                    if (cell.getStatus() == LifeGame.Status.LIVE) {
////                        g.setColor(Color.green);
////                        int cb = cell.getAge();
////                        cb += 255;
////                        int cr = 0;
////                        if (cb >= 255) {
////                            cr = cb % 255;
////                            cb = 255;
////                            System.out.println("cr = " + cr);
////                        }
////                        Color color = new Color(cr, 255, cb);//new Color(cell.getAge());
////                        g.setColor(color);
//                        if (cell.getFaction() == 0) {
//                            g.setColor(Color.white);
//                        } else if (cell.getFaction() == 1) {
//                            g.setColor(Color.red);
//                        }
//
////                        if (fontSize<g.getFontMetrics().stringWidth(cell.getAge()+"")){
////                            fontSize = g.getFontMetrics().stringWidth(cell.getAge()+"");
////                        }
////                        g.drawString(cell.getAge() + "", cell.getX() * fontSize, cell.getY() * fontSize);
//                        g.fillOval(cell.getX() * 10, cell.getY() * 10, 10, 10);
//                    } 
////                    else {
////                        g.setColor(Color.black);
////                    }
////                      g.fillRect(cell.getX() * 10, cell.getY() * 10, 10, 10);
////                      g.fillOval(cell.getX() * 10, cell.getY() * 10, 10, 10);
////                      g.drawOval(cell.getX() * 10, cell.getY() * 10, 10, 10);
//                }
//            }
////            System.out.println(" " + live);
//        }
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
