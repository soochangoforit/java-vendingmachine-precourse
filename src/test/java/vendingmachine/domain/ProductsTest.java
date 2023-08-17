package vendingmachine.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;

import org.junit.jupiter.api.Test;

class ProductsTest {

    @Test
    void getProduct는_상품이름을_받아_상품을_반환한다() {
        Products products = Products.init(List.of(
                Product.of("콜라", 100, 1),
                Product.of("사이다", 200, 1)
        ));

        Product product = products.getProduct("콜라");

        assertThat(product).isEqualTo(Product.of("콜라", 100, 1));
    }

    @Test
    void getProduct는_상품이름으로_찾을_수_없는_상품이름을_받으면_예외응답() {
        Products products = Products.init(List.of(
                Product.of("콜라", 100, 1),
                Product.of("사이다", 200, 1)
        ));

        assertThrows(IllegalArgumentException.class, () -> {
            products.getProduct("환타");
        });
    }

    @Test
    void getMinimumPrice는_상품들의_최소가격을_반환한다() {
        Products products = Products.init(List.of(
                Product.of("콜라", 100, 1),
                Product.of("사이다", 200, 1)
        ));

        int minimumPrice = products.getMinimumPrice();

        assertThat(minimumPrice).isEqualTo(100);
    }

    @Test
    void getMinimumPrice는_상품들이_없으면_예외응답() {
        Products products = Products.init(List.of());

        assertThrows(IllegalArgumentException.class, products::getMinimumPrice);
    }

    @Test
    void isSoldOut는_상품들이_모두_품절되면_true() {
        Products products = Products.init(List.of(
                Product.of("콜라", 100, 0),
                Product.of("사이다", 200, 0)
        ));

        boolean isSoldOut = products.isAllSoldOut();

        assertThat(isSoldOut).isTrue();
    }

    @Test
    void isSoldOut는_상품들이_모두_품절되지_않으면_false() {
        Products products = Products.init(List.of(
                Product.of("콜라", 100, 1),
                Product.of("사이다", 200, 0)
        ));

        boolean isSoldOut = products.isAllSoldOut();

        assertThat(isSoldOut).isFalse();
    }

    @Test
    void validateExistProduct는_상품이름을_받아_상품이_존재하지_않으면_예외응답() {
        Products products = Products.init(List.of(
                Product.of("콜라", 100, 1),
                Product.of("사이다", 200, 0)
        ));

        assertThrows(IllegalArgumentException.class, () -> {
            products.validateExistProduct("환타");
        });
    }

    @Test
    void validateExistProduct는_상품이름을_받아_상품이_존재하면_정상응답() {
        Products products = Products.init(List.of(
                Product.of("콜라", 100, 1),
                Product.of("사이다", 200, 0)
        ));

        assertDoesNotThrow(() -> {
            products.validateExistProduct("콜라");
        });
    }
}
