package vendingmachine.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.EnumMap;
import java.util.Map;

import org.junit.jupiter.api.Test;

class CoinTest {

    /**
     * public static MachineCoins generateCoins(MachineMoney machineMoney, Picker picker) {
     * EnumMap<Coin, Integer> coins = new EnumMap<>(Coin.class);
     * initCoins(coins);
     * while (!machineMoney.isZero()) {
     * int pickedMoney = pick(machineMoney, picker);
     * Coin coin = findCoin(pickedMoney);
     * addCoin(coins, coin);
     * machineMoney = machineMoney.minus(pickedMoney);
     * }
     * <p>
     * return MachineCoins.from(coins);
     * }
     * <p>
     * private static void addCoin(EnumMap<Coin, Integer> coins, Coin coin) {
     * coins.put(coin, coins.getOrDefault(coin, MIN_COUNT) + PLUS_COUNT);
     * }
     * <p>
     * private static void initCoins(EnumMap<Coin, Integer> coins) {
     * Stream.of(values())
     * .forEach(coin -> coins.put(coin, MIN_COUNT));
     * }
     * <p>
     * private static int pick(MachineMoney machineMoney, Picker picker) {
     * return Stream.generate(() -> picker.pick(coinValues()))
     * .limit(MAX_RECURSIVE_COUNT)
     * .filter(pickedMoney -> machineMoney.isBiggerThan(pickedMoney))
     * .findFirst()
     * .orElseThrow(() -> new IllegalArgumentException(INVALID_PICKED_MONEY));
     * }
     * <p>
     * <p>
     * private static Coin findCoin(int pickedMoney) {
     * return Stream.of(values())
     * .filter(coin -> coin.isSame(pickedMoney))
     * .findFirst()
     * .orElseThrow(() -> new IllegalArgumentException(INVALID_COIN_MONEY));
     * }
     * <p>
     * private boolean isSame(int pickedMoney) {
     * return amount == pickedMoney;
     * }
     * <p>
     * private static List<Integer> coinValues() {
     * return Stream.of(values())
     * .map(Coin::getAmount)
     * .collect(toList());
     * }
     */
    @Test
    void generateCoins는_picker을_이용해서_동전_반환() {
        MachineMoney machineMoney = MachineMoney.init(1000);
        Picker picker = (list) -> 500;
        Map<Coin, Integer> expectedCoins = new EnumMap<>(Coin.class) {
            {
                put(Coin.COIN_500, 2);
                put(Coin.COIN_100, 0);
                put(Coin.COIN_50, 0);
                put(Coin.COIN_10, 0);
            }
        };

        MachineCoins machineCoins = Coin.generateCoins(machineMoney, picker);


        assertThat(machineCoins.getCoins()).isEqualTo(expectedCoins);
    }


}
