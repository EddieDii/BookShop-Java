// managing the cart, adding and removing books from the cart, and check out
public class CartManage {
    private ShoppingCart cart;
    private BookStore bookStore;
    private Menu menu;

    public CartManage(ShoppingCart cart, BookStore bookStore, Menu menu) {
        this.cart = cart;
        this.bookStore = bookStore;
        this.menu = menu;
    }

    public void addBookToCart() {
        String keyword = menu.getKeyWord();
        Book[] foundBooks = bookStore.findBooksByKeyword(keyword);

        if (foundBooks.length == 0) {
            System.out.println("Sorry! There are no books that match with your keyword.");
            return;
        }

        System.out.println("The following book(s) are found: ");
        for (int i = 0; i < foundBooks.length; i++) {
            System.out.println((i+1) + ". " + foundBooks[i]);
        }
        menu.displayCancelOption(foundBooks.length + 1);

        int selection = menu.getBookSelection(foundBooks.length + 1);
        if (selection == foundBooks.length + 1 || selection == -1) {
            System.out.println("You have cancelled the operation.");
            return;
        }

        Book selectedBook = foundBooks[selection-1];
        boolean ebookOption = selectedBook.hasEbook() && menu.askForEbook();

        cart.addBook(selectedBook, ebookOption);
    }

    public void removeBookFromCart() {
        cart.viewCart();
        int removeIndex = menu.getRemovalIndex();
        if (removeIndex >= 0 && removeIndex < cart.getBooks().length) {
            Book bookToRemove = cart.getBooks()[removeIndex];
            cart.removeBook(bookToRemove);
        } else {
            System.out.println("Invalid selection.");
        }
    }

    public void checkout() {
        double total = cart.checkout();
        System.out.printf("You have purchased items to the total value of $%.2f.\n", total);
        System.out.println("Thanks for shopping with The Reading Room!");
    }

}
 