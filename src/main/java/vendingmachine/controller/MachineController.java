package vendingmachine.controller;

import static vendingmachine.util.RetryUtil.read;

import java.util.List;
import java.util.Map;

import vendingmachine.domain.Coin;
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
        Map<Coin, Integer> coins = Coin.generateCoins(machineMoney, picker);
        outputView.printCoins(coins);
        Products products = read(this::getProducts);
        VendingMachine vendingMachine = VendingMachine.init(products, coins);
        UserMoney userMoney = read(this::getUserMoney);
        ProductInfo productToBuy = read(this::getProductToBuy, vendingMachine, userMoney);


    }

    private ProductInfo getProductToBuy(VendingMachine vendingMachine, UserMoney userMoney) {
        ProductToBuyDto productToBuyDto = inputView.readProductToBuy(userMoney);
        return vendingMachine.getProduct(productToBuyDto.getProductName());
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
