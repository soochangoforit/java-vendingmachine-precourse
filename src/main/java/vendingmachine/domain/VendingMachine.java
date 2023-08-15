package vendingmachine.domain;

import java.util.Map;

public class VendingMachine {
    private final Map<Coin, Integer> coins;
    private final Products products;

    private VendingMachine(final Products products, final Map<Coin, Integer> coins) {
        this.products = products;
        this.coins = coins;
    }

    public static VendingMachine init(final Products products, final Map<Coin, Integer> coins) {
        return new VendingMachine(products, coins);
    }

    public ProductInfo getProduct(String productName) {
        return products.getProduct(productName);
    }
}
