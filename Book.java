public class Book {
    private String title;
    private String author;
    private int copies; 
    private boolean hasEbook;
    private boolean isInCart;
    private boolean isEbookInCart;

    public Book(String title, String author, int copies, boolean hasEbook) {
        this.title = title;
        this.author = author;
        this.copies = copies;
        this.hasEbook = hasEbook;
        this.isInCart = false;
        this.isEbookInCart = false;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public int getCopies() {
        return copies;
    }

    public boolean hasEbook() {
        return hasEbook;
    }

    public boolean isInCart() {
        return isInCart;
    }

    public boolean isEbookInCart() {
        return isEbookInCart;
    }

    public void addToCart(boolean isEbook) {
        this.isInCart = true;
        this.isEbookInCart = isEbook;
        if (!isEbook && copies > 0) {
            copies--; // 确保减少库存数量
        }
    }

    public void removeFromCart() {
        this.isInCart = false;
        if (!isEbookInCart) {
            copies++; // 恢复库存数量
        }
        this.isEbookInCart = false;
    }

    @Override
    public String toString() {
        return title + " | " + author + " | " + copies + " copies | " + (hasEbook ? "yes" : "no");
    }
}
