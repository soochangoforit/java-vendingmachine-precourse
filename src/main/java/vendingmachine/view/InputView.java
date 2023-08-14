package vendingmachine.view;

import camp.nextstep.edu.missionutils.Console;
import vendingmachine.dto.MachineMoneyDto;
import vendingmachine.util.InputConverter;
import vendingmachine.util.InputValidator;

public class InputView {

    private InputView() {
    }

    public static InputView init() {
        return new InputView();
    }

    public MachineMoneyDto readMachineMoney() {
        System.out.println("자판기가 보유하고 있는 금액을 입력해 주세요.");
        String rawMachineMoney = Console.readLine();
        InputValidator.validateMachineMoney(rawMachineMoney);
        int machineMoney = InputConverter.convertMachineMoney(rawMachineMoney);
        System.out.println();
        return new MachineMoneyDto(machineMoney);
    }
}
