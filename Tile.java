package Pathfinding;

// Node class
public class Tile implements Comparable<Tile> {
    
    // Fields
    private int row, col, type;
    private int fCost, gCost, hCost;
    private Tile parent;

    // Constructor
    public Tile() {}
    public Tile(int r, int c, int t) {
        this.row = r;
        this.col = c;
        this.type = t;
        // 0 = walkable
        // 1 = unwalkable
        this.parent = null;
    }
    public Tile(int r, int c, int t, int g, int h) { // Overloaded Constructor
        this.row = r;
        this.col = c;
        this.type = t;
        // 0 = walkable
        // 1 = unwalkable
        this.gCost = g;
        this.hCost = h;
        this.fCost = g + h;
        this.parent = null;
    }

    // Setters
    public void setF() {
        fCost = gCost + hCost;
    }
    public void setG(int g) {
        gCost = g;
    }
    public void setH(int h) {
        hCost = h;
    }
    public void setParent(Tile tile) {
        parent = tile;
    }

    // Getters
    public int getF() {
        return fCost;
    }
    public int getG() {
        return gCost;
    }
    public int getH() {
        return hCost;
    }
    public int getRow() {
        return row;
    }
    public int getCol() {
        return col;
    }
    public Tile getParent() {
        return parent;
    }

    // Methods
    public boolean equals(Object in) {
        Tile tile = (Tile) in;
        return (row == tile.getRow() && col == tile.getCol());
    }
    public String toString() {
        return ("Tile: " + row + "_" + col);
    }
    public String printTile() {
        return ("Tile: " + row + "_" + col + " " + "g: " + gCost + " " + "h: " + hCost + " " + "f: " + fCost);
    }

    @Override
    public int compareTo(Tile t) {
        if (this.fCost == t.getF()) {
            return 0;
        } else if (this.fCost > t.getF()) {
            return 1;
        } else {
            return -1;
        }
    }

}
