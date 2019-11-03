package hw;//-------------------------------------------------
// Title: hw.Graph generator
// Author: Oguzhan Ugur Sarisakaloglu
// ID: 39274105326
// Section: 1
// Assignment: 1
// Description: This class generates graph by using board
//-------------------------------------------------

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Stack;

public class Graph {
    private static final String NEWLINE = System.getProperty("line.separator");


    /*following;

        ArrayList of move vectors. */
    public ArrayList<Move> moves = new ArrayList<Move>() {
        {
            add(new Move(1, -2));
            add(new Move(2, -1));
            add(new Move(2, 1));
            add(new Move(1, 2));
            add(new Move(-1, 2));
            add(new Move(-2, 1));
            add(new Move(-2, -1));
            add(new Move(-1, -2));
        }
    };

    private int V;
    private int E;
    public ArrayList<ArrayList<Integer>> adj;

    public Graph(int V) {
        if (V < 0) throw new IllegalArgumentException("Number of vertices must be nonnegative");
        this.V = V;
        this.E = 0;
        adj = (ArrayList<ArrayList<Integer>>) new ArrayList<ArrayList<Integer>>();
        for (int v = 0; v < V; v++) {


            //adj.get(v) = new  ArrayList<Integer>();
        }
    }

    /*following;

        Constructor of a hw.Graph*/
    public Graph(Board b, FileInputStream fin) {

        this.V = b.V;

        /* ADJ BAG */
        adj = (ArrayList<ArrayList<Integer>>) new ArrayList<ArrayList<Integer>>();

        for (int v = 0; v < V; v++) {
            adj.add(new ArrayList<Integer>());
        }
        /* ADJ BAG */

        int E = 0;

        for (int i = 0; i != b.a.size(); i++) {

            for (int k = 0; k != b.a.get(i).size(); k++) {

                //System.out.println(b.a.get(i).get(k).toString());

                if (b.a.get(i).get(k).getReachable()) {

                    if (k + 1 < b.width) {

                        if (b.a.get(i).get(k + 1).getReachable()) {
                            addEdge(b.a.get(i).get(k).id, b.a.get(i).get(k + 1).id);
                        }


                    }

                    if (i + 1 < b.height) {

                        if (b.a.get(i + 1).get(k).getReachable()) {
                            addEdge(b.a.get(i).get(k).id, b.a.get(i + 1).get(k).id);

                        }

                    }

                }


            }

        }


    }


    /**
     * Initializes a new graph that is a deep copy of {@code G}.
     *
     * @param G the graph to copy
     */
    public Graph(Graph G) {
        this(G.V());
        this.E = G.E();
        for (int v = 0; v < G.V(); v++) {
            // reverse so that adjacency list is in same order as original
            Stack<Integer> reverse = new Stack<Integer>();
            for (int w : G.adj.get(v)) {
                reverse.push(w);
            }
            for (int w : reverse) {
                adj.get(v).add(w);
            }
        }
    }

    /**
     * Returns the number of vertices in this graph.
     *
     * @return the number of vertices in this graph
     */
    public int V() {
        return V;
    }

    /**
     * Returns the number of edges in this graph.
     *
     * @return the number of edges in this graph
     */
    public int E() {
        return E;
    }

    // throw an IllegalArgumentException unless {@code 0 <= v < V}
    private boolean validateVertex(int v) {
        if (v < 0 || v >= V)
            return false;
        else
            return true;
    }

    // throw an IllegalArgumentException unless {@code 0 <= v < V}
    private boolean validateTile(Board b, Tile t) {
        if (b.height > t.getROW() && t.getROW() >= 0
                && b.width > t.getCOLUMN() && t.getCOLUMN() >= 0
                ) {
            return true;
        } else return false;


    }

    /**
     * Adds the undirected edge v-w to this graph.
     *
     * @param v one vertex in the edge
     * @param w the other vertex in the edge
     * @throws IllegalArgumentException unless both {@code 0 <= v < V} and {@code 0 <= w < V}
     */
    public void addEdge(int v, int w) {

        if (validateVertex(v) && validateVertex(w)) {


            E++;
            adj.get(v).add(w);
            adj.get(w).add(v);
        }
    }


    /**
     * Returns the vertices adjacent to vertex {@code v}.
     *
     * @param v the vertex
     * @return the vertices adjacent to vertex {@code v}, as an iterable
     * @throws IllegalArgumentException unless {@code 0 <= v < V}
     */
    public Iterable<Integer> adj(int v) {
        validateVertex(v);
        return adj.get(v);
    }

    /**
     * Returns the degree of vertex {@code v}.
     *
     * @param v the vertex
     * @return the degree of vertex {@code v}
     * @throws IllegalArgumentException unless {@code 0 <= v < V}
     */
    public int degree(int v) {
        validateVertex(v);
        return adj.get(v).size();
    }


    /**
     * Returns a string representation of this graph.
     *
     * @return the number of vertices <em>V</em>, followed by the number of edges <em>E</em>,
     * followed by the <em>V</em> adjacency lists
     */
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(V + " vertices, " + E + " edges " + NEWLINE);
        for (int v = 0; v < V; v++) {
            s.append(v + ": ");
            for (int w : adj.get(v)) {
                s.append(w + " ");
            }
            s.append(NEWLINE);
        }
        return s.toString();
    }


    /**
     * Unit tests the {@code hw.Graph} data type.
     *
     * @param args the command-line arguments
     */
    public static void main(String[] args) {
//        In in = new In(args[0]);
//        hw.Graph G = new hw.Graph(in);
//        StdOut.println(G);
    }

}