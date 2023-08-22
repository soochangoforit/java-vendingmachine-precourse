package vendingmachine.domain;

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


    public boolean isAllSoldOut() {
        return products.isAllSoldOut();
    }

    public void buyProduct(String productName, UserMoney userMoney) {
        Product productToBuy = products.getProduct(productName);
        // 재고 감소
        productToBuy.decreaseQuantity();
        // 사용자 돈 감소
        userMoney.decreaseMoney(productToBuy.getPrice());
    }

    public int minimumPrice() {
        return products.getMinimumPrice();
    }


    public void validateExistProduct(String productName) {
        products.validateExistProduct(productName);
    }

    public UserChangeCoins returnChange(UserMoney userMoney) {
        return coins.returnUserChange(userMoney);
    }


}
