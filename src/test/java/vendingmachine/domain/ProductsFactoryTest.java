package vendingmachine.domain;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.Test;

import vendingmachine.dto.ProductDto;

class ProductsFactoryTest {

    @Test
    void generateProducts는_ProductDto_리스트를_받아서_Products_객체를_생성한다() {
        ProductDto cokeProduct = new ProductDto("콜라", 1000, 1);
        ProductDto ciderProduct = new ProductDto("사이다", 1000, 1);
        List<ProductDto> products = List.of(cokeProduct, ciderProduct);

        Products actualProducts = ProductsFactory.generateProducts(products);

        assertNotNull(actualProducts.getProduct("콜라"));
        assertNotNull(actualProducts.getProduct("사이다"));
    }
}
