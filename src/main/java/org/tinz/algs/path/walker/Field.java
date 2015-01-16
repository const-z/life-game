package org.tinz.algs.path.walker;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Path2D;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;
import javax.imageio.ImageIO;
import org.tinz.algs.utils.Display;

/**
 *
 * @author tinz
 */
public class Field {

    static int BACKGROUND_COLOR = -1;
    static int TARGET_COLOR = -1237980;
    int width;
    int height;
    private BufferedImage image;
    public BitOfField[][] bits;
    private Walker walker;
//    private List<BitOfField> targets;
    public BitOfField start;

    private void computePaths(BitOfField source) {
        source.minDistance = 0.;
        PriorityQueue<BitOfField> vertexQueue = new PriorityQueue<BitOfField>();
        vertexQueue.add(source);
        while (!vertexQueue.isEmpty()) {
            BitOfField u = vertexQueue.poll();
            // Visit each edge exiting u
            for (BitOfField e : u.nearBits) {
//                if (e.type!=0){
//                    continue;
//                }
                BitOfField v = e;
                double weight = e.weight;
                double distanceThroughU = u.minDistance + weight;
                if (distanceThroughU < v.minDistance) {
                    vertexQueue.remove(v);
                    v.minDistance = distanceThroughU;
                    v.previous = u;
                    vertexQueue.add(v);
                }
            }
        }
    }

    private List<BitOfField> getShortestPathTo(BitOfField target) {
        List<BitOfField> path = new ArrayList<BitOfField>();
        for (BitOfField vertex = target; vertex != null; vertex = vertex.previous) {
            path.add(vertex);
        }
        Collections.reverse(path);
        return path;
    }

    public Field(BufferedImage image, Walker walker) {
        this.width = image.getWidth();
        this.height = image.getHeight();
        this.walker = walker;
        this.image = image;
    }

    public void createBits() {
        bits = new BitOfField[(width / walker.width)][(height / walker.height)];
        int bitx = 0;
        int bity = 0;
        int bitid = 0;
        Graphics g = image.getGraphics();
        g.setColor(Color.cyan);
        for (int x = 0; x < (width / walker.width) * walker.width; x += walker.width) {
            for (int y = 0; y < (height / walker.height) * walker.height; y += walker.height) {
                TypeOfBit type = typeOfBit(x, y);
                BitOfField bit = new BitOfField(bitid, x, y, type);
                bits[bitx][bity] = bit;
                bity++;
                bitid++;
                g.drawRect(x+1, y+1, walker.width-2, walker.height-2);
            }
            bity = 0;
            bitx++;
        }

        for (int i = 0; i < bits.length; i++) {
            for (int j = 0; j < bits[0].length; j++) {
                ArrayList<BitOfField> near = new ArrayList<>();
                if (i + 1 < bits.length) {
                    near.add(bits[i + 1][j]);
                }
                if (i - 1 >= 0) {
                    near.add(bits[i - 1][j]);
                }
                if (j - 1 >= 0) {
                    near.add(bits[i][j - 1]);
                }
                if (j + 1 < bits[0].length) {
                    near.add(bits[i][j + 1]);
                }
                bits[i][j].nearBits = new BitOfField[near.size()];
                int l = 0;
                for (BitOfField b : near) {
                    bits[i][j].nearBits[l] = b;
                    l++;
                }
            }
        }
    }

    public List<BitOfField> getPath() {
        computePaths(start);
        BitOfField target = null;
        double mindist = Double.MAX_VALUE;
        for (int i = 0; i < bits.length; i++) {
            for (int j = 0; j < bits[0].length; j++) {
                if (bits[i][j].type == TypeOfBit.TARGET) {
                    double d = Math.sqrt(Math.pow(bits[i][j].x - start.x, 2) + Math.pow(bits[i][j].y - start.y, 2));
                    if (d < mindist) {
                        target = bits[i][j];
                        mindist = d;
                    }
                }
            }
        }
///
///     g.fillRect(start.x, start.y, walker.width, walker.height);
        return getShortestPathTo(target);
    }

    private TypeOfBit typeOfBit(int bitx, int bity) {
        TypeOfBit type = TypeOfBit.FREE;
        for (int x = bitx; x < bitx + walker.width; x++) {
            for (int y = bity; y < bity + walker.height; y++) {
                try {
                    int c = image.getRGB(x, y);
                    if (c == TARGET_COLOR) {
                        type = TypeOfBit.TARGET;
                        break;
                    } else if (c != BACKGROUND_COLOR) {
                        type = TypeOfBit.BUSY;
                        break;
                    }
                } catch (Exception e) {
                    continue;
                }
            }
            if (type != TypeOfBit.FREE) {
                break;
            }
        }
        return type;
    }

    public static void main(String... args) throws Exception {
        BufferedImage bi = ImageIO.read(new File("field.bmp"));
        Walker w = new Walker();
        w.width = 10;
        w.height = 10;
        Field f = new Field(bi, w);
        f.createBits();
        f.start = f.bits[0][f.bits[0].length-2];
        Graphics2D g = (Graphics2D) bi.getGraphics();
        g.setColor(Color.gray);
        List<BitOfField> path = f.getPath();
        for (BitOfField bit : path){
//            System.out.print(bit.id+" - ");
            g.fillOval(bit.x+3, bit.y+3, w.width-6, w.height-6);
        }
        Point2D[] points = new Point2D[path.size()];
        int i = 0;
        for (BitOfField v : path) {
            points[i] = new Point2D.Double(v.x, v.y);
//            g.fillRect(v.x, v.y, v.x+20, v.y+20);
            i++;
        } 
        //
        Path2D lines2d = new Path2D.Double();
        lines2d.moveTo(points[0].getX(), points[0].getY());
        for (i = 0; i < points.length; i++) {
            Point2D p2d = points[i];
            lines2d.lineTo(p2d.getX(), p2d.getY());
        }
        g.draw(lines2d);
        //
        g.setColor(Color.red);
        
        java.awt.geom.Path2D path2d = new Path2D.Double();
        path2d.moveTo(points[0].getX(), points[0].getY());
        for (i = 0; i < points.length - 2; i = i + 2) {
            double midx = points[i + 2].getX();
            double midy = points[i + 2].getY();
            if (i < points.length - 3) {
                midx = points[i + 1].getX() + ((points[i + 2].getX() - points[i + 1].getX()) / 2);
                midy = points[i + 1].getY() + ((points[i + 2].getY() - points[i + 1].getY()) / 2);
            }
            path2d.curveTo(
                    points[i].getX(), points[i].getY(),
                    points[i + 1].getX(), points[i + 1].getY(),
                    midx, midy);
            path2d.moveTo(midx, midy);
        }
        g.draw(path2d);

        Display disp = new Display();
        disp.setSize(800, 600);
        DrawPane dp = new DrawPane();
        dp.setBi(bi);
        disp.setContentPane(dp);
        disp.setVisible(true);
    }
}
