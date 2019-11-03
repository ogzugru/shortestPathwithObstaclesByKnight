package hw;//-------------------------------------------------
// Title: hw.Board
// Author: Oguzhan Ugur Sarisakaloglu
// ID: 39274105326
// Section: 1
// Assignment: 1
// Description: This class generates a board for playing chess
//-------------------------------------------------


import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Scanner;

public class Board {

    public int V;
    public int width;
    public int height;
    public int knight;
    public int gold;

    ArrayList<ArrayList<Tile>> a;

        /*following;

        Constructor of a hw.Board*/

    public Board(FileInputStream fin) {

        Scanner scanner = new Scanner(fin);


        this.V = 0;

        int temp_y = scanner.nextInt();
        int temp_x = scanner.nextInt();

        this.height = temp_y;
        this.width = temp_x;


        String temp_String_1 = "";

        ArrayList<ArrayList<Tile>> rows = new ArrayList<ArrayList<Tile>>();

        for (int q = 0; q != temp_y; q++) {

            ArrayList<Tile> columns = new ArrayList<Tile>();

            temp_String_1 = scanner.next();
            for (int w = 0; w != temp_x; w++) {


                columns.add(new Tile(temp_String_1.charAt(w), q, w, V));

                if (columns.get(w).Knight) {
                    this.knight = columns.get(w).id;
                }
                if (columns.get(w).Gold) {
                    this.gold = columns.get(w).id;
                }


                V++;


            }

            rows.add(columns);

        }

        this.a = rows;

        // System.out.println("v: " + this.V);

    }


    @Override
    public String toString() {

        String tempString = "";

        for (ArrayList<Tile> b : this.a) {

            for (Tile tile : b) {

                tempString += tile.toString();

            }
            tempString += "\n";
        }

        return tempString;
    }
}
