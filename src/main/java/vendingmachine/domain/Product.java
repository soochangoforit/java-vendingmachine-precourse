package vendingmachine.domain;

public class Product {

    private final String name;
    private final int price;
    private final int quantity;

    private Product(String name, int price, int quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public static Product init(String name, int price, int quantity) {
        return new Product(name, price, quantity);
    }
}
