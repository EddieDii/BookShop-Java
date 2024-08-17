// Book class to show the information of a book
public class Book {
    private String title;
    private String author;
    private int copies;
    private boolean hasEbook;
    private boolean isInCart;
    private boolean isEbookInCart;

    // Constructor
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
        if (!isEbook) {
            decreaseCopies();  // if not an ebook, decrease the number of copies from the library
        }
    }

    public void removeBookFromCart() {
        this.isInCart = false;
        if (!isEbookInCart) {
            increaseCopies();  // if not an ebook, increase the number of copies in the library
        }
        this.isEbookInCart = false;
    }

    private void decreaseCopies() {
        if (copies > 0) {
            copies--;
        }
    }

    private void increaseCopies() {
        copies++;
    }

    @Override
    public String toString() {
        return title + " | " + author + " | " + copies + " copies | " + (hasEbook ? "yes" : "no");
    }




}
