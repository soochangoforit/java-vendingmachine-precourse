package vendingmachine.domain;

import static vendingmachine.domain.ExceptionMessage.INVALID_PRODUCT;
import static vendingmachine.domain.ExceptionMessage.INVALID_PRODUCT_NAME;

import java.util.List;

public class Products {

    private final List<Product> products;

    private Products(List<Product> products) {
        this.products = products;
    }

    public static Products init(List<Product> products) {
        return new Products(products);
    }

    public Product getProduct(String productName) {
        return findProductByName(productName);
    }

    private Product findProductByName(String productName) {
        return products.stream()
                .filter(product -> product.isSameName(productName))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(INVALID_PRODUCT_NAME));
    }

    public int getMinimumPrice() {
        return products.stream()
                .mapToInt(Product::getPrice)
                .min()
                .orElseThrow(() -> new IllegalArgumentException(INVALID_PRODUCT));
    }

    public boolean isSoldOut() {
        return products.stream()
                .allMatch(Product::isSoldOut);
    }

    public void validateExistProduct(String name) {
        findProductByName(name);
    }
}
