// Book class to show the information of a book
public class Book {
    private String title;
    private String author;
    private int copies;
    private boolean hasEbook;
    private boolean isInCart; // to check if the book is in the cart
    private boolean isEbookInCart; // to check if the ebook is in the cart

    // Constructor
    public Book(String title, String author, int copies, boolean hasEbook) {
        this.title = title;
        this.author = author;
        this.copies = copies;
        this.hasEbook = hasEbook;
        this.isInCart = false;
        this.isEbookInCart = false;
    }
    // Getters
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

    // reduce the number of copies of the book
    private void decreaseCopies() {
        if (copies > 0) {
            copies--;
        }
    }

    // increase the number of copies of the book
    private void increaseCopies() {
        copies++;
    }

    // set the template for the book information to print
    @Override
    public String toString() {
        return title + " | " + author + " | " + copies + " copies | " + (hasEbook ? "yes" : "no");
    }
}
