package vendingmachine.dto;

public class MachineMoneyDto {

    private final int machineMoney;

    public MachineMoneyDto(int machineMoney) {
        this.machineMoney = machineMoney;
    }

    public int getMachineMoney() {
        return machineMoney;
    }
}
