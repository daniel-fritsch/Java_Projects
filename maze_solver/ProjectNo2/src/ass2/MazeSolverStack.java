package ass2;
public class MazeSolverStack extends MazeSolver {
    private MyStack<Square> worklist;

    public MazeSolverStack(Maze maze) {
        super(maze);
        worklist = new MyStack<>();
        worklist.push(maze.getStart());
    }

    void makeEmpty() {
        worklist = new MyStack<>();
    }


    boolean isEmpty() {
        return worklist.isEmpty();
    }


    void add(Square sq) {
        worklist.push(sq);
    }


    Square next() {
        return worklist.pop();
    }
}

