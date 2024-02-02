package ass2;

import java.util.*;

abstract class MazeSolver {
    public static final int STACK = 1, QUEUE = 2;
    protected Maze maze;
    protected boolean finished = false;
    protected boolean pathFound = false;
    protected  List<Square> path;

    MazeSolver(Maze maze) {
        this.maze = maze;          
    }

    abstract void makeEmpty();

    abstract boolean isEmpty();

    abstract void add(Square sq);

    abstract Square next();

    public boolean isFinished() {
        return finished;
    }

    public boolean pathFound() {
        return pathFound;
    }    

    /*
     * Once the finish square is reached,
     * this method makes a list of the squares on a path 
     * from the start square to the exit square
     */
    private void buildPath(Square sq) {
        this.path = new LinkedList<Square>();

        LinkedList<Square> path = new LinkedList<>();
        while (sq != null) {
            path.addFirst(sq);
            sq.setOnPath(); // Set onPath to true for each square in the path
            sq = sq.getPrevious();
        }
        this.path = path;
    }
    
    public List<Square> getPath() {        
        return this.path; 
    }
    
    /* performs one step of the maze solver algorithm */
    public void step() {
        if (this.isEmpty()) {
            this.finished = true;
            return;
        }
        
        Square nextSquare = this.next();
        // TODO: one step of the maze exploration algorithm
        
        if (nextSquare.getType() == Square.EXIT) {
            this.finished = true;
            this.pathFound = true;
            buildPath(nextSquare);
        } else {
            List<Square> neighbors = maze.getNeighbors(nextSquare);
            for (Square neighbor : neighbors) {
                if (!neighbor.isMarked() && neighbor.getType() != Square.WALL) {
                    add(neighbor);
                    neighbor.setPrevious(nextSquare);
                }
            }
            nextSquare.mark();
        }
    }
}
