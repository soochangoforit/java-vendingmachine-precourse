package vendingmachine.domain;

import static vendingmachine.domain.ExceptionMessage.INVALID_PRODUCT_PRICE;

import vendingmachine.util.NullOrEmptyValidator;

public class Product {

    private static final int MINIMUM_PRICE = 100;
    private final String name;
    private final int price;
    private final int quantity;

    private Product(String name, int price, int quantity) {
        validate(name, price);
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    private void validate(String name, int price) {
        NullOrEmptyValidator.throwIfBlank(name);
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
}
