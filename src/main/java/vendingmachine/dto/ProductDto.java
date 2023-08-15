package vendingmachine.dto;

import vendingmachine.domain.Product;

public class ProductDto {
    private final String name;
    private final int price;
    private final int quantity;

    public ProductDto(String name, int price, int quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }


    public Product toProduct() {
        return Product.of(name, price, quantity);
    }
}
