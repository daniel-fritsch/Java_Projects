package ass2;
class MazeSolverQueue extends MazeSolver {
    private MyQueue<Square> queue;

    public MazeSolverQueue(Maze maze) {
        super(maze);
        this.makeEmpty();
        Square start = this.maze.getStart();
        start.mark();
        this.add(start);
    }


    void makeEmpty() {
        queue = new MyQueue<>();
    }


    boolean isEmpty() {
        return queue.isEmpty();
    }


    void add(Square sq) {
        queue.enqueue(sq);
    }


    Square next() {
        return queue.dequeue();
    }
}