package vendingmachine.domain;

import static vendingmachine.domain.ExceptionMessage.INVALID_PRODUCT_NAME;

import java.util.List;

public class Products {

    private final List<Product> products;

    public Products(List<Product> products) {
        this.products = products;
    }

    public static Products init(List<Product> products) {
        return new Products(products);
    }

    public void validateExistProduct(String productName) {
        products.stream()
                .filter(product -> product.isSameName(productName))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException(INVALID_PRODUCT_NAME));
    }
}
