// manage the book in the cart
// offer the operations to add book to the cart
// remove book from the cart, print the cart and checkout
// the methods in this class are Mid-level methods, manage the internal state of the cart
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

    public boolean isCartEmpty() {
        if (cart.isEmpty()) {
            System.out.println("Your shopping cart is empty. Returning to the main menu.");
            return true;
        }
        return false;
    }

    // actual addtion of a book to the cart
    // manage the internal state of the cart
    public void addBook(Book book, boolean isEbook) {
        // if the book is not an ebook and there are no physical copies available
        if (!isEbook && book.getCopies() <= 0) {
            System.out.println("Sorry! There are no physical copies of \"" + book.getTitle() + "\" available.");
            return;
        }

        for (CartItem item : cart) {
            // if the book is already in the cart, increment the quantity
            if (item.getBook().equals(book) && item.isEbook() == isEbook) {
                item.incrementQuantity();
                System.out.println("\"" + book.getTitle() + "\" has been added to the shopping cart.");
                return;
            }
        }

        // if the book is either an ebook or there are physical copies available
        if (isEbook || book.getCopies() > 0) {
            // if the book is not an ebook, add the physical copy to the cart
            if (!isEbook) {
                book.addToCart(false);
            }
            // if the book is an ebook, add the ebook to the cart
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
        if (isCartEmpty()) return;

        int count = 1;
        for (CartItem item : cart) {
            System.out.println(count + ". " + item);
            count++;
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
