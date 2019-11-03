package hw;//-------------------------------------------------
// Title: paths generator
// Author: Oguzhan Ugur Sarisakaloglu
// ID: 39274105326
// Section: 1
// Assignment: 1
// Description: This class manages paths
//-------------------------------------------------

import java.util.ArrayList;
import java.util.Stack;

public class Paths {
    private static final int INFINITY = Integer.MAX_VALUE;
    private boolean[] marked;  // marked[v] = is there an s-v path
    private int[] edgeTo;      // edgeTo[v] = previous edge on shortest s-v path
    private int[] distTo;      // distTo[v] = number of edges shortest s-v path

    public Paths(Board B, Graph G) {

        marked = new boolean[G.V()];

        distTo = new int[G.V()];

        edgeTo = new int[G.V()];


        goKnight(B, G);

    }

    /*following;

        This class moves knight*/
    private void goKnight(Board B, Graph G) {

        int start = B.knight;
        int end = B.gold;

        Queue<Integer> q = new Queue<Integer>();
        for (int v = 0; v < G.V(); v++)
            distTo[v] = INFINITY;

        int v = -1;
        distTo[start] = 0;
        marked[start] = true;
        q.enqueue(start);

        while (!q.isEmpty() && end != v) {
            v = q.dequeue();


            int tl_dest_R = (v / B.width);
            int tl_dest_C = (v % B.width);

            ArrayList<Integer> destinations = new ArrayList<Integer>();

            for (Move M : G.moves) {


                if (validateMove(M, v, G, B)) {

                    if (true) {

                        //System.out.println(thereispath(M, B.a.get(tl_dest_R).get(tl_dest_C), newDestination(M, v, G, B), G, B));

                        if (!marked[newDestination(M, v, G, B)]) {


                            q.enqueue(newDestination(M, v, G, B));
                            marked[newDestination(M, v, G, B)] = true;
                            edgeTo[newDestination(M, v, G, B)] = v;
                            distTo[newDestination(M, v, G, B)] = distTo[v] + 1;

                            //   System.out.println(newDestination(M, v, G, B) + " " + edgeTo[newDestination(M, v, G, B)]);

                        }


                    }

                    // System.out.println();

                }

            }


            //break;
        }

        System.out.println();

        Stack<Integer> reverse = new Stack<Integer>();


        int temp = end;
        reverse.push(temp);

        while (true) {


            temp = edgeTo[temp];
            reverse.push(temp);

            if (temp == start)
                break;
        }

        int last = 0;

        int temp_row = 0, temp_column = 0;

        String te = "";

        while (!reverse.isEmpty()) {

            if (last != 0) {
                te += " -> ";
            }


            last = reverse.pop();


            temp_row = last / B.width;
            temp_column = last % B.width;


            te += B.a.get(temp_row).get(temp_column).toT();


        }

        if (distTo[last] == INFINITY) {
            System.out.println("No path to the target.");
        } else {

            int a = 0, b = 0;

            a = B.gold / B.width;
            b = B.gold % B.width;
            int c = 0, d = 0;

            c = B.knight / B.width;
            d = B.knight % B.width;

            System.out.println(distTo[last] + " steps");
            System.out.println(B.a.get(c).get(d).toT() + " to " + B.a.get(a).get(b).toT() + ": " + te);

        }


    }

    /*following;

        Check the path between tiles*/
    private boolean thereispath(Move m, Tile tile, Integer integer, Graph g, Board B) {

        boolean result = true;
        boolean result1 = true;

        int start_dest_C = tile.COLUMN;
        int start_dest_R = tile.ROW;

        int move_column = m.getX();
        int move_row = m.getY();

        int TEMP = 0;
        int TEMP_ID = 0;
        int TEMP_ID_F = tile.id;
        int TEMP_ID_FIRST = tile.id;

        int final_dest_R = (integer / B.width);
        int final_dest_C = (integer % B.width);

        //System.out.println("* " + tile.toString() + " " + integer + " " + m.toString());

        // Y ROW - X COLUMN

        for (int i = 0; i != 2; i++) {

            TEMP_ID_FIRST = tile.id;

            // possibility 1
            if (i == 0) {


                // first move columns
                for (int c = 0; c != Math.abs(move_column) && result != false; c++) {

                    if (c == 0) {
                        TEMP_ID_FIRST = tile.id;

                    } else {
                        TEMP_ID_FIRST = B.a.get(start_dest_R).get(start_dest_C).id;

                    }

                    TEMP = move_column / Math.abs(move_column);

                    //System.out.println("1C:" + TEMP);

                    //System.out.println(" " + TEMP_ID_FIRST + " - > " + (start_dest_C + TEMP));

                    start_dest_C += TEMP;

                    TEMP_ID = B.a.get(start_dest_R).get(start_dest_C).id;

                    //System.out.println(TEMP_ID_FIRST + " = " + TEMP_ID);

                    if (g.adj.get(TEMP_ID_FIRST).contains(TEMP_ID)) {

                        // System.out.println("OK");

                    } else {

                        result = false;

                        // System.out.println("NOPE");


                    }
                }

                for (int r = 0; r != Math.abs(move_row) && result != false; r++) {

                    TEMP_ID_FIRST = B.a.get(start_dest_R).get(start_dest_C).id;

                    TEMP = move_row / Math.abs(move_row);

                    //System.out.println("1R:" + TEMP);

                    //System.out.println(start_dest_R + " - > " + (start_dest_R + TEMP));

                    start_dest_R += TEMP;

                    TEMP_ID = B.a.get(start_dest_R).get(start_dest_C).id;

                    // System.out.println(TEMP_ID_FIRST + " and " + TEMP_ID);

                    if (g.adj.get(TEMP_ID_FIRST).contains(TEMP_ID)) {

                        //System.out.println("OK");


                    } else {

                        result = false;

                        //System.out.println("NOPE");


                    }
                }

                if (result == true) {
                    return true;
                }


            }

            TEMP_ID = 0;

            if (i == 1) {

                result = true;

                start_dest_C = tile.COLUMN;
                start_dest_R = tile.ROW;
                // first move row
                for (int r = 0; r != Math.abs(move_row) && result1 != false; r++) {

                    if (r == 0) {
                        TEMP_ID_FIRST = tile.id;

                    } else {
                        TEMP_ID_FIRST = B.a.get(start_dest_R).get(start_dest_C).id;

                    }

                    TEMP_ID_FIRST = B.a.get(start_dest_R).get(start_dest_C).id;

                    TEMP = move_row / Math.abs(move_row);

                    //System.out.println("1R:" + TEMP);

                    //System.out.println(start_dest_R + " - > " + (start_dest_R + TEMP));

                    start_dest_R += TEMP;

                    TEMP_ID = B.a.get(start_dest_R).get(start_dest_C).id;


                    //System.out.println(TEMP_ID_FIRST + " = " + TEMP_ID);

                    if (g.adj.get(TEMP_ID_FIRST).contains(TEMP_ID)) {

                        //System.out.println("OK");

                    } else {

                        //System.out.println("NOPE");

                        result = false;

                    }


                }

                // first move columns
                for (int c = 0; c != Math.abs(move_column) && result1 != false; c++) {


                    TEMP_ID_FIRST = B.a.get(start_dest_R).get(start_dest_C).id;

                    TEMP = move_column / Math.abs(move_column);

                    //System.out.println("1C:" + TEMP);

                    //System.out.println(" " + TEMP_ID_FIRST + " - > " + (start_dest_C + TEMP));

                    start_dest_C += TEMP;

                    TEMP_ID = B.a.get(start_dest_R).get(start_dest_C).id;

                    //System.out.println(TEMP_ID_FIRST + " and " + TEMP_ID);

                    if (g.adj.get(TEMP_ID_FIRST).contains(TEMP_ID)) {

                        //System.out.println("OK");

                    } else {
                        result = false;

                        //System.out.println("NOPE");


                    }
                }

                if (result == false) {
                    return false;

                }


            }


        }


        return true;
    }

    /*following;

        Using move vector this method finds destinations.*/
    private Integer newDestination(Move m, int v, Graph g, Board B) {

        int final_dest_R = 0, final_dest_C = 0;

        int temp_id = 0;

        final_dest_R = (m.getY() + (v / B.width));
        final_dest_C = (m.getX() + (v % B.width));

        if (final_dest_R < B.height && final_dest_R >= 0
                && final_dest_C < B.width && final_dest_C >= 0) {


            temp_id = B.a.get(final_dest_R).get(final_dest_C).id;

        }

        return temp_id;
    }

    /*following;

        this method validate move
         For example we can not go out of board */
    private boolean validateMove(Move m, int v, Graph g, Board B) {

        int final_dest_R = 0, final_dest_C = 0;

        final_dest_R = (m.getY() + (v / B.width));
        final_dest_C = (m.getX() + (v % B.width));


        if (final_dest_R < B.height && final_dest_R >= 0
                && final_dest_C < B.width && final_dest_C >= 0) {

            if (!B.a.get(final_dest_R).get(final_dest_C).Reachable) {
                return false;
            }

            return true;
        }


        return false;
    }

    /*following;

            Check moves tile by tile */
    private boolean isPathH(Board B, Move M, Graph G, int row, int column, Tile tile) {

        Tile t = tile;

        boolean result = true;
        boolean tempa = true;

        int sourceid = 0;


        int c = M.getX(), r = M.getY();

        if (r <= 0 && c <= 0) {


        } else if (r <= 0 && c >= 0) {


        } else if (r >= 0 && c >= 0) {

            for (int y = 0; y != 2; y++) {

                sourceid = (row * B.width) + column;

                //  System.out.println("source id: " + sourceid);

                if (y == 0) {


                    //aab
                    for (int i = 0; i != r; i++) {

                        System.out.println(G.adj(sourceid).toString());

                        if (G.adj.get(sourceid).contains(sourceid + B.width)) {

                            // System.out.println("row artı");

                        } else {

                            result = false;

                        }

                        sourceid += B.width;

                    }

                    for (int i = 0; i != c; i++) {


                        if (G.adj.get(sourceid).contains(sourceid + 1)) {

                            //  System.out.println("okk");

                        } else {

                            //return false;

                        }

                        sourceid += 1;

                        System.out.println("b");

                    }
                } else {

                    sourceid = row * B.width + column;

                    for (int i = 0; i != c; i++) {
                        //   System.out.println("a");

                    }
                    for (int i = 0; i != r; i++) {
                        //  System.out.println("b");

                    }
                }


            }


        }


        return result;
    }


    private void validateVertex(int v) {

        int V = marked.length;

        if (v < 0 || v >= V)
            throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (V - 1));


    }


}
