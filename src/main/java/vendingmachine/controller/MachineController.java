package vendingmachine.controller;

import static vendingmachine.util.RetryUtil.read;

import vendingmachine.dto.MachineMoneyDto;
import vendingmachine.view.InputView;

public class MachineController {

    private final InputView inputView = InputView.init();

    public void run() {
        // 자판기가 보유하고 있는 금액을 입력해 주세요.자판기가 보유하고 있는 금액을 입력해 주세요.
        MachineMoneyDto machineMoneyDto = read(inputView::readMachineMoney);


    }
}
