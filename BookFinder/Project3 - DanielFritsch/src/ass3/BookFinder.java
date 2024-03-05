package ass3;

/** 
 * This creates a dataset of books by reading the book data from 
 * GoodReadsData.txt
 */

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;



/**
 * A BookFinder class holds mappings between ISBNs, titles, authors, 
 * publishers, and ratings to books. each book is an object of type {@link BookData}.
 * 
 * @version Fall 2022
 * 
 */
public class BookFinder {
	
	MyHashMap<String, BookData> isbnToData;
	MyHashMap<String, ArrayList<BookData>> titleToData;
	MyHashMap<String, ArrayList<BookData>> authorToData;
	MyHashMap<String, ArrayList<BookData>> publisherToData;
	MyHashMap<Float, ArrayList<BookData>> ratingToData;
   
	
	/**
	 * Default constructor: used for tests
	 */
	public BookFinder() {
    	isbnToData = new MyHashMap<String, BookData>();
    	titleToData = new MyHashMap<String, ArrayList<BookData>>();
    	authorToData = new MyHashMap<String, ArrayList<BookData>>();
    	publisherToData = new MyHashMap<String, ArrayList<BookData>>();
    	ratingToData = new MyHashMap<Float, ArrayList<BookData>>();       
    }
	
	/**
     * Creates a BookFinder object by reading the data file at path.
     * 
     * The input file is a comma-separated text file with 5 fields per line:
     * isbn,authors,title,publisher,rating
     * 
     * Multiple authors are separated by '/' characters: 
     * for example: Frank Herbert/Domingo Santos
     * 
     * @param path The file path for the input data file.
     */
    public BookFinder (String path) {
    	this();
    	
    	fillDataFromFile(path);
    }   
    
    
    /*
     * You need to open the data file with a "UTF-8" flag, as in
     * 
     * Scanner scan = new Scanner( new File(s), "UTF-8");
     *
     * Parse each line of the file and create a new BookData object 
     * with the relevant fields. 
     * 
     * Put the newly created BookData object into isbnToData map with the isbn as the key.
     * 
     * For the other maps, add the BookData object to the ArrayList stored in the map with
     * the appropriate key (title, author, publisher, or rating). 
     * If a book has multiple authors, then each author's list should contain the BookData object.
     */
    private void fillDataFromFile(String path) {
    	Scanner scan = null;
	    try {
	        scan = new Scanner(new File(path), "UTF-8");
	    } catch (FileNotFoundException e) {
	        System.err.println("File not found");
	    }
	    
	    //TODO your code here
	    while (scan.hasNextLine()) {
            String line = scan.nextLine();
            String[] fields = line.split(",");

            String isbn = fields[0];
            String[] authors = fields[1].split("/");
            String title = fields[2];
            String publisher = fields[3];
            float rating = Float.parseFloat(fields[4]);

            BookData bd = new BookData(isbn, authors, title, publisher, rating);

            addBookByISBN(isbn, bd);
            addBookByTitle(title, bd);
            for (String author : authors) {
                addBookByAuthor(author, bd);
            }
            addBookByPublisher(publisher, bd);
            addBookByRating(rating, bd);
        }
    }
    
    /** 
     * Adds the isbn as a key and the BookData object as a value into the isbnToData map
     * 
     * @param isbn - book ISBN
     * @param bd - the BookData object
     */
    public void addBookByISBN(String isbn, BookData bd) {
    	//TODO your code here  	
    	isbnToData.put(isbn, bd);
    }
    
    /** 
     * Adds the title as a key and the BookData object as a value into the titleToData map
     * Note that a title is not guaranteed to be unique, that is why 
     * you should store a list of BookData objects for each title key.
     * 
     * @param title - book title
     * @param bd - the BookData object
     */
    public void addBookByTitle (String title, BookData bd) {
    	//TODO your code here  
    	ArrayList<BookData> arrayList = titleToData.get(title);
        if (arrayList == null) {
        	arrayList = new ArrayList<>();
        }
        arrayList.add(bd);
        titleToData.put(title, arrayList);
    }
    
    /** 
     * Adds the author as a key and the BookData object as a value into the authorToData map
     * There are of course many books for the same author, that is why 
     * you should store a list of BookData objects for each author key.
     * 
     * @param author - an author
     * @param bd - the BookData object
     */
    public void addBookByAuthor (String author, BookData bd) {
    	//TODO your code here  
    	ArrayList<BookData> arrayList = authorToData.get(author);
        if (arrayList == null) {
        	arrayList = new ArrayList<>();
        }
        arrayList.add(bd);
        authorToData.put(author, arrayList);
    }
    
    /** 
     * Adds the publisher as a key and the BookData object as a value into the authorToData map
     * There are many books from the same publisher, that is why 
     * you should store a list of BookData objects for each key.
     * 
     * @param publisher - a publisher
     * @param bd - the BookData object
     */
    public void addBookByPublisher (String publisher, BookData bd) {
    	//TODO your code here
    	ArrayList<BookData> arrayList = publisherToData.get(publisher);
        if (arrayList == null) {
        	arrayList = new ArrayList<>();
        }
        arrayList.add(bd);
        publisherToData.put(publisher, arrayList);
    }
    
    /** 
     * Groups all the books with the same rating under the same map key
     * 
     * There are many books with the same rating, that is why 
     * you should store a list of BookData objects for each key.
     * 
     * @param rating - a book rating
     * @param bd - the BookData object
     */
    public void addBookByRating(Float rating, BookData bd) {
    	//TODO: your code here
    	ArrayList<BookData> arrayList = ratingToData.get(rating);
        if (arrayList == null) {
        	arrayList = new ArrayList<>();
        }
        arrayList.add(bd);
        ratingToData.put(rating, arrayList);
    }
    
    //possible helper method to which you can pass the map of interest, the key, and the value
    //and it will add the value to the list of values
    //you can then reuse this for addBookByTitle, addBookByAuthor, addBookByPublisher
    // you DO NOT have to implement this helper - feel free to delete it.
    private static void addBookToList(MyHashMap<String, ArrayList<BookData>> map, 
    		String key, BookData bd) {
    	//Optional TODO: your code here  
    	ArrayList<BookData> arrayList = map.get(key);
        if (arrayList == null) {
        	arrayList = new ArrayList<>();
        }
        arrayList.add(bd);
        map.put(key, arrayList);
    }   

	/**
	 * Returns a list of books written by the author.
	 * 
	 * @param author The author to search for.
	 * @return A list of {@link BookData} objects written by author.
	 */
	public List<BookData> searchByAuthor(String author) {
		//TODO: your code here
		return authorToData.get(author);
	}

	/**
	 * Returns a list of books with the exact title.
	 * 
	 * @param title The title to search for.
	 * @return A list of {@link BookData} objects with the given title.
	 */
	public List<BookData> searchByTitle(String title) {
	    /* Implement this. */
		//TODO: your code here
		return titleToData.get(title);
	}

	/**
	 * Returns a list of books published by publisher.
	 * 
	 * @param publisher The publisher to search for.
	 * @return A list of {@link BookData} published by the publisher.
	 */
	public List<BookData> searchByPublisher(String publisher) {
		//TODO: your code here
		return publisherToData.get(publisher);
	}

	/**
	 * Returns a book corresponding to an ISBN, or null if no such book is in the
	 * database.
	 * 
	 * @param isbn The ISBN to search for.
	 * @return A (unique) {@link BookData} corresponding to the isbn, or null.
	 */
	public BookData searchByIsbn(String isbn) {
		//TODO: your code here
		return isbnToData.get(isbn);
	}
	
	/**
	 * Returns a list of books with the same rating
	 * 
	 * @param rating The value of book rating.
	 * @return A list of {@link BookData} with this rating.
	 */
	public List<BookData> searchByRating(Float rating) {
		//TODO: your code here
		return ratingToData.get(rating);
	}
}
