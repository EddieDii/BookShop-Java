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
        if (!isEbook && book.getCopies() > 0) {
            this.quantity++;
            book.addToCart(false); 
        } else if (isEbook) {
            this.quantity++;
        }
    }

    public void decrementQuantity() {
        this.quantity--;
    }

    @Override
    public String toString() {
        return book.getTitle() + " | " + quantity + (quantity > 1 ? " copies" : " copy") + " | " + (isEbook ? "e-book" : "physical copy");
    }
}
