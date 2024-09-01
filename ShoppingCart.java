import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {
    private List<CartItem> cart; // 使用 CartItem 列表来管理购物车

    public ShoppingCart() {
        cart = new ArrayList<>();
    }

    public List<CartItem> getCartItems() {
        return cart;
    }

    public void addBook(Book book, boolean isEbook) {
        // 检查库存是否充足
        if (!isEbook && book.getCopies() <= 0) { // 如果是实体书且库存为 0
            System.out.println("Sorry! There are no physical copies of \"" + book.getTitle() + "\" available.");
            return;
        }

        for (CartItem item : cart) {
            if (item.getBook().equals(book) && item.isEbook() == isEbook) {
                item.incrementQuantity(); // 增加现有 CartItem 的数量，并减少库存
                System.out.println("\"" + book.getTitle() + "\" has been added to the shopping cart.");
                return;
            }
        }

        // 如果购物车中没有该书，创建一个新的 CartItem
        if (isEbook || book.getCopies() > 0) { // 再次确保库存检查
            if (!isEbook) {
                book.addToCart(false); // 直接调用减少库存的方法
            }
            cart.add(new CartItem(book, 1, isEbook)); // 添加新的书籍到购物车
            System.out.println("\"" + book.getTitle() + "\" has been added to the shopping cart.");
        } else {
            System.out.println("Sorry! There are no physical copies of the book!");
        }
    }

    public void removeBook(Book book) {
        CartItem itemToRemove = null;

        for (CartItem item : cart) {
            if (item.getBook().equals(book)) {
                item.decrementQuantity(); // 减少数量
                if (item.getQuantity() <= 0) {
                    itemToRemove = item;
                }
                System.out.println("Item removed from shopping cart.");

                // 如果是实体书，恢复库存
                if (!item.isEbook()) {
                    book.removeFromCart();
                }
                break;
            }
        }

        if (itemToRemove != null) {
            cart.remove(itemToRemove);
        }
    }

    public void viewCart() {
        System.out.println("Your shopping cart contains the following book(s):");
        if (cart.isEmpty()) {
            System.out.println("Your shopping cart is empty.");
        } else {
            int count = 1;
            for (CartItem item : cart) {
                System.out.println(count + ". " + item);
                count++;
            }
        }
    }

    public double checkout() {
        double total = 0.0;
        for (CartItem item : new ArrayList<>(cart)) {
            if (item.getQuantity() > 0) {
                total += item.isEbook() ? 8.0 * item.getQuantity() : 50.0 * item.getQuantity();
                cart.remove(item); // 清空购物车
            }
        }
        return total;
    }
}
