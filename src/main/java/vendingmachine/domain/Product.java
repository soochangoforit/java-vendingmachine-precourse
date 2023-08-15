package vendingmachine.domain;

import vendingmachine.util.NullOrEmptyValidator;

public class Product {

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
            throw new IllegalArgumentException("상품의 가격은 100원 이상여야 합니다.");
        }
    }

    private static boolean isMinimumPrice(int price) {
        return price >= 100;
    }

    public static Product init(String name, int price, int quantity) {
        return new Product(name, price, quantity);
    }
}
