package ass2;
import java.util.NoSuchElementException;

class MyStack<T> implements StackInterface<T> {
    private MiniList<T> list = new MiniList<T>();

    public void push(T element) {
        list.addFirst(element);
    }

    public T pop() {
        return list.removeFirst();
    }

    public boolean isEmpty() {
        return list.isEmpty();
    }

    public T peek() {
        if (isEmpty()) {
            throw new NoSuchElementException("Stack empty");
        }
        return list.getFirst();
    }

}
