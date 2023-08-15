package vendingmachine.domain;

import java.util.Map;

public class VendingMachine {
    private final Products products;
    private final MachineCoins coins;

    private VendingMachine(final Products products, final MachineCoins coins) {
        this.products = products;
        this.coins = coins;
    }

    public static VendingMachine init(final Products products, final MachineCoins coins) {
        return new VendingMachine(products, coins);
    }

    public ProductInfo getProduct(String productName) {
        return products.getProduct(productName);
    }


    public boolean isAllSoldOut() {
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

    public Map<Coin, Integer> returnChange(UserMoney userMoney) {
        int change = userMoney.getUserMoney();
        return coins.changeToCoins(change);
    }
}
