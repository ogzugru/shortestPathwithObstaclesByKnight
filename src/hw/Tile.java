package hw;//-------------------------------------------------
// Title: hw.Tile
// Author: Oguzhan Ugur Sarisakaloglu
// ID: 39274105326
// Section: 1
// Assignment: 1
// Description: This class describes tiles
//-------------------------------------------------


public class Tile {

    public int COLUMN;
    public int ROW;
    public int id;
    public Boolean Gold;
    public Boolean Knight;
    public Boolean Reachable;

    public Tile(char Type, int Y, int X, int id) {

        this.id = id;

        this.COLUMN = X;
        this.ROW = Y;

        if (Type == 'G') {
            this.Gold = true;
        } else {
            this.Gold = false;
        }

        if (Type == 'K') {
            this.Knight = true;
        } else {
            this.Knight = false;
        }

        if (Type == 'T') {
            this.Reachable = false;
        } else {
            this.Reachable = true;
        }

    }

    //    @Override
//    public String toString() {
//        return "hw.Tile{" +
//                "X=" + X +
//                ", Y=" + Y +
//                ", Reachable=" + Reachable +
//                '}';
//    }


    public int getCOLUMN() {
        return COLUMN;
    }

    public void setCOLUMN(int x) {
        COLUMN = x;
    }

    public int getROW() {
        return ROW;
    }

    public void setROW(int y) {
        ROW = y;
    }

    public Boolean getGold() {
        return Gold;
    }

    public void setGold(Boolean gold) {
        Gold = gold;
    }

    public Boolean getReachable() {
        return Reachable;
    }

    public void setReachable(Boolean reachable) {
        Reachable = reachable;
    }

    public String toT() {
//        c3,4

        return "c" + (ROW + 1) + "," + (COLUMN + 1);

    }

    @Override
    public String toString() {
        return "{" + id +
                "C_" + COLUMN +
                ",R_" + ROW +
                "," + Reachable.toString().charAt(0) +
                '}';
    }
}
