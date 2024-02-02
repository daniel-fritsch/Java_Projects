package ass2;

import java.util.NoSuchElementException;

class MyQueue<T> implements QueueInterface<T> {
    private MiniList<T> list = new MiniList<T>();

    public void enqueue(T element) {
        list.addLast(element);
    }

    public T dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException("Queue empty");
        }
        return list.removeFirst();
    }

    public boolean isEmpty() {
        return list.isEmpty();
    }

    public T front() {
        if (isEmpty()) {
            throw new NoSuchElementException("Queue empty");
        }
        return list.getFirst();
    }
}



