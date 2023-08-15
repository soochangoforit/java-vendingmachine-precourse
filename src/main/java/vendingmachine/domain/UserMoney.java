package vendingmachine.domain;

public class UserMoney {
    private final int userMoney;

    private UserMoney(int userMoney) {
        validate(userMoney);
        this.userMoney = userMoney;
    }

    private void validate(int userMoney) {
        if (!isMinimumMoney(userMoney)) {
            throw new IllegalArgumentException("투입 금액은 0원 이상이어야 합니다.");
        }
    }

    private boolean isMinimumMoney(int userMoney) {
        return userMoney >= 0;
    }

    public static UserMoney from(int userMoney) {
        return new UserMoney(userMoney);
    }
}
