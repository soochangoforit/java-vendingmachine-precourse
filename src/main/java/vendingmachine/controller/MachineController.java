package vendingmachine.controller;

import static vendingmachine.util.RetryUtil.read;

import vendingmachine.domain.Picker;
import vendingmachine.dto.MachineMoneyDto;
import vendingmachine.view.InputView;

public class MachineController {

    private final InputView inputView = InputView.init();
    private final Picker picker;

    public MachineController(final Picker picker) {
        this.picker = picker;
    }

    public void run() {
        MachineMoneyDto machineMoneyDto = read(inputView::readMachineMoney);


    }
}
