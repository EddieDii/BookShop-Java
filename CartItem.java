// Store information about a book in the cart, 
//including the quantity and whether it is an e-book or physical copy.
// method: increase the quantity of the book in the cart and decrease the quantity from the stock and vice versa
public class CartItem {
    private Book book;
    private int quantity; 
    private boolean isEbook; 

    public CartItem(Book book, int quantity, boolean isEbook) {
        this.book = book;
        this.quantity = quantity;
        this.isEbook = isEbook;
    }

    public Book getBook() {
        return book;
    }

    public int getQuantity() {
        return quantity;
    }

    public boolean isEbook() {
        return isEbook;
    }

    public void incrementQuantity() {
        // if the book is not an ebook and there are copies available
        // then increase the quantity of the book in the cart
        // and call the method addToCart to decrease the number of copies from the stock
        // otherwise, increase the quantity of the book in the cart only
        if (!isEbook && book.getCopies() > 0) {
            this.quantity++;
            book.addToCart(false); 
        } else if (isEbook) {
            this.quantity++;
        }
    }

    public void decrementQuantity() {
        // no matter the book is an ebook or not
        // decrease the quantity of the book in the cart
        this.quantity--;
    }

    @Override
    public String toString() {
        return book.getTitle() + " | " + quantity + (quantity > 1 ? " copies" : " copy") + " | " + (isEbook ? "e-book" : "physical copy");
    }
}
