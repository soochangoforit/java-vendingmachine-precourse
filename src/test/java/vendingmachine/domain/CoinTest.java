package vendingmachine.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.EnumMap;
import java.util.Map;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class CoinTest {

    @Test
    void initCoins는_동전_초기화() {
        Map<Coin, Integer> coins = new EnumMap<>(Coin.class);

        Coin.initCoins(coins);

        assertThat(coins).contains(Map.entry(Coin.COIN_500, 0), Map.entry(Coin.COIN_100, 0),
                Map.entry(Coin.COIN_50, 0), Map.entry(Coin.COIN_10, 0));
    }

    @Test
    void pick는_램덤으로_고른_금액이_동전으로_만들_수_있는_경우_그_동전을_반환() {
        MachineMoney machineMoney = MachineMoney.init(1000);
        Picker picker = (list) -> 500;

        Coin pickedCoin = Coin.pick(machineMoney, picker);

        assertThat(pickedCoin).isEqualTo(Coin.COIN_500);
    }

    @Test
    void pick는_램덤으로_고른_금액이_동전으로_만들_수_없는_경우_예외_발생() {
        MachineMoney machineMoney = MachineMoney.init(100);
        Picker picker = (list) -> 500;

        assertThatThrownBy(() -> Coin.pick(machineMoney, picker))
                .isInstanceOf(IllegalArgumentException.class);
    }

    private static Stream<Arguments> findCoin() {
        return Stream.of(
                Arguments.of(500, Coin.COIN_500),
                Arguments.of(100, Coin.COIN_100),
                Arguments.of(50, Coin.COIN_50),
                Arguments.of(10, Coin.COIN_10)
        );
    }

    @ParameterizedTest
    @MethodSource("findCoin")
    void findCoin은_동전_금액에_해당하는_동전_반환(int pickedMoney, Coin expectedCoin) {
        Coin coin = Coin.findCoin(pickedMoney);

        assertThat(coin).isEqualTo(expectedCoin);
    }


}
