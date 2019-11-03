package hw;//-------------------------------------------------
// Title: path finder
// Author: Oguzhan Ugur Sarisakaloglu
// ID: 39274105326
// Section: 1
// Assignment: 1
// Description: This class contains main method.
//-------------------------------------------------


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;

public class PathFinder {


    public static void main(String[] args) throws IOException {


        Scanner sc = new Scanner(System.in);  // Create a Scanner object

        System.out.print("Input file(input2.txt):");
        String input_file_path = sc.nextLine();

        ClassLoader classLoader = PathFinder.class.getClassLoader();

        File input_file = new File(classLoader.getResource(input_file_path).getFile());

        String fileName = classLoader.getResource(input_file_path).getFile();

        FileInputStream fin = new FileInputStream(fileName);

        /*
        following;
        hw.Board B = new hw.Board(fin);

        We generates a board using input file which scanned by FileInputStream.

        **********************************/

        Board B = new Board(fin);

        /*
        following;
        hw.Graph G = new hw.Graph(B, fin);

        We generates a hw.Graph using exsisted board.

        **********************************/

        Graph G = new Graph(B, fin);

        /*
        following; print method no informs us created game hw.Board from file input.

        **********************************/
        System.out.println("hw.Board created width=" + B.width + " height=" + B.height + ".");

        System.out.println("Location id of knight is " + B.knight + ".");

        System.out.println("Location id of gold is " + B.gold + "");

        /*
        following;
        hw.Paths bfs = new hw.Paths(B, G);

        If we use breadth-first search algorithm we can find shortest path.
        This algorithm uses hw.Board and hw.Graph as parameters.

        **********************************/

        Paths bfs = new Paths(B, G);


    }

}