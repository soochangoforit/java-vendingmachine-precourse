package vendingmachine.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class MachineMoneyTest {


    @ParameterizedTest
    @ValueSource(ints = {1, 11, 101})
    void init는_10원단위로_만들어진_금액이_아니면_예외응답(int amount) {
        assertThrows(IllegalArgumentException.class, () -> {
            MachineMoney.init(amount);
        });
    }

    @ParameterizedTest
    @ValueSource(ints = {10, 20, 30})
    void init는_10원단위로_만들어진_금액이면_예외응답하지_않는다(int amount) {
        assertDoesNotThrow(() -> {
            MachineMoney.init(amount);
        });
    }


    @Test
    void isBiggerThan는_현재보유하고_있는_금액이_선택된_금액보다_작은_경우_false응답() {
        MachineMoney machineMoney = MachineMoney.init(100);

        boolean result = machineMoney.isBiggerThan(200);

        assertFalse(result);
    }

    @Test
    void isBiggerThan는_현재보유하고_있는_금액이_선택된_금액보다_같거나_큰_경우_true응답() {
        MachineMoney machineMoney = MachineMoney.init(100);

        boolean result = machineMoney.isBiggerThan(100);

        assertTrue(result);
    }

    @Test
    void minus는_현재보유하고_있는_금액에서_선택된_금액을_뺀_금액을_반환한다() {
        MachineMoney machineMoney = MachineMoney.init(100);

        MachineMoney result = machineMoney.minus(50);

        assertThat(result).isEqualTo(MachineMoney.init(50));
    }

    @Test
    void isZero는_현재보유하고_있는_금액이_0원이면_true를_반환한다() {
        MachineMoney machineMoney = MachineMoney.init(0);

        boolean result = machineMoney.isZero();

        assertTrue(result);
    }

    @Test
    void isZero는_현재보유하고_있는_금액이_0원이_아니면_false를_반환한다() {
        MachineMoney machineMoney = MachineMoney.init(100);

        boolean result = machineMoney.isZero();

        assertFalse(result);
    }
}
