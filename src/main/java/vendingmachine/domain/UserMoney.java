package vendingmachine.domain;

import static vendingmachine.domain.ExceptionMessage.INVALID_USER_MONEY;
import static vendingmachine.domain.ExceptionMessage.NOT_ENOUGH_MONEY;

public class UserMoney {

    private static final int MINIMUM_MONEY = 0;
    private int userMoney;

    private UserMoney(int userMoney) {
        validate(userMoney);
        this.userMoney = userMoney;
    }

    private void validate(int userMoney) {
        if (!isMinimumMoney(userMoney)) {
            throw new IllegalArgumentException(INVALID_USER_MONEY);
        }
    }

    private boolean isMinimumMoney(int userMoney) {
        return userMoney >= MINIMUM_MONEY;
    }

    public static UserMoney from(int userMoney) {
        return new UserMoney(userMoney);
    }

    public int getUserMoney() {
        return userMoney;
    }

    public boolean isLessThan(int minimumPrice) {
        return userMoney < minimumPrice;
    }

    public void decreaseMoney(int price) {
        if (isLessThan(price)) {
            throw new IllegalArgumentException(NOT_ENOUGH_MONEY);
        }
        this.userMoney -= price;
    }

    public boolean isLessThanMinimumPrice(VendingMachine vendingMachine) {
        return userMoney < vendingMachine.minimumPrice();
    }
}
