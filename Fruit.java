class Fruit {
    private String name;
    private double price;
    private Long id;
    private int quantity;

    public Fruit(String name, double price, Long id, int quantity) {
        this.name = name;
        this.price = price;
        this.id = id;
        this.quantity = quantity;
    }
    public class AddFruit{
        public void addFruit(String name, double price, Long id, int quantity) {
            // update the enclosing Fruit instance instead of assigning to a non-existent static field
            Fruit.this.name = name;
            Fruit.this.price = price;
            Fruit.this.id = id;
            Fruit.this.quantity = quantity;
        }
    }
    public class RemoveFruit {
        public void remove(Fruit fruitToRemove) {
            // nulling the reference passed in has no effect on caller; clear fields instead
            if (fruitToRemove != null) {
                fruitToRemove.name = null;
                fruitToRemove.price = 0.0;
                fruitToRemove.id = null;
                fruitToRemove.quantity = 0;
            }
        }
    }
    public  void displayFruit(){
        System.out.println("fruit name:" + name + ", price:" + price + ", quantity:" + quantity);
    }
    public class UpdateFruit {
        // renamed method to avoid having the same name as the enclosing class
        public void setFruit(String newName, double newPrice, int newQuantity) {
            name = newName;
            price = newPrice;
            quantity = newQuantity;
        }
    }
    public class Quantity {
        public void updateQuantity(int newQuantity) {
            quantity = newQuantity;
        }
    }
    public class ReturnFruit {
        public String returnFruit() {
            return name + " - Price: " + price + ", Quantity: " + quantity;
        }
    }
    public class Price {
        public void updatePrice(double newPrice) {
            price = newPrice;
        }
    }
    public class SearchFruit {
        public boolean matches(String searchName, int searchid) {
            return name.equalsIgnoreCase(searchName) && id == searchid;
        }
    }

    
    public class CartItem {
        public void addToCart(int quantityToAdd) {
            if (quantityToAdd <= quantity) {
                quantity -= quantityToAdd;
            } else {
                System.out.println("Not enough quantity available.");

            }
        }
    }
    public class RemoveFromCart {
        public void removeQuantity(int quantityToRemove) {
            quantity += quantityToRemove;
        }
    }
    public class DisplayCartItems {
        public void printCartItems() {
            System.out.println("Cart items:" + name + " - Price: " + price + ", Quantity: " + quantity);
        }
    }
    public class TotalPrice {
        public void calculateTotalPrice() {
            double totalPrice = price * quantity;
            System.out.println("Total price: " + totalPrice);
        }
    }
    public class Checkout {
        public void performCheckout() {
            System.out.println("Checkout complete. Total price:" + (price * quantity));
        }
    }
    public class ClearCart {
        public void clear() {
            quantity = 0;
        }
    }
    public static void main(String[] args) {
        Fruit apple = new Fruit("Apple", 0.5, 1L, 100);
        apple.displayFruit();

        AddFruit add = apple.new AddFruit();
        add.addFruit("Banana", 0.3, 2L, 150);

        RemoveFruit remove = apple.new RemoveFruit();
        remove.remove(apple);

        UpdateFruit update = apple.new UpdateFruit();
        update.setFruit("Green Apple", 0.6, 120);

        Quantity quantityUpdate = apple.new Quantity();
        quantityUpdate.updateQuantity(80);

        ReturnFruit returnInfo = apple.new ReturnFruit();
        System.out.println(returnInfo.returnFruit());

        Price priceUpdate = apple.new Price();
        priceUpdate.updatePrice(0.55);

        SearchFruit search = apple.new SearchFruit();
        System.out.println("Search for Apple: " + search.matches("Apple", 1));
    }
}