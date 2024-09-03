// The main entry point forr the Reading Room program.
// including the main method to manage the flow of the program
public class ReadingRoom {
    private static BookStore bookStore = new BookStore();
    private static ShoppingCart cart = new ShoppingCart();
    private static Menu menu = new Menu();
    private static CartManage cartManage = new CartManage(cart, bookStore, menu);

    public static void main(String[] args) {
        boolean quit = false;

        while (!quit) {
            int option = menu.displayMainMenu();
            switch (option) {
                case 1:
                    cartManage.addBookToCart();
                    break;
                case 2:
                    cart.viewCart();
                    break;
                case 3:
                    cartManage.removeBookFromCart();
                    break;
                case 4:
                    cartManage.checkout();
                    break;
                case 5:
                    bookStore.displayBooks();
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
    }
}
