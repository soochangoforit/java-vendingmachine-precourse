package vendingmachine.controller;

import static vendingmachine.util.RetryUtil.read;

import java.util.List;
import java.util.Map;

import vendingmachine.domain.Coin;
import vendingmachine.domain.MachineCoins;
import vendingmachine.domain.MachineMoney;
import vendingmachine.domain.Picker;
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
        MachineCoins machineCoins = getMachineCoins(machineMoney);
        Products products = read(this::getProducts);
        VendingMachine vendingMachine = initVendingMachine(machineCoins, products);
        UserMoney userMoney = read(this::getUserMoney);
        productPerchase(vendingMachine, userMoney);
    }

    private static VendingMachine initVendingMachine(MachineCoins machineCoins, Products products) {
        return VendingMachine.init(products, machineCoins);
    }

    private MachineCoins getMachineCoins(MachineMoney machineMoney) {
        MachineCoins machineCoins = Coin.generateCoins(machineMoney, picker);
        outputView.printCoins(machineCoins.getCoins());
        return machineCoins;
    }

    private void productPerchase(VendingMachine vendingMachine, UserMoney userMoney) {
        while (!isUserMoneyLessOrProductsSoldOut(vendingMachine, userMoney)) {
            read(this::buyProduct, vendingMachine, userMoney);
        }
        processUserChange(vendingMachine, userMoney);
    }

    private boolean isUserMoneyLessOrProductsSoldOut(VendingMachine vendingMachine, UserMoney userMoney) {
        return userMoney.isLessThanMinimumPrice(vendingMachine) || vendingMachine.isAllSoldOut();
    }

    private void processUserChange(VendingMachine vendingMachine, UserMoney userMoney) {
        Map<Coin, Integer> userChangeCoins = vendingMachine.returnChange(userMoney);
        outputView.printUserChange(userMoney, userChangeCoins);
    }


    private void buyProduct(VendingMachine vendingMachine, UserMoney userMoney) {
        ProductToBuyDto productToBuyDto = inputView.readProductToBuy(userMoney);
        vendingMachine.buyProduct(productToBuyDto.getProductName(), userMoney);
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
