import java.util.List;
import java.util.ArrayList;
public class BookStore {
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

    public List<Book> findBooksByKeyword(String keyword) {
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
