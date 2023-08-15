package vendingmachine.view;

import java.util.List;

import camp.nextstep.edu.missionutils.Console;
import vendingmachine.dto.MachineMoneyDto;
import vendingmachine.dto.ProductDto;
import vendingmachine.dto.UserMoneyDto;
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

    public List<ProductDto> readProducts() {
        System.out.println("상품명과 가격, 수량을 입력해 주세요.");
        String inputProducts = Console.readLine();
        InputValidator.validateProducts(inputProducts);
        return InputConverter.convertProducts(inputProducts);
    }

    public UserMoneyDto readUserMoney() {
        System.out.println("투입 금액을 입력해 주세요.");
        String rawUserMoney = Console.readLine();
        InputValidator.validateUserMoney(rawUserMoney);
        int userMoney = InputConverter.convertUserMoney(rawUserMoney);
        return new UserMoneyDto(userMoney);
    }
}
