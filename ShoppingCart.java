public class ShoppingCart {
    private Book[] books;

    public ShoppingCart(Book[] books) {
        this.books = books;
    }

    public void addBook(Book book, boolean isEbook) {
        if(isEbook || book.getCopies()>0) {
            book.addToCart(isEbook);
            System.out.println("\"" + book.getTitle() + "\" has been added to the shopping cart.");
        } else {
            System.out.println("Sorry! There are no physical copies of the book!");
        }
    }

    public void removeBookFromCart(int index) {
        if (index >= 0 && index < books.length) {
            Book book = books[index];
            if (book.isInCart()) {
                book.removeBookFromCart();
                System.out.println("Item removed from shopping cart.");
            } else {
                System.out.println("The book is not in the shopping cart.");
            }
        } else {
            System.out.println("Invalid selection. Please enter a valid number.");
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
