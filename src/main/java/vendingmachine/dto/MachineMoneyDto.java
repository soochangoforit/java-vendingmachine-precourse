package vendingmachine.dto;

import static vendingmachine.domain.ExceptionMessage.INVALID_MACHINE_MONEY;

public class MachineMoneyDto {

    private final int DIVIDABLE_MIN_MONEY = 10;
    private final int ZERO = 0;
    private final int machineMoney;

    public MachineMoneyDto(int machineMoney) {
        validate(machineMoney);
        this.machineMoney = machineMoney;
    }

    private void validate(int machineMoney) {
        if (!isDividable(machineMoney)) {
            throw new IllegalArgumentException(INVALID_MACHINE_MONEY);
        }
    }

    private boolean isDividable(int machineMoney) {
        return machineMoney % DIVIDABLE_MIN_MONEY == ZERO;
    }


}
