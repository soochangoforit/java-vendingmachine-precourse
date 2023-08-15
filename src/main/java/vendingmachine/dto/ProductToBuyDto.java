package vendingmachine.dto;

public class ProductToBuyDto {
    private final String name;

    public ProductToBuyDto(final String name) {
        this.name = name;
    }

    public String getProductName() {
        return name;
    }
}
