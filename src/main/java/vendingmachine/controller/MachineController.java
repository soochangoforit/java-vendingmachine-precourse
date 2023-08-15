package vendingmachine.controller;

import static vendingmachine.util.RetryUtil.read;

import java.util.List;
import java.util.Map;

import vendingmachine.domain.Coin;
import vendingmachine.domain.MachineCoins;
import vendingmachine.domain.MachineMoney;
import vendingmachine.domain.Picker;
import vendingmachine.domain.ProductInfo;
import vendingmachine.domain.Products;
import vendingmachine.domain.ProductsFactory;
import vendingmachine.domain.UserMoney;
import vendingmachine.domain.VendingMachine;
import vendingmachine.dto.MachineMoneyDto;
import vendingmachine.dto.ProductDto;
import vendingmachine.dto.ProductToBuyDto;
import vendingmachine.dto.UserMoneyDto;
import vendingmachine.view.InputView;
import vendingmachine.view.OutputView;

public class MachineController {

    private final InputView inputView = InputView.init();
    private final OutputView outputView = OutputView.init();
    private final Picker picker;

    public MachineController(final Picker picker) {
        this.picker = picker;
    }

    public void run() {
        MachineMoney machineMoney = read(this::getMachineMoney);
        MachineCoins machineCoins = Coin.generateCoins(machineMoney, picker);
        outputView.printCoins(machineCoins.getCoins());
        Products products = read(this::getProducts);
        VendingMachine vendingMachine = VendingMachine.init(products, machineCoins);
        UserMoney userMoney = read(this::getUserMoney);

        // UserMoney가 최저 상품 가격보다 작은 경우 || 모두 소진된 경우 => 잔돈 출력
        while (true) {
            if (userMoney.isLessThanMinimumPrice(vendingMachine) || vendingMachine.isSoldOut()) {
                // 투입 금액 및 잔돈(동전) 출력
                Map<Coin, Integer> userChangeCoins = vendingMachine.returnChange(userMoney);
                outputView.printUserChange(userMoney, userChangeCoins);
                break;
            }
            // 상품 구매 (해당 상품만 재고가 부족한 경우 || 해당 상품에 대한 잔액이 부족한 경우)
            read(this::buyProduct, vendingMachine, userMoney);
        }

    }

    private void buyProduct(VendingMachine vendingMachine, UserMoney userMoney) {
        ProductToBuyDto productToBuyDto = inputView.readProductToBuy(userMoney);
        ProductInfo productToBuy = vendingMachine.getProduct(productToBuyDto.getProductName());
        vendingMachine.buyProduct(productToBuy, userMoney);
    }

    private UserMoney getUserMoney() {
        UserMoneyDto userMoneyDto = inputView.readUserMoney();
        return UserMoney.from(userMoneyDto.getUserMoney());
    }

    private Products getProducts() {
        List<ProductDto> productDtos = inputView.readProducts();
        return ProductsFactory.generateProducts(productDtos);
    }

    private MachineMoney getMachineMoney() {
        MachineMoneyDto machineMoneyDto = read(inputView::readMachineMoney);
        return MachineMoney.init(machineMoneyDto.getMachineMoney());
    }
}
