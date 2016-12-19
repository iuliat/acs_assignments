
package com.acertainbookstore.client.workloads;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Set;

import com.acertainbookstore.business.CertainBookStore;
import com.acertainbookstore.business.ImmutableBook;
import com.acertainbookstore.business.ImmutableStockBook;
import com.acertainbookstore.business.StockBook;

/**
 * Helper class to generate stockbooks and isbns modelled similar to Random
 * class
 */
public class BookSetGenerator {

	public BookSetGenerator() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * Returns num randomly selected isbns from the input set
	 * 
	 * @param num
	 * @return
	 */
	public Set<Integer> sampleFromSetOfISBNs(Set<Integer> isbns, int num) {
		Set<Integer> ISBNs = new HashSet<>();
	    Random randomGenerator = new Random();
	    List<Integer> isbnsList = new ArrayList<Integer>(isbns);
	    for (int index = 1; index <= num; index++){
	      int randomIndex = randomGenerator.nextInt(isbns.size());
	      ISBNs.add(isbnsList.get(randomIndex));
	    }
		return ISBNs;
	}

	/**
	 * Return num stock books. For now return an ImmutableStockBook
	 * 
	 * @param num
	 * @return
	 */
	public Set<ImmutableStockBook> nextSetOfStockBooks(int num) {
		CertainBookStore bookStore = new CertainBookStore();
		Set<ImmutableStockBook> immutableStockBooks = new HashSet<ImmutableStockBook>();
		ArrayList<Integer> existentISBNs = new ArrayList<Integer>();
		
		int ISBN_RANGE = 100;
		// ISBNs will be in range of (1,100)
		Random randomGenerator = new Random();		
		
	    for (int index = 1; index <= num; index++){
			int  randomISBN = randomGenerator.nextInt(100) + 1;
			while(existentISBNs.contains(randomISBN)){
				randomISBN = randomGenerator.nextInt(100) + 1;
			}
	    	ImmutableStockBook immutableBook = generateImmutableBook(randomISBN);
	    	immutableStockBooks.add(immutableBook);
		}
		return immutableStockBooks;
	}
	
	private ImmutableStockBook generateImmutableBook(int ISBN){
		int NO_BOOKS = 100;
		int TITLE_MAX_LENGTH = 25;
		int AUTHOR_MAX_LENGTH = 20;
		int MAX_PRICE = 1000;
		int MAX_NO_COPIES = 500;
		
		Random randomGenerator = new Random();

		// Titles will be maximum TITLE_MAX_LENGTH characters long
		String randomTitle = generateRandomString(TITLE_MAX_LENGTH);
		
		// Titles will be maximum AUTHOR_MAX_LENGTH characters long
		String randomAuthor = generateRandomString(AUTHOR_MAX_LENGTH);

		// Price will be in range of (1, MAX_PRICE)
		int  randomPrice = randomGenerator.nextInt(MAX_PRICE) + 1;

		// No. of copies for a book will be in range of (1, MAX_NO_COPIES)
		int randomNoCopies = randomGenerator.nextInt(MAX_PRICE) + 1;
		
		boolean randomEditorPick = randomGenerator.nextBoolean();

		
		ImmutableStockBook immutableStockBook = new ImmutableStockBook(
				ISBN, randomTitle, randomAuthor, randomPrice, randomNoCopies, 0, 0, 0, randomEditorPick);
		return immutableStockBook;
	}
	
    private String generateRandomString(int length){
        String alphabet= "abcdefghijklmnopqrstuvwxyz";
        String randomString = "";
        Random random = new Random();
        // make sure that no empty string will be generated
        int randomLen = 1+random.nextInt(length-1);
        for (int i = 0; i < randomLen; i++) {
            char c = alphabet.charAt(random.nextInt(alphabet.length()));
            randomString+=c;
        }
		return randomString;
    }

}
