package ass2;

import java.util.NoSuchElementException;


public class MiniList<E> {
	
    private class Node {
        E data;
        Node next;

        public Node(E data) {
            this.data = data;
            this.next = null;
        }
        
        public String toString(){
            return this.data.toString();
        }

    }
    

    private Node head;
    private Node tail;


    public MiniList() {
        this.head = null;
        this.tail = null;
    }

    public boolean isEmpty() {
        return head == null;
    }
    
    public void addFirst(E element) {
        Node newNode = new Node(element);

        if (this.isEmpty()) {
            this.head = newNode;
            this.tail = newNode; // Add this line
        } else {
            newNode.next = this.head;
            this.head = newNode;
        }
    }
    
    private void printList() {
        int index = 0;
        Node node = this.head;
        while (node != null) {
            System.out.println(index + ": " + node.data);
            node = node.next;
            index++;
        }
    }
    
    public E removeFirst() {
        if (this.isEmpty()) {
            throw new NoSuchElementException("Cannot remove from an empty list");
        }

        E removedData = this.head.data;
        this.head = this.head.next;

        if (this.head == null) {
            this.tail = null; // Add this line
        }

        return removedData;
    }
    
    public void addLast(E element) {
        if (this.isEmpty()) {
            this.addFirst(element);
            return;
        }

        this.tail.next = new Node(element);
        this.tail = this.tail.next;
    }
    
    public E getFirst() {
        if (this.head != null) {
            return this.head.data;
        } else {
            throw new NoSuchElementException("List is empty");
        }
    }
    
    public E removeLast() {
        if (this.isEmpty()) {
            throw new NoSuchElementException();
        }

        if (this.head.next == null) {
            E removedData = this.head.data;
            this.head = null;
            return removedData;
        }

        Node prevNode = null;
        Node currentNode = this.head;

        while (currentNode.next != null) {
            prevNode = currentNode;
            currentNode = currentNode.next;
        }

        E removedData = currentNode.data;

        if (prevNode != null) {
            prevNode.next = null;
        } else {
            this.head = null;
        }

        return removedData;
    }


    
    public static void main(String[] args) {
    	MiniList<String> list = new MiniList<String>();

    	list.addLast("one");
    	list.addLast("two");
    	list.addLast("three");
    	while (!list.isEmpty()) {
    	    System.out.println(list.removeFirst());
    	}


    }





    

}
