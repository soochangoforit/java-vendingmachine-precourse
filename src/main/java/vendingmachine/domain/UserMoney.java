package vendingmachine.domain;

import static vendingmachine.domain.ExceptionMessage.INVALID_USER_MONEY;

public class UserMoney {

    private static final int MINIMUM_MONEY = 0;
    private final int userMoney;

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
}
