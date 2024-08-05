public class ReadingRoom {
    // Book class to show the information of a book
    static class Book {
        // Instance variables
        String title;
        String author;
        int copies;
        boolean hasEbook;
        // Constructor
        Book(String title, String author, int copies, boolean hasEbook) {
            this.title = title;
            this.author = author;
            this.copies = copies;
            this.hasEbook = hasEbook;
        }
    }

    // list of books in the reading room
    static Book[] books = {
        new Book("Absolute Java", "Savitch", 5, true),
        new Book("JAVA: How to Program", "Deitel and Deitel", 0, true),
        new Book("Computing Concepts with JAVA 8 Essentials", "Horstman", 5, false),
        new Book("Java Software Solutions", "Lewis and Loftus", 5, false),
        new Book("Java Program Design", "Cohoon and Davidson", 1, true)
    };

    // Method to display the list of books
    private static void  displayBooks() {
        System.out.println("The following books are available:");
        for (int i = 0; i < books.length; i++) {
            System.out.println((i+1) + ". " + books[i].title + ", " + books[i].copies + " copies, " + (books[i].hasEbook ? "ebook available" : "no ebook"));
        }
    }


    public static void main(String[] args) {
        System.out.println("Reading Room");
    }
}