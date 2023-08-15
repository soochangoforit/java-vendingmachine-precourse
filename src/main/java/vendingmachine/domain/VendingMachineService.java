package vendingmachine.domain;

public class VendingMachineService {
    private final VendingMachine vendingMachine;

    private final UserMoney userMoney;

    private VendingMachineService(final VendingMachine vendingMachine, final UserMoney userMoney) {
        this.vendingMachine = vendingMachine;
        this.userMoney = userMoney;
    }

    public static VendingMachineService init(final VendingMachine vendingMachine, final UserMoney userMoney) {
        return new VendingMachineService(vendingMachine, userMoney);
    }
}
