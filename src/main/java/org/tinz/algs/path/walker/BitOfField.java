/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.tinz.algs.path.walker;

/**
 *
 * @author tinz
 */
public class BitOfField implements Comparable<BitOfField> {

    int id;
    int x;
    int y;
    TypeOfBit type;
    double weight = 1;//
    BitOfField[] nearBits;
    double minDistance = Double.POSITIVE_INFINITY;
    BitOfField previous;

//    public BitOfField(int id) {
//        this.id = id;
//    }

    public BitOfField(int id, int x, int y, TypeOfBit type) {
        this.id = id;
        this.x = x;
        this.y = y;
        this.type = type;
//        this.weight = 1+type;
        if (type != TypeOfBit.FREE) {
            weight = Double.MAX_VALUE;
        }
    }

    @Override
    public String toString() {
        return "" + id;
    }

    @Override
    public int compareTo(BitOfField other) {
        return Double.compare(minDistance, other.minDistance);
    }
}
