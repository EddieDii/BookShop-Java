import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {
    private List<CartItem> cart; 

    public ShoppingCart() {
        cart = new ArrayList<>();
    }

    public List<CartItem> getCartItems() {
        return cart;
    }

    public void addBook(Book book, boolean isEbook) {

        if (!isEbook && book.getCopies() <= 0) { 
            System.out.println("Sorry! There are no physical copies of \"" + book.getTitle() + "\" available.");
            return;
        }

        for (CartItem item : cart) {
            if (item.getBook().equals(book) && item.isEbook() == isEbook) {
                item.incrementQuantity(); 
                System.out.println("\"" + book.getTitle() + "\" has been added to the shopping cart.");
                return;
            }
        }

        if (isEbook || book.getCopies() > 0) { 
            if (!isEbook) {
                book.addToCart(false); 
            }
            cart.add(new CartItem(book, 1, isEbook)); 
            System.out.println("\"" + book.getTitle() + "\" has been added to the shopping cart.");
        } else {
            System.out.println("Sorry! There are no physical copies of the book!");
        }
    }

    public void removeBook(Book book) {
        CartItem itemToRemove = null;

        for (CartItem item : cart) {
            if (item.getBook().equals(book)) {
                item.decrementQuantity(); 
                if (item.getQuantity() <= 0) {
                    itemToRemove = item;
                }
                System.out.println("Item removed from shopping cart.");


                if (!item.isEbook()) {
                    book.removeFromCart();
                }
                break;
            }
        }

        if (itemToRemove != null) {
            cart.remove(itemToRemove);
        }
    }

    public void viewCart() {
        System.out.println("Your shopping cart contains the following book(s):");
        if (cart.isEmpty()) {
            System.out.println("Your shopping cart is empty.");
        } else {
            int count = 1;
            for (CartItem item : cart) {
                System.out.println(count + ". " + item);
                count++;
            }
        }
    }

    public double checkout() {
        double total = 0.0;
        for (CartItem item : new ArrayList<>(cart)) {
            if (item.getQuantity() > 0) {
                total += item.isEbook() ? 8.0 * item.getQuantity() : 50.0 * item.getQuantity();
                cart.remove(item); 
            }
        }
        return total;
    }
}
