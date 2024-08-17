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

    public Book[] getBooks() {
        return books;
    }

    public Book[] findBooksByKeyword(String keyword) {
        int count = 0;
        for (int i = 0; i < books.length; i++) {
            if (books[i].getTitle().toLowerCase().contains(keyword)) {
                count++;
            }
        }
        Book[] foundBooks = new Book[count];
        int index = 0;
        for (int i = 0; i < books.length; i++) {
            if (books[i].getTitle().toLowerCase().contains(keyword)) {
                foundBooks[index] = books[i];
                index++;
            }
        }
        return foundBooks;
    }

    public void displayBooks() {
        System.out.println("The following books are available:");
        for (Book book : books) {
            System.out.println(book);
        }
    }
}
