package vendingmachine.domain;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class ProductTest {

    @Test
    void of는_product의_name이_null이면_예외응답() {
        assertThrows(IllegalArgumentException.class, () -> {
            Product.of(null, 100, 1);
        });
    }

    @ParameterizedTest
    @ValueSource(strings = {"", " "})
    void of는_product의_name이_빈문자열이면_예외응답(String name) {
        assertThrows(IllegalArgumentException.class, () -> {
            Product.of(name, 100, 1);
        });
    }

    @ParameterizedTest
    @ValueSource(strings = {"콜라", "사이다"})
    void of는_product의_name이_정상이면_정상응답(String name) {
        assertDoesNotThrow(() -> {
            Product.of(name, 100, 1);
        });
    }

    @ParameterizedTest
    @ValueSource(ints = {98, 99})
    void of는_product의_price가_100원미만이면_예외응답(int price) {
        assertThrows(IllegalArgumentException.class, () -> {
            Product.of("콜라", price, 1);
        });
    }

    @ParameterizedTest
    @ValueSource(ints = {100, 101})
    void of는_product의_price가_100원이상이면_정상응답(int price) {
        assertDoesNotThrow(() -> {
            Product.of("콜라", price, 1);
        });
    }


    @Test
    void isSameName는_product의_name과_같은_이름이면_true() {
        Product product = Product.of("콜라", 100, 1);
        boolean expected = product.isSameName("콜라");

        assertTrue(expected);
    }

    @Test
    void isSameName는_product의_name과_다른_이름이면_false() {
        Product product = Product.of("콜라", 100, 1);
        boolean expected = product.isSameName("사이다");

        assertFalse(expected);
    }


    @Test
    void isSoldOut는_product의_quantity가_0이면_true() {
        Product product = Product.of("콜라", 100, 0);
        boolean expected = product.isSoldOut();

        assertTrue(expected);
    }

    @Test
    void isSoldOut는_product의_quantity가_0이_아니면_false() {
        Product product = Product.of("콜라", 100, 1);
        boolean expected = product.isSoldOut();

        assertFalse(expected);
    }

    @Test
    void decreaseQuantity는_product의_quantity가_0이면_예외응답() {
        Product product = Product.of("콜라", 100, 0);

        assertThrows(IllegalArgumentException.class, product::decreaseQuantity);
    }

    @Test
    void decreaseQuantity는_product의_quantity가_0이_아니면_정상응답() {
        Product product = Product.of("콜라", 100, 1);

        assertDoesNotThrow(product::decreaseQuantity);
    }

    @Test
    void decreaseQuantity에_의해_모두_소진되면_isSoldOut는_true() {
        Product product = Product.of("콜라", 100, 1);
        product.decreaseQuantity();

        boolean expected = product.isSoldOut();

        assertTrue(expected);
    }


}
