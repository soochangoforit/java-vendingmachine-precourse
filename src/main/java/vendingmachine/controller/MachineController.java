package vendingmachine.controller;

import static vendingmachine.util.RetryUtil.read;

import java.util.List;
import java.util.Map;

import vendingmachine.domain.Coin;
import vendingmachine.domain.MachineMoney;
import vendingmachine.domain.Picker;
import vendingmachine.dto.MachineMoneyDto;
import vendingmachine.dto.ProductDto;
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
        MachineMoneyDto machineMoneyDto = read(inputView::readMachineMoney);
        MachineMoney machineMoney = MachineMoney.init(machineMoneyDto.getMachineMoney());
        Map<Coin, Integer> coins = Coin.generateCoins(machineMoney, picker);
        outputView.printCoins(coins);
        List<ProductDto> productDtos = read(inputView::readProducts);

    }
}
