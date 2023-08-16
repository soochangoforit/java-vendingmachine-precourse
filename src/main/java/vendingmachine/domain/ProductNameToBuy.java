package vendingmachine.domain;

import vendingmachine.util.NullOrEmptyValidator;

public class ProductNameToBuy {

    private final String name;


    private ProductNameToBuy(String name, VendingMachine vendingMachine) {
        validate(name, vendingMachine);
        this.name = name;
    }

    private void validate(String name, VendingMachine vendingMachine) {
        NullOrEmptyValidator.throwIfNullOrBlank(name);
        NullOrEmptyValidator.throwIfNull(vendingMachine);
        vendingMachine.validateExistProduct(name);
    }

    public static ProductNameToBuy from(final String name, final VendingMachine vendingMachine) {
        return new ProductNameToBuy(name, vendingMachine);
    }

    public String getProductName() {
        return name;
    }
}
