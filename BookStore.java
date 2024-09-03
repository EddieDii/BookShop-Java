// Create a array of Book objects, 
// and offering several methods to interact with the array of Book objects.
// For example, you can search for books by keyword, display all books.
import java.util.List;
import java.util.ArrayList;
public class BookStore {
    // create am array of Book objects
    private Book[] books; 
    public BookStore() {
        books = new Book[]{
            new Book("Absolute Java", "Savitch", 5, true),
            new Book("JAVA: How to Program", "Deitel and Deitel", 0, true),
            new Book("Computing Concepts with JAVA 8 Essentials", "Horstman", 5, false),
            new Book("Java Software Solutions", "Lewis and Loftus", 5, false),
            new Book("Java Program Design", "Cohoon and Davidson", 1, true)
        };
    }

    public Book[] getBooks() {
        return books;
    }
    
    // search for books by keyword
    public List<Book> findBooksByKeyword(String keyword) {
        // using ArrayList to store the found books provides more flexibility
        // and it is easier to add or remove elements because it is dynamic in size
        // It allows us to handle varying numbers of search results efficiently
        List<Book> foundBooks = new ArrayList<>(); 
        for (Book book : books) {
            if (book.getTitle().toLowerCase().contains(keyword.toLowerCase())) {
                foundBooks.add(book);
            }
        }
        return foundBooks; 
    }

    public void displayBooks() {
        System.out.println("The following books are available:");
        for (int i = 0; i < books.length; i++) {
            System.out.println((i + 1) + ". " + books[i]);
        }
    }
}
