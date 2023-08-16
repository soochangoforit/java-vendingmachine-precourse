package vendingmachine.domain;

import static vendingmachine.domain.ExceptionMessage.INVALID_MACHINE_MONEY;

public class MachineMoney {
    public static final int DIVIDABLE_MIN_MONEY = 10;
    public static final int ZERO = 0;
    private final int money;

    private MachineMoney(int money) {
        validate(money);
        this.money = money;
    }

    private void validate(int money) {
        if (!isDividable(money)) {
            throw new IllegalArgumentException(INVALID_MACHINE_MONEY);
        }
    }

    private boolean isDividable(int money) {
        return money % DIVIDABLE_MIN_MONEY == ZERO;
    }

    public static MachineMoney init(int money) {
        return new MachineMoney(money);
    }

    public boolean isBiggerThan(int pickedMoney) {
        return money >= pickedMoney;
    }

    public MachineMoney minus(int pickedMoney) {
        return new MachineMoney(money - pickedMoney);
    }

    public boolean isZero() {
        return money == ZERO;
    }
}
