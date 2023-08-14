package vendingmachine;

import vendingmachine.controller.MachineController;
import vendingmachine.domain.Picker;
import vendingmachine.domain.RandomPicker;

public class Application {
    public static void main(String[] args) {
        Picker picker = new RandomPicker();
        MachineController machineController = new MachineController(picker);
        machineController.run();
    }
}
