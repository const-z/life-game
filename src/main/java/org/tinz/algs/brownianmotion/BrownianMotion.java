/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.tinz.algs.brownianmotion;

import java.awt.BorderLayout;
import java.awt.Frame;
import java.util.LinkedList;
import java.util.List;
import org.tinz.algs.lifegame.LifeGame;
import org.tinz.algs.utils.Display;

/**
 *
 * @author user
 */
public class BrownianMotion {

    static class Point {

        int x;
        int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public String toString() {
            return "[ " + x + " ; " + y + " ]";
        }
    }

    Point startPoint;
    List<Point> points;
    int rmax = 50;
    int maxWidth;
    int maxHeight;

    public BrownianMotion(Point startPoint, int maxWidth, int maxHeight) {
        this.startPoint = startPoint;
        points = new LinkedList<>();
        points.add(startPoint);
        this.maxWidth = maxWidth;
        this.maxHeight = maxHeight;
    }

    private Point getLastPoint() {
        return points.get(points.size() - 1);
    }

    public void nextStep() {
        Point xy1 = getLastPoint();        
        int x2 = -1;
        do {
            int fi = (int) Math.round(Math.random() * 360);
            int r = (int) Math.round(Math.random() * rmax);
            x2 = (int) Math.round(xy1.x + r * Math.cos(fi * Math.PI / 180));
        } while (x2 < 0 || x2 > maxWidth);
        int y2 = -1;
        do {
            int fi = (int) Math.round(Math.random() * 360);
            int r = (int) Math.round(Math.random() * rmax);
            y2 = (int) Math.round(xy1.y + r * Math.sin(fi * Math.PI / 180));
        } while (y2 < 0 || y2 > maxHeight);
        Point newPoint = new Point(x2, y2);
//        System.out.println(newPoint.toString());
        points.add(newPoint);
    }

    public List<Point> getPoints() {
        return points;
    }

    public static void main(String... args) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                int dwidth = 800;
                int dheight = 600;
                final BrownianMotion bm = new BrownianMotion(new Point(dwidth / 2, dheight / 2), dwidth, dheight);
                final Display disp = new Display();
                disp.setSize(dwidth, dheight);
                final DrawPane dp = new DrawPane(bm);
                disp.setContentPane(dp);
                disp.setVisible(true);

                Thread thread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        while (true) {
                            try {
                                Thread.sleep(10);
                            } catch (InterruptedException ex) {
                            }
                            bm.nextStep();
                            if (disp.getState() != Frame.ICONIFIED) {
                                dp.repaint();
                            }
                        }
                    }
                });
                thread.start();
            }
        });

    }
}
