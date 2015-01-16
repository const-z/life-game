/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.tinz.algs.path.walker;

import java.util.List;

/**
 *
 * @author tinz
 */
public class BizierCurves {

    /**
     * Computes factorial
     */
    public static double fact(int k) {
        if (k == 0 || k == 1) {
            return 1;
        } else {
            return k * fact(k - 1);
        }
    }

    /**
     * Computes Bernstain
     *
     * @param {Integer} i - the i-th index
     * @param {Integer} n - the total number of points
     * @param {Number} t - the value of parameter t , between 0 and 1
     *
     */
    public static double B(int i, int n, double t) {
        //if(n < i) throw "Wrong";
        return fact(n) / (fact(i) * fact(n - i)) * Math.pow(t, i) * Math.pow(1 - t, n - i);
    }

    /**
     * Computes a point's coordinates for a value of t
     *
     * @param {Number} t - a value between o and 1
     * @param {Array} points - an {Array} of [x,y] coodinates. The initial
     * points
     *
     */
    public static int[] P(double t, List<BitOfField> points) {
        int[] r = {0, 0};
        int n = points.size() - 1;
//        for (int i = 0; i <= n; i++) {
        int i = 0;
        for (BitOfField bit : points) {
            r[0] += bit.x * B(i, n, t);
            //points[i][0] * B(i, n, t);
            r[1] += bit.y * B(i, n, t);
            //points[i][1] * B(i, n, t);
            i++;
        }
        return r;
    }
//    public static int[] P(double t, Integer[][] points) {
//        int[] r = {0, 0};
//        int n = points.length - 1;
//        for (int i = 0; i <= n; i++) {
//            r[0] += points[i][0] * B(i, n, t);
//            r[1] += points[i][1] * B(i, n, t);
//        }
//        return r;
//    }
}
