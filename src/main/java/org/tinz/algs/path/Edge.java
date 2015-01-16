/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.tinz.algs.path;

public class Edge {

    public final Vertex target;
    public final double weight;

    public Edge(Vertex argTarget, double argWeight) {
        target = argTarget;
        weight = argWeight;
    }
}
