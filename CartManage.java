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
    public void addBookToCart() {
        String keyword = menu.getKeyword();
        List<Book> foundBooks = bookStore.findBooksByKeyword(keyword);

        if (foundBooks.isEmpty()) {
            System.out.println("Sorry! There are no books that match with your keyword.");
            return;
        }

        System.out.println("The following book(s) are found:");
        for (int i = 0; i < foundBooks.size(); i++) {
            System.out.println((i + 1) + ". " + foundBooks.get(i));
        }
        menu.displayCancelOption(foundBooks.size() + 1);

        int selection = menu.getBookSelection(foundBooks.size() + 1);
        if (selection == foundBooks.size() + 1 || selection == -1) {
            System.out.println("You have cancelled the operation.");
            return;
        }

        Book selectedBook = foundBooks.get(selection - 1);
        // check if the book has an ebook
        if(!selectedBook.hasEbook()) {
            System.out.println("This book does not have an ebook available.");
            System.out.println("Would you like to add the physical copy instead?");
            if(menu.confirmContinue()) {
                if (selectedBook.getCopies()>0) {
                    cart.addBook(selectedBook, false);
                    System.out.println("\"" + selectedBook.getTitle() + "\" physical copy has been added to the shopping cart.");
                } else {
                    System.out.println("Sorry! No physical copies are available.");
                }
            } else {
                System.out.println("Returning to the main menu.");
                return;
            }
        }
        boolean ebookOption = selectedBook.hasEbook() && menu.askForEbook();

        if(!ebookOption && selectedBook.getCopies() <= 0) {
            System.out.println("No physical copies available. Would you like to add the ebook instead? ");
            if(menu.askForEbook()) {
                cart.addBook(selectedBook, true);
                System.out.println("\"" + selectedBook.getTitle() + "\" e-book has been added to the shopping cart.");
            } else {
                System.out.println("Returning to the main menu.");
                return; 
            }
        } else {
            cart.addBook(selectedBook, ebookOption);
        }
    }

    @Override
    public void removeBookFromCart() {
        // 首先检查购物车是否为空
        if (cart.getCartItems().isEmpty()) {
            System.out.println("Your shopping cart is empty. Returning to the main menu.");
            return;
        }

        cart.viewCart();
        List<CartItem> cartItems = cart.getCartItems(); 


        System.out.println((cartItems.size() + 1) + ". Cancel");

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
