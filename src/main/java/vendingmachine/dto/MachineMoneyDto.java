package vendingmachine.dto;

import static vendingmachine.domain.ExceptionMessage.INVALID_MACHINE_MONEY;
import static vendingmachine.domain.MachineMoney.DIVIDABLE_MIN_MONEY;
import static vendingmachine.domain.MachineMoney.ZERO;

public class MachineMoneyDto {


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


    public int getMachineMoney() {
        return machineMoney;
    }
}
