// to store the books in the shopping cart 
// and perform operations like adding, removing, viewing and checking out books
public class ShoppingCart {
    private Book[] books;

    public ShoppingCart(Book[] books) {
        this.books = books;
    }
    
    // to get the list of books
    public Book[] getBooks() {
        return books;
    }
    public void addBook(Book book, boolean isEbook) {
        // check if the book is an ebook or there are physical copies of the book
        if(isEbook || book.getCopies()>0) {  
            book.addToCart(isEbook); 
            System.out.println("\"" + book.getTitle() + "\" has been added to the shopping cart.");
        } else {
            System.out.println("Sorry! There are no physical copies of the book!");
        }
    }

    public void removeBook(Book book) {
        if (book.isInCart()) {
            book.removeBookFromCart();
            System.out.println("Item removed from shopping cart.");
        } else {
            System.out.println("This book is not in the shopping cart.");
        }
    }
    
    public void viewCart() {
        System.out.println("Your shopping cart contains the following book(s):");
        int count = 1;
        for (Book book : books) {
            if (book.isInCart()) {
                System.out.println(count + ". " + book.getTitle());
                count++;
            }
        }
        if (count == 1) {
            System.out.println("Your shopping cart is empty.");
        }
    }

    public double checkout() {
        double total = 0.0;
        for (Book book:books) {
            if (book.isInCart()) {
                total += book.isEbookInCart() ? 8.0:50.0;
                book.removeBookFromCart();
            }
        }
        return total;
    }
}
