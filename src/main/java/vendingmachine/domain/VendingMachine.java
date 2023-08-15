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


    public boolean isSoldOut() {
        return products.isSoldOut();
    }

    public void buyProduct(ProductInfo productToBuy, UserMoney userMoney) {
        // 재고 감소
        products.decreaseProduct(productToBuy.getProductName());
        // 사용자 돈 감소
        userMoney.decreaseMoney(productToBuy.getPrice());
    }

    public int minimumPrice() {
        return products.getMinimumPrice();
    }
}
