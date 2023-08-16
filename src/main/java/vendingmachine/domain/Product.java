package vendingmachine.domain;

import static vendingmachine.domain.ExceptionMessage.INVALID_PRODUCT_PRICE;
import static vendingmachine.domain.ExceptionMessage.SOLD_OUT_PRODUCT;

import vendingmachine.util.ParamValidator;

public class Product {

    private static final int MINIMUM_PRICE = 100;
    private static final int ZERO_QUANTITY = 0;
    private final String name;
    private final int price;
    private int quantity;

    private Product(String name, int price, int quantity) {
        validate(name, price);
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    private void validate(String name, int price) {
        ParamValidator.throwIfNullOrBlank(name);
        if (!isMinimumPrice(price)) {
            throw new IllegalArgumentException(INVALID_PRODUCT_PRICE);
        }
    }

    private static boolean isMinimumPrice(int price) {
        return price >= MINIMUM_PRICE;
    }

    public static Product of(String name, int price, int quantity) {
        return new Product(name, price, quantity);
    }

    public boolean isSameName(String productName) {
        return name.equals(productName);
    }

    public int getPrice() {
        return price;
    }

    public boolean isSoldOut() {
        return quantity == ZERO_QUANTITY;
    }

    public void decreaseQuantity() {
        if (isSoldOut()) {
            throw new IllegalArgumentException(SOLD_OUT_PRODUCT);
        }
        this.quantity--;
    }
}
