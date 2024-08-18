// to store the list of books in the reading room and methods about the operations on the books storeage
public class BookStore {
    private Book[] books;

    public BookStore(){
        // list of books in the reading room
        books = new Book[] {
            new Book("Absolute Java", "Savitch", 5, true),
            new Book("JAVA: How to Program", "Deitel and Deitel", 0, true),
            new Book("Computing Concepts with JAVA 8 Essentials", "Horstman", 5, false),
            new Book("Java Software Solutions", "Lewis and Loftus", 5, false),
            new Book("Java Program Design", "Cohoon and Davidson", 1, true)
        };
    }
    // to get the list of books
    public Book[] getBooks() {
        return books;
    }

    // to find books by keyword
    public Book[] findBooksByKeyword(String keyword) {
        int count = 0;
        for (int i = 0; i < books.length; i++) {
            // if the keyword is found in the title of the book, count+1 & ignaore case
            if (books[i].getTitle().toLowerCase().contains(keyword)) {
                count++;
            }
        }
        // create an array to store the found books
        Book[] foundBooks = new Book[count];
        int index = 0;
        for (int i = 0; i < books.length; i++) {
            if (books[i].getTitle().toLowerCase().contains(keyword)) {
                foundBooks[index] = books[i]; // store the found book in the array
                index++;
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
