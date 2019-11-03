package hw;//-------------------------------------------------
// Title: hw.Move
// Author: Oguzhan Ugur Sarisakaloglu
// ID: 39274105326
// Section: 1
// Assignment: 1
// Description: This class describes move vectors of knight.
//-------------------------------------------------


public class Move {
    int x;
    int y;

    public Move(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    @Override
    public String toString() {
        return "hw.Move{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
