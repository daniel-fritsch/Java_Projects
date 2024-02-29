package ass1;
import java.util.Random;
//import java.util.ArrayList;  #not sure if this is needed!!!!!!

public class SQueue<T> implements QueueInterface<T>, Shufflable {
	private T[] theArray;  // internal representation of a queue
	
	//this is an index in the array where to read the data 
	//at the front of the queue
	private int read; 
	
	//this is an index in the array where to write the next element 
	//at the end of the queue
	private int write;
	
	//variable to store the number of slots in theArray
	//just for consistency, technically not required, 
	//because you can get the capacity of theArray from theArray.length
	private int capacity;
	
	//this stores the size of the queue: 
	// the total number of actual elements in the queue
	private int size;
	
	private boolean isEmpty;
	
	//initialization of the internal array of predefined capacity
	@SuppressWarnings("unchecked") //not sure if this is allowed just saying
	public SQueue (int capacity)	{		
		// your code here
		this.capacity = capacity + 1;
		T [] temp = (T []) new Object[this.capacity];
		theArray = temp;
		this.read = 0;
		this.write = -1;
		this.size = 0;
		this.isEmpty = true;
	}	
	
	//size meth
	public int getSize() {
		return this.size;
	}
	
	//add element to the end of the queue
	//wrap around the array as long as you did not reach the front 
	// of the queue
	// if no element can be added - throw exception
	public void enqueue(T newEntry) throws FullQueueException {
		if (size == capacity-1) {
			throw new FullQueueException();
		}

		
		if (isEmpty) {
			write = (write + 1) % capacity;
			theArray[write] = newEntry;
			isEmpty = false;
		} else {
			write = (write + 1) % capacity;
			theArray[write] = newEntry;
		}
		
		size ++;
	}
	  
	//remove and return an element from the front of the queue
	//because the array is circular - this object is at position read
	//if queue is empty - throw exception
	//if you remove element at position i of the array, 
	// please set theArray[read] = null; 
	// though technically not needed,
	//this will help you to see empty slots in theArray
	public T dequeue() throws EmptyQueueException{
		if (size == 0) {
			throw new EmptyQueueException();
		}
		
		T removedElement = theArray[read];
		theArray[read] = null;
		read = (read + 1) % capacity;
		size --;
		
		if (size == 0) {
			isEmpty = true;
		}
		return removedElement;
		//replace the line above with your code
	}
	  
	//Returns the entry at the front of this queue.
	//throw  EmptyQueueException if the queue is empty. 
	public T getFront() {
		if (isEmpty()) {
			throw new EmptyQueueException();
		}
		return theArray[write];
	}
	  
	//Detect whether this queue is empty.
	public boolean isEmpty() {	
		return size == 0;
		//replace the line above with your code
	}
	
	//Detect whether this queue is full.
	//this should prevent read and write indexes to become equal
	
	public boolean isFull() {
		return size == capacity;
		//replace the line above with your code
	}
	  
	//Removes all entries from this queue. 
	//think: can it potentially be done in one operation?
	
	@SuppressWarnings("unchecked")
	public void clear() {
		theArray = (T[]) new Object[capacity];
		read = 0;
		write = -1;
		size = 0;
		
	}
	
	//implement the random reordering of the elements in theARray
	//try to come up with an efficient algorithm by yourself.
	//if not - check out Fisher shuffle algorithm, 
	//also known as the Knuth shuffle algorithm
	//be careful - you only need to shuffle elements 
	//between read and write index - not including the unoccupied slots of the array.
	public void shuffle() {
	    Random rand = new Random();

	    for (int i = write - 1; i > read; i--) {
	        int random = rand.nextInt(i - read + 1) + read;

	        T swap = theArray[i];
	        theArray[i] = theArray[random];
	        theArray[random] = swap;
	    }
	}
	
	public void shuffle(long seed) {
		Random rand = new Random(seed);
		
	for (int i = 0, pos = read; i < size; pos++, i++) {
			int currentIndex = (pos) % capacity;
			int r = rand.nextInt(size - i);
		       int randomIndex = (read + r) % capacity;	        
		        	
	T temp = theArray[currentIndex];
		       theArray[currentIndex] = theArray[randomIndex];
		       theArray[randomIndex] = temp;
		}
	}


	
	public int countNonNullElements() {
	    int count = 0;
	    for (T element : theArray) {
	        if (element != null) {
	            count++;
	        }
	    }
	    return count;
	}
	
	public void addAll(SQueue<T> otherQueue) throws FullQueueException {
	    while (!otherQueue.isEmpty()) {
	        enqueue(otherQueue.dequeue());
	    }
	}

	
	//this is used for debugging and testing
	//please do not change!
	public String toString() {
		StringBuilder result = new StringBuilder("SQueue: the array [" + theArray[0] );
        for (int i = 1; i < capacity; i++) {
        	result.append(", ");
        	result.append(theArray[i]);
        }
        result.append("] ");
   
       
        result.append("Capacity:" + this.capacity);
        result.append(" Size:" + this.size);
        result.append(" Read:" + this.read);
        result.append(" Write:" + this.write);
        return result.toString();
	}

}
