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

    public ProductInfo getProduct(String productName) {
        return products.stream()
                .filter(product -> product.isSameName(productName))
                .findFirst()
                .map(product -> new ProductInfo(productName, product.getPrice()))
                .orElseThrow(() -> new IllegalArgumentException(INVALID_PRODUCT_NAME));
    }
}
