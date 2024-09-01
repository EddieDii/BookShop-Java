import java.util.Scanner;

public class Menu {
    private Scanner scanner;

    public Menu() {
        this.scanner = new Scanner(System.in);
    }

    public int displayMainMenu() {
        System.out.println("======================================================");
        System.out.println("Welcome to the Reading Room!");
        System.out.println("======================================================");
        System.out.println("Choose an option:");
        System.out.println("1. Add a book to shopping cart");
        System.out.println("2. View shopping cart");
        System.out.println("3. Remove a book from shopping cart");
        System.out.println("4. Checkout");
        System.out.println("5. List all books");
        System.out.println("6. Quit");
        System.out.print("Please select: ");
        return scanner.nextInt();
    }

    public String getKeyword() {
        System.out.print("Enter a keyword: ");
        scanner.nextLine();  // Consume the newline
        return scanner.nextLine();
    }

    public int getBookSelection(int numberOfOptions) {
        System.out.print("Please select: ");
        int selection = scanner.nextInt();
        scanner.nextLine();  // Consume the newline
        if (selection < 1 || selection > numberOfOptions) {
            System.out.println("Invalid selection. Please try again.");
            return -1;
        }
        return selection;
    }


    public boolean askForEbook() {
        System.out.print("Do you want to buy this as an ebook (yes/no): ");
        String response = scanner.nextLine();
        return response.equalsIgnoreCase("yes");
    }

    public boolean confirmContinue() {
        System.out.print("Do you want to continue (yes/no): ");
        String response = scanner.nextLine();
        return response.equalsIgnoreCase("yes");
    }
    public int getRemovalIndex() {
        System.out.print("Which item do you want to remove: ");
        return scanner.nextInt() - 1;
    }

    public void displayCancelOption(int numberOfOptions) {
        System.out.println(numberOfOptions + ". Cancel");
    }
}