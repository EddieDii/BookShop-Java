// manage the cart operaations, 
// including adding a book to the cart, removing a book from the cart and checking out.
// the methods in this class are high-level methods
// it is user-facing and interacts with the user
import java.util.List;

public class CartManage implements CartOperations {
    private ShoppingCart cart;
    private BookStore bookStore;
    private Menu menu;

    public CartManage(ShoppingCart cart, BookStore bookStore, Menu menu) {
        this.cart = cart;
        this.bookStore = bookStore;
        this.menu = menu;
    }

    @Override
    // handling the overall process of adding a book to the cart
    // including calling the searching method from other classes
    // checking if the book is found and the availability of copies or ebooks
    // and deciding whether to add the book to the cart
    public void addBookToCart() {
        String keyword = menu.getKeyword();
        List<Book> foundBooks = bookStore.findBooksByKeyword(keyword);

        // check if any books are found
        if (foundBooks.isEmpty()) {
            System.out.println("Sorry! There are no books that match with your keyword.");
            return;
        }

        System.out.println("The following book(s) are found:");
        // loop through the found books and display them
        for (int i = 0; i < foundBooks.size(); i++) {
            System.out.println((i + 1) + ". " + foundBooks.get(i));
        }

        // display the cancel option as the last option
        menu.displayCancelOption(foundBooks.size() + 1); 

        // get the user selection
        int selection = menu.getBookSelection(foundBooks.size() + 1);
        if (selection == foundBooks.size() + 1 || selection == -1) {
            System.out.println("You have cancelled the operation.");
            return;
        }
        
        // if user not cancel the operation, add add the book to the cart
        // then get the selected book
        Book selectedBook = foundBooks.get(selection - 1); 

        // check if the selected book has an ebook
        // if not, ask the user if they want to add the physical copy instead
        // if yes, check if there are physical copies available
        // if yes, add the physical copy to the cart
        // if no, display a message that no physical copies are available 
        // and return to the main menu 
        if (!selectedBook.hasEbook()) {
            System.out.println("This book does not have an ebook available.");
            if (menu.getConfirmation("Would you like to add the physical copy instead")) {
                if (selectedBook.getCopies() > 0) {
                    cart.addBook(selectedBook, false);
                } else {
                    System.out.println("Sorry! No physical copies are available.");
                }
            } else {
                System.out.println("Returning to the main menu.");
                return;
            }
        } else {
            // set the boolean value to true if the selected book has ebook and user wants to add ebook
            boolean ebookOption = selectedBook.hasEbook() && menu.askForEbook();
            
            // if user did not select the ebook option and there are no physical copies available
            if (!ebookOption && selectedBook.getCopies() <= 0) {
                if (menu.getConfirmation("No physical copies available. Would you like to add the ebook instead")) {
                    // call the addBook method to 
                    cart.addBook(selectedBook, true);
                } else {
                    System.out.println("Returning to the main menu.");
                }
            } else {
                cart.addBook(selectedBook, ebookOption);
            }
        }
    }

    @Override
    // handling the overall process of removing a book from the cart
    public void removeBookFromCart() {
        if (cart.isCartEmpty()) {
            return;
        }

        cart.viewCart();
        List<CartItem> cartItems = cart.getCartItems();

        // display the cancel option as the last option
        menu.displayCancelOption(cartItems.size() + 1);
        int removeIndex = menu.getRemovalIndex();

        if (removeIndex == cartItems.size()) {
            System.out.println("Returning to the main menu.");
            return;
        }

        if (removeIndex >= 0 && removeIndex < cartItems.size()) {
            CartItem itemToRemove = cartItems.get(removeIndex);
            cart.removeBook(itemToRemove.getBook());
        } else {
            System.out.println("Invalid selection.");
        }
    }

    @Override
    public void checkout() {
        double total = cart.checkout();
        System.out.printf("You have purchased items to the total value of $%.2f.\n", total);
        System.out.println("Thanks for shopping with The Reading Room!");
    }
}
