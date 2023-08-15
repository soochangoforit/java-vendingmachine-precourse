package vendingmachine.domain;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toList;

import java.util.List;

import vendingmachine.dto.ProductDto;

public class ProductsFactory {

    private ProductsFactory() {
    }

    public static Products generateProducts(List<ProductDto> productDtos) {
        return productDtos.stream()
                .map(ProductDto::toProduct)
                .collect(collectingAndThen(toList(), Products::init));
    }

}
