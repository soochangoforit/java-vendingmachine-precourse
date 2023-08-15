package vendingmachine.view;

import java.util.List;

import camp.nextstep.edu.missionutils.Console;
import vendingmachine.domain.UserMoney;
import vendingmachine.dto.MachineMoneyDto;
import vendingmachine.dto.ProductDto;
import vendingmachine.dto.ProductToBuyDto;
import vendingmachine.dto.UserMoneyDto;
import vendingmachine.util.InputConverter;
import vendingmachine.util.InputValidator;

public class InputView {

    private static final String INPUT_MACHINE_MONEY = "자판기가 보유하고 있는 금액을 입력해 주세요.";
    private static final String INPUT_PRODUCTS = "상품명과 가격, 수량을 입력해 주세요.";
    private static final String INPUT_USER_MONEY = "투입 금액을 입력해 주세요.";
    private static final String USER_MONEY_FORMAT = "투입 금액: %d원\n";
    private static final String INPUT_PRODUCT_TO_BUY = "구매할 상품명을 입력해 주세요.";

    private InputView() {
    }

    public static InputView init() {
        return new InputView();
    }

    public MachineMoneyDto readMachineMoney() {
        System.out.println(INPUT_MACHINE_MONEY);
        String rawMachineMoney = Console.readLine();
        printEmptyLine();
        InputValidator.validateMachineMoney(rawMachineMoney);
        int machineMoney = InputConverter.convertMachineMoney(rawMachineMoney);
        return new MachineMoneyDto(machineMoney);
    }

    private static void printEmptyLine() {
        System.out.println();
    }

    public List<ProductDto> readProducts() {
        System.out.println(INPUT_PRODUCTS);
        String inputProducts = Console.readLine();
        printEmptyLine();
        InputValidator.validateProducts(inputProducts);
        return InputConverter.convertProducts(inputProducts);
    }

    public UserMoneyDto readUserMoney() {
        System.out.println(INPUT_USER_MONEY);
        String rawUserMoney = Console.readLine();
        printEmptyLine();
        InputValidator.validateUserMoney(rawUserMoney);
        int userMoney = InputConverter.convertUserMoney(rawUserMoney);
        return new UserMoneyDto(userMoney);
    }

    public ProductToBuyDto readProductToBuy(UserMoney userMoney) {
        System.out.printf(USER_MONEY_FORMAT, userMoney.getUserMoney());
        System.out.println(INPUT_PRODUCT_TO_BUY);
        String productToBuy = Console.readLine();
        printEmptyLine();
        InputValidator.validateProductToBuy(productToBuy);
        return new ProductToBuyDto(productToBuy);
    }
}
