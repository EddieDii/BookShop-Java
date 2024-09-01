public class CartItem {
    private Book book;
    private int quantity; // 数量
    private boolean isEbook; // 是否为电子书

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
            book.addToCart(false); // 减少实体书库存
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
