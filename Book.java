// show the information of the book and the status of the book
// the methods in this class are data-focused methods
// method: increase the quantity of the book in the stock 
// and decrease the quantity from the stock
public class Book {
    private String title;
    private String author;
    private int copies; 
    private boolean hasEbook;
    private boolean isInCart; // check if the book is in the cart
    private boolean isEbookInCart; // check if the book in the cart is Ebook

    public Book(String title, String author, int copies, boolean hasEbook) {
        this.title = title;
        this.author = author;
        this.copies = copies;
        this.hasEbook = hasEbook;
        this.isInCart = false;  // book is not in cart by default
        this.isEbookInCart = false; 
    }

    // getter methods
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

    // handles the internal state of a book instance when it is added to the cart
    // manages the number of copies in stock
    public void addToCart(boolean isEbook) {
        this.isInCart = true;
        this.isEbookInCart = isEbook;
        // if the book is not an ebook 
        // and there are copies available
        // then decrease the number of copies
        if (!isEbook && copies > 0) {
            copies--; 
        }
    }

    public void removeFromCart() {
        this.isInCart = false;
        // if the book is not in the cart
        // and it is not an ebook
        // then increase the number of copies
        if (!isEbookInCart) {
            copies++; 
        }
        // if the book is ebook
        // then set the book in the cart as not ebook
        this.isEbookInCart = false;
    }

    @Override
    public String toString() {
        return title + " | " + author + " | " + copies + " copies | " + (hasEbook ? "yes" : "no");
    }
}
