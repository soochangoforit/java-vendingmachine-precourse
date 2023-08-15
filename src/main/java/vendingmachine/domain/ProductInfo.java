package vendingmachine.domain;

public class ProductInfo {
    private final String productName;
    private final int price;

    public ProductInfo(final String productName, final int price) {
        this.productName = productName;
        this.price = price;
    }

    public int getPrice() {
        return price;
    }

    public String getProductName() {
        return productName;
    }
}
