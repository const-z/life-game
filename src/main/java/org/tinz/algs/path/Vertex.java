/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.tinz.algs.path;

public class Vertex implements Comparable<Vertex> {

    public int id;
    public Edge[] adjacencies;
    public double minDistance = Double.POSITIVE_INFINITY;
    public Vertex previous;

    public Vertex(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return ""+id;
    }

    @Override
    public int compareTo(Vertex other) {
        return Double.compare(minDistance, other.minDistance);
    }
}