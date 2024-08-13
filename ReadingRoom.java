import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
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

    // Shopping cart
    static List<Book> shoppingCart = new ArrayList<>();
    static List<Boolean> isEbookInCart = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean quit = false;
        while(! quit) {
            System.out.println("======================================================");
            System.out.println("Welcome to the Reading Room!");
            System.out.println("======================================================");
            System.out.println("Choose an option:");
            System.out.println("1. Add a book to shopping cart");
            System.out.println("2. View shopping cart");
            System.out.println("3. Remove a book from shopping cart");
            System.out.println("4. Checkout");
            System.out.println("5. List all books");
            System.out.println("6. Quit");
            System.out.print("Please select:");
            int option = scanner.nextInt();
            scanner.nextLine();

            switch(option) {
                case 1:
                    addBookToCart(scanner);
                    break;
                case 2:
                    viewCart();
                    break;
                case 3:
                    removeBookFromCart(scanner);
                    break;
                case 4:
                    checkout();
                case 5:
                    displayBooks();
                    break;
                case 6:
                    quit = true;
                    System.out.println("Thank you for visiting the Reading Room! Bye!");
                    break;
                default:
                    System.out.println("Invalid option. Please enter a number between 1 to 6.");
                    break;
            }
        }
        
        scanner.close();
    }

    // Method to add a book to the shopping cart
    // enter a keyword and search for books that match the keyword
    private static void addBookToCart(Scanner scanner) {
        System.out.print("Enter a keyword:");
        String keyword = scanner.nextLine().toLowerCase();
        // store the found books in a list
        List<Integer> foundBooks = new ArrayList<>();
        for (int i = 0; i < books.length; i++) {
            if (books[i].title.toLowerCase().contains(keyword)) {
                foundBooks.add(i);
            }
        }

        // if no books are found, print a message and return
        if (foundBooks.isEmpty()) {
            System.out.println("Sorry! There are no books that match with your keyword.");
            return;
        }

        // print the list of found books
        System.out.println("The following book(s) are found:");
        for (int i = 0; i < foundBooks.size(); i++) {
            Book book = books[foundBooks.get(i)];
            System.out.println((i+1) + ". " + book.title);
        }
        System.out.println((foundBooks.size()+1) + " . Cancel"); // add a cancel option in the end of the list
        System.out.print("Please select:");
        int selection = scanner.nextInt();
        scanner.nextLine();

        if (selection == foundBooks.size() + 1) { // select the cancel option
            System.out.println("You have cancelled the operation.");
            return;
        }
        //  add the selected book to the shopping cart
        // index of the book is the selection - 1
        // set the ebook option to false by default
        Book selectedBook = books[foundBooks.get(selection-1)];
        boolean ebookOption = false;
        
        // if the book has an ebook, ask the user if they want to buy the ebook
        if (selectedBook.hasEbook) {
            System.out.print("Do you want to buy this as an ebook(yes/no):");
            ebookOption = scanner.nextLine().equalsIgnoreCase("yes");
        }
        
        // if the book has physical copies or the user selected the ebook option, add the book to the shopping cart
        if(ebookOption || selectedBook.copies > 0) {
            shoppingCart.add(selectedBook);
            isEbookInCart.add(ebookOption);
            System.out.println("\"" + selectedBook.title + "\" has been added to the shopping cart.");
            if (!ebookOption) {
                selectedBook.copies--;
            }
        } else {
            System.out.println("Sorry! There are no physical copies of the book!");
        }
    }

    // Method to view the shopping cart
    private static void viewCart() {
        System.out.println("Your shopping cart contains the following book(s):");
        for (int i = 0; i < shoppingCart.size(); i++) {
            Book book = shoppingCart.get(i);
            System.out.println((i+1) + ". " + book.title);
        }
    }

    // Method to remove a book from the shopping cart
    private static void removeBookFromCart(Scanner scanner) {
        viewCart();
        System.out.print("Your shopping cart contains the following book(s):");
        int removeIndex = scanner.nextInt();
        scanner.nextLine();

        if (removeIndex < 1 || removeIndex > shoppingCart.size()) {
            System.out.println("Invalid selection. Please enter a number between 1 to " + shoppingCart.size());
            return;
        }

        Book removedBook = shoppingCart.remove(removeIndex - 1);
        boolean removedEbook = isEbookInCart.remove(removeIndex - 1);

        if(!removedEbook) {
            removedBook.copies++;
        }
        System.out.println("Item removed from shopping cart.");
    }

    // Method to checkout
    private static void checkout() {
        double total = 0.0;
        for (int i = 0; i < shoppingCart.size(); i++) {
            total += isEbookInCart.get(i) ? 8.0 : 50.0;
        }
        System.out.printf("You have purchased items to the total value of $%.2f.\n", total);
        System.out.println("Thanks for shopping with The Reading Room!");
        shoppingCart.clear();
        isEbookInCart.clear();
    }

    // Method to display the list of books 
    private static void  displayBooks() {
        System.out.println("The following books are available:");
        for (int i = 0; i < books.length; i++) {
            System.out.println((i+1) + ". " + books[i].title + ", " + books[i].copies + " copies, " + (books[i].hasEbook ? "ebook available" : "no ebook"));
        }
    }
}