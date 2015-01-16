/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.tinz.algs.lifegame;

import org.tinz.algs.utils.Display;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Frame;

/**
 *
 * @author tinz
 */
public class LifeGame {

    public static enum Status {

        LIVE,
        DEAD
    }

    public static class Cell implements Cloneable {

        private Status status;
        private int id;
        private int age = 0;
        private int faction = -1;

        @Override
        protected Object clone() throws CloneNotSupportedException {
            return super.clone();
        }

        public Cell() {
        }

        public Cell(Status status, int faction, int id, int age) {
            this.status = status;
            this.id = id;
            this.faction = faction;
            this.age = age;
        }

        public int getFaction() {
            return faction;
        }

        public void setFaction(int faction) {
            this.faction = faction;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public Status getStatus() {
            return status;
        }

        public void setStatus(Status status) {
            if (status == Status.DEAD) {
                setAge(0);
                setFaction(-1);
            } else if (status == Status.LIVE) {
                setAge(1);
            }
            this.status = status;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj instanceof Cell) {
                Cell cell = (Cell) obj;
                return (cell.id == this.id) && (cell.status == this.status);
            } else {
                return false;
            }
        }
    }

    public static class Universe {

        private int width;
        private int height;
        private int depth;
        Cell[] population;
        private int size;
        private int countAlive = 0;

        public Universe(int width, int height, int depth) {
            this.width = width;
            this.height = height;
            this.depth = depth;
            size = width * height;
            population = new Cell[size];
        }

        public int getCountAlive() {
            return countAlive;
        }

        public void setCountAlive(int countAlive) {
            this.countAlive = countAlive;
        }

        public void createNewPopulation() {
            countAlive = 0;
            for (int i = 0; i < size; i++) {
                Status st = Math.random() < 0.5 ? Status.LIVE : Status.DEAD;
                int f = -1;
                if (st == Status.LIVE) {
                    countAlive++;
                    f = Math.random() < 0.5 ? 1 : 0;
                }
                Cell cell = new Cell(st, f, i, 0);
                population[i] = cell;
            }
        }

        public void createNextPopulation() {
            Cell[] tmp = new Cell[size];
            countAlive = 0;
            for (int i = 0; i < size; i++) {
                tmp[i] = new Cell(population[i].status, population[i].faction, i, population[i].age);
                int near = countLiveNearCell(tmp[i]);
//                System.out.println("id = " + i + "   " + near);
                if ((near == 3) && (tmp[i].getStatus() == Status.DEAD)) {
                    int faction = countLikeFactionNearCell(tmp[i], 0);
                    if (faction >= 2) {
                        tmp[i].setFaction(0);
                    } else {
                        tmp[i].setFaction(1);
                    }
                    tmp[i].setStatus(Status.LIVE);
                }
                else if (tmp[i].getStatus() == Status.LIVE) {
                    tmp[i].setAge(tmp[i].getAge() + 1);
                    if ((near < 2) || (near > 3)) {
                        tmp[i].setStatus(Status.DEAD);
                    }
                }

                if (tmp[i].getStatus() == Status.LIVE) {
                    countAlive++;
                }
//                tmp[i] = cell;
//                if (tmp[i]==null){
//                    System.out.println(i+" = "+null);
//                }
            }
            population = new Cell[size];
            population = tmp;
        }

        public boolean progressExists() {
            return true;
        }

        public int countLikeFactionNearCell(Cell cell, int faction) {
            int count = 0;
            int cellid = cell.id;
            int rcellid = (cellid+1)%width;
            int tmpid;            
            if (rcellid != 1) {
                tmpid = cellid - 1;
                if ((tmpid >= 0) && (tmpid < size) && (population[tmpid].getFaction() == faction)) {
                    count++;
                }
                tmpid = cellid + width - 1;
                if (((tmpid >= 0) && (tmpid < size)) && (population[tmpid].getFaction() == faction)) {
                    count++;
                }
                tmpid = cellid - width - 1;
                if ((tmpid >= 0) && (tmpid < size) && (population[tmpid].getFaction() == faction)) {
                    count++;
                }
            }
            ///
            if (rcellid != 0) {
                tmpid = cellid + 1;
                if ((tmpid >= 0) && (tmpid < size) && (population[tmpid].getFaction() == faction)) {
                    count++;
                }
                tmpid = cellid + width + 1;
                if ((tmpid >= 0) && (tmpid < size) && (population[tmpid].getFaction() == faction)) {
                    count++;
                }
                tmpid = cellid - width + 1;
                if ((tmpid >= 0) && (tmpid < size) && (population[tmpid].getFaction() == faction)) {
                    count++;
                }
            }

            tmpid = cellid + width;
            if ((tmpid >= 0) && (tmpid < size) && (population[tmpid].getFaction() == faction)) {
                count++;
            }

            tmpid = cellid - width;
            if ((tmpid >= 0) && (tmpid < size) && (population[tmpid].getFaction() == faction)) {
                count++;
            }
            return count;
        }

        public int countLiveNearCell(Cell cell) {
            int count = 0;
            int cellid = cell.id;
            int rcellid = (cellid+1)%width;
            int tmpid;            
            if (rcellid != 1) {
                tmpid = cellid - 1;
                if ((tmpid >= 0) && (tmpid < size) && (population[tmpid].status == Status.LIVE)) {
                    count++;
                }
                tmpid = cellid + width - 1;
                if (((tmpid >= 0) && (tmpid < size)) && (population[tmpid].status == Status.LIVE)) {
                    count++;
                }
                tmpid = cellid - width - 1;
                if ((tmpid >= 0) && (tmpid < size) && (population[tmpid].status == Status.LIVE)) {
                    count++;
                }
            }
            ///
            if (rcellid != 0) {
                tmpid = cellid + 1;
                if ((tmpid >= 0) && (tmpid < size) && (population[tmpid].status == Status.LIVE)) {
                    count++;
                }
                tmpid = cellid + width + 1;
                if ((tmpid >= 0) && (tmpid < size) && (population[tmpid].status == Status.LIVE)) {
                    count++;
                }
                tmpid = cellid - width + 1;
                if ((tmpid >= 0) && (tmpid < size) && (population[tmpid].status == Status.LIVE)) {
                    count++;
                }
            }

            tmpid = cellid + width;
            if ((tmpid >= 0) && (tmpid < size) && (population[tmpid].status == Status.LIVE)) {
                count++;
            }

            tmpid = cellid - width;
            if ((tmpid >= 0) && (tmpid < size) && (population[tmpid].status == Status.LIVE)) {
                count++;
            }
            return count;
        }

        public int countByStatus(Status status) {
            int count = 0;
            for (Cell cell : population) {
                if (cell.getStatus() == status) {
                    count++;
                }
            }
            return count;
        }

        public int getWidth() {
            return width;
        }

        public void setWidth(int width) {
            this.width = width;
        }

        public int getHeight() {
            return height;
        }

        public void setHeight(int height) {
            this.height = height;
        }

        public int getDepth() {
            return depth;
        }

        public void setDepth(int depth) {
            this.depth = depth;
        }

        public Cell[] getPopulation() {
            return population;
        }

        public void setPopulation(Cell[] population) {
            this.population = population;
        }

        public int getSize() {
            return size;
        }
        
    }

    public static void printPopulation(Universe universe) {
        for (int i = 0; i < universe.size; i++) {
            int y = (i + 1) / universe.width;
            int x = (i + 1) % universe.width;
            System.out.print(universe.population[i].getStatus() == Status.LIVE ? "X" : "-");
            if (x == 0) {
                System.out.println("");
            }
        }
    }
    
    public static void checkNulls(Cell[] population){
        for (int i=0; i<population.length; i++){
            if (population[i]==null){
                System.out.println("id null = "+i);
            }
        }
    }

    public static void main(String[] args) throws Exception {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                final Display disp = new Display();
                disp.setSize(1080, 900);
                disp.setLocationRelativeTo(null);                
                int s = DrawPane.CELL_SIZE;
                final DrawPane dp = new DrawPane(
                        disp.getWidth() / s,
                        disp.getHeight() / s);
                dp.setBackground(Color.BLACK);
//                disp.setContentPane(dp);
                disp.add(dp, BorderLayout.CENTER);
//                disp.add(jp, BorderLayout.CENTER);
                disp.setVisible(true);

                final Universe universe = new Universe(
                        //                        disp.getContentPane().getWidth() / s,
                        //                        disp.getContentPane().getHeight() / s,
                        //                        0);
                        //                20,9,0);
                        disp.getWidth() / s,
                        disp.getHeight() / s, 0);
                universe.createNewPopulation();
                dp.setPopulation(universe.population);

//                dp.setUniverse(universe);

                Thread thread = new Thread(new Runnable() {
                    @Override
                    public void run() {
//                        int i = 0;
                        while (true) {

//                            i++;
//                            System.out.println("" + i);
                            try {
                                Thread.sleep(10);
                            } catch (InterruptedException ex) {
//                                Logger.getLogger(Display.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            universe.createNextPopulation();
                            if (disp.getState() != Frame.ICONIFIED) {
                                dp.setPopulation(universe.population);
                                dp.repaint();
                            }
                            if (!universe.progressExists()) {
                                System.out.println("generation complete");
                                break;
                            }
                        }
                    }
                });
                thread.start();
            }
        });
    }
}
