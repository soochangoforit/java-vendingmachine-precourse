package vendingmachine.dto;

import vendingmachine.domain.Product;
import vendingmachine.util.NullOrEmptyValidator;

public class ProductDto {
    private final String name;
    private final int price;
    private final int quantity;

    public ProductDto(String name, int price, int quantity) {
        validate(name, price);
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    private void validate(String name, int price) {
        NullOrEmptyValidator.throwIfBlank(name);
        if (!isMinimumPrice(price)) {
            throw new IllegalArgumentException("가격은 100원 이상이어야 합니다.");
        }
        if (!isDividable(price)) {
            throw new IllegalArgumentException("가격은 10원 단위로 입력해주세요.");
        }
    }

    private boolean isDividable(int price) {
        return price % 10 == 0;
    }

    private static boolean isMinimumPrice(int price) {
        return price >= 100;
    }

    public Product toProduct() {
        return Product.init(name, price, quantity);
    }
}
