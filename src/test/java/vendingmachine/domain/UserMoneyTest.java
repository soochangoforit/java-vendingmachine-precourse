package vendingmachine.domain;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class UserMoneyTest {


    @ParameterizedTest
    @ValueSource(ints = {0, 100})
    void from는_0원이상의_금액이면_예외응답하지_않는다(int userMoney) {
        assertDoesNotThrow(() -> {
            UserMoney.from(userMoney);
        });
    }

    @ParameterizedTest
    @ValueSource(ints = {-1, -2})
    void from는_0원미만의_금액이면_예외응답(int userMoney) {
        assertThrows(IllegalArgumentException.class, () -> UserMoney.from(userMoney));
    }

    @Test
    void decreaseMoney는_현재_가지고_있는_돈이_상품의_가격보다_적은_경우_예외응답() {
        UserMoney userMoney = UserMoney.from(100);

        assertThrows(IllegalArgumentException.class, () -> userMoney.decreaseMoney(200));
    }

    @Test
    void decreaseMoney는_현재_가지고_있는_돈이_상품의_가격보다_같거나_많은_경우_예외응답하지_않는다() {
        UserMoney userMoney = UserMoney.from(100);

        assertDoesNotThrow(() -> userMoney.decreaseMoney(100));
    }

    @Test
    void isLessThanMinimumPrice는_사용자_금액이_최소_상품_금액보다_적은_경우_true_응답() {
        UserMoney userMoney = UserMoney.from(100);
        VendingMachine mockedVendingMachine = mock(VendingMachine.class);
        when(mockedVendingMachine.minimumPrice()).thenReturn(200);

        boolean result = userMoney.isLessThanMinimumPrice(mockedVendingMachine);

        assertTrue(result);
    }

    @Test
    void isLessThanMinimumPrice는_사용자_금액이_최소_상품_금액보다_같거나_많은_경우_false_응답() {
        UserMoney userMoney = UserMoney.from(100);
        VendingMachine mockedVendingMachine = mock(VendingMachine.class);
        when(mockedVendingMachine.minimumPrice()).thenReturn(100);

        boolean result = userMoney.isLessThanMinimumPrice(mockedVendingMachine);

        assertFalse(result);
    }
}
