import java.util.*;

public class Driver {
    
    // Fields
    final private int n = 15;
    final private char[][] world = new char[n][n];
    int[] startPos = new int[2];
    int[] finishPos = new int[2];

    PriorityQueue<Tile> openList = new PriorityQueue<>();
    ArrayList<Tile> closedList = new ArrayList<>();

    // Create a 15x15 world
    public void genWorld() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                world[i][j] = '.';
            }
        }
    }

    // Generate random blocks on 10 percent of the board
    public void randomizeWorld() {
        Random r1 = new Random();
        Random r2 = new Random();
        //int cap = (n*n) / 10; // unwalkable tiles on 10 percent of board
        int cap = 50;
        for (int i = 0; i < cap; i++) {
            int s1 = r1.nextInt(n-1);
            int s2 = r2.nextInt(n-1);
            world[s1][s2] = '#';
        }
    }

    // Print a greeting to the user
    public void greetUser() {
        System.out.println();
        System.out.println("----------------------------------------------");
        System.out.println("A* Program in Java");
    }

    // Build the world with the given start and finish coordinates
    public void buildWorld() {
        Scanner s1 = new Scanner(System.in);
        System.out.println();
        System.out.println("VALID INPUTS ARE INTEGERS BETWEEN 0 and " + (n-1));
        System.out.println();
        System.out.println("Enter row of starting position: ");
        startPos[0] = s1.nextInt();
        System.out.println("Enter col of starting position: ");
        startPos[1] = s1.nextInt();
        System.out.println("Enter row of finish position: ");
        finishPos[0] = s1.nextInt();
        System.out.println("Enter col of finish position: ");
        finishPos[1] = s1.nextInt();
        s1.close();
        world[startPos[0]][startPos[1]] = 'S';
        world[finishPos[0]][finishPos[1]] = 'F';
    }

    // Helper method
    public void initialize() {
        genWorld();
        randomizeWorld();
        greetUser();
        buildWorld();
    }

    // Print the world to the screen
    public void displayWorld() {
        System.out.println();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(world[i][j] + "  ");
            }
            System.out.println();
        }
        System.out.println();
    }

    // A* algorithm
    public void aStar() {
        // Find the start tile and calculate its values
        Tile startTile = new Tile(startPos[0], startPos[1], 0);
        startTile.setG(0);
        startTile.setH(calculateH(startTile));
        startTile.setF();
        
        // Add the start tile to the openList
        openList.add(startTile);

        boolean goalFound = false;
        
        // Run until a) you find the goal or b) nothing left in openList
        while (!goalFound || !openList.isEmpty()) {
            
            // Retrieve and Remove tile with lowest f score from openlist
            Tile currTile = openList.poll();
            
            // Add currTile to closedList
            closedList.add(currTile);

            // test
            //System.out.println("currTile details: " + currTile.printTile());
            
            // then check to see if current is goal node
            if (currTile.getRow() == finishPos[0] && currTile.getCol() == finishPos[1]) {
                
                goalFound = true;
                System.out.println("Path has been found");
                printResult(currTile);
                return;

            }

            // Generate neighbors and add to openList
            findNeighbors(currTile);
        }
    }

    
    // Find neighbors
    public void findNeighbors(Tile cTile) {
        // Find neighbors of currTile
        int currR = cTile.getRow();
        int currC = cTile.getCol();
        // Establish directions
        int north = currR - 1;
        int south = currR + 1;
        int east = currC + 1;
        int west = currC - 1;
        // Look north
        if (north >= 0 && north <= n-1) {
            if (world[north][currC] == '.' || world[north][currC] == 'F') {
                Tile t = new Tile(north, currC, 0);
                t.setH(calculateH(t));
                t.setG(10);
                t.setF();
                t.setParent(cTile);
                //nList.add(t);
                openList.add(t);
            }
        }
        // Look south
        if (south >= 0 && south <= n-1) {
            if (world[south][currC] == '.' || world[south][currC] == 'F') {
                Tile t = new Tile(south, currC, 0);
                t.setH(calculateH(t));
                t.setG(10);
                t.setF();
                t.setParent(cTile);
                //nList.add(t);
                openList.add(t);
            }
        }
        // Look east
        if (east >= 0 && east <= n-1) {
            if (world[currR][east] == '.' || world[currR][east] == 'F') {
                Tile t = new Tile(currR, east, 0);
                t.setH(calculateH(t));
                t.setG(10);
                t.setF();
                t.setParent(cTile);
                //nList.add(t);
                openList.add(t);
            }
        }
        // Look west
        if (west >= 0 && west <= n-1) {
            if (world[currR][west] == '.' || world[currR][west] == 'F') {
                Tile t = new Tile(currR, west, 0);
                t.setH(calculateH(t));
                t.setG(10);
                t.setF();
                t.setParent(cTile);
                //nList.add(t);
                openList.add(t);
            }
        }
        // Look northwest
        if ((north >= 0 && north <= n-1) && (west >= 0 && west <= n-1)) {
            if (world[north][west] == '.' || world[north][west] == 'F') {
                Tile t = new Tile(north, west, 0);
                t.setH(calculateH(t));
                t.setG(14);
                t.setF();
                t.setParent(cTile);
                //nList.add(t);
                openList.add(t);
            }
        }
        // Look northeast
        if ((north >= 0 && north <= n-1) && (east >= 0 && east <= n-1)) {
            if (world[north][east] == '.' || world[north][east] == 'F') {
                Tile t = new Tile(north, east, 0);
                t.setH(calculateH(t));
                t.setG(14);
                t.setF();
                t.setParent(cTile);
                //nList.add(t);
                openList.add(t);
            }
        }
        // Look southwest
        if ((south >= 0 && south <= n-1) && (west >= 0 && west <= n-1)) {
            if (world[south][west] == '.' || world[south][west] == 'F') {
                Tile t = new Tile(south, west, 0);
                t.setH(calculateH(t));
                t.setG(14);
                t.setF();
                t.setParent(cTile);
                //nList.add(t);
                openList.add(t);
            }
        }
        // Look southeast
        if ((south >= 0 && south <= n-1) && (east >= 0 && east <= n-1)) {
            if (world[south][east] == '.' || world[south][east] == 'F') {
                Tile t = new Tile(south, east, 0);
                t.setH(calculateH(t));
                t.setG(14);
                t.setF();
                t.setParent(cTile);
                //nList.add(t);
                openList.add(t);
            }
        }
    }

    // Calculate distance from tile to finish tile using the manhattan method
    public int calculateH(Tile tile) {
        int d1 = Math.abs(tile.getRow() - finishPos[0]);
        int d2 = Math.abs(tile.getCol() - finishPos[1]);
        int d = (d1 + d2) * 10;
        return d;
    }

    // Print results to screen
    public void printResult(Tile tile) {
        
        System.out.println();
        System.out.println("----------------------------------------------");
        System.out.println("Printing Shortest Path:");
        System.out.println();

        for (Tile t : closedList) {
            int cRow = t.getRow();
            int cCol = t.getCol();
            world[cRow][cCol] = 'O';
        }

        world[startPos[0]][startPos[1]] = 'S';
        world[finishPos[0]][finishPos[1]] = 'F';

        System.out.println();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(world[i][j] + "  ");
            }
            System.out.println();
        }
        System.out.println();

    }

//-----------------------------------------------------------------
    public static void main(String args[]) {
        Driver d = new Driver();
        d.initialize();
        d.displayWorld();
        d.aStar();
    }
}
