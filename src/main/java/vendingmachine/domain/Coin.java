package vendingmachine.domain;

import static java.util.stream.Collectors.toList;
import static vendingmachine.domain.ExceptionMessage.INVALID_COIN_MONEY;
import static vendingmachine.domain.ExceptionMessage.INVALID_PICKED_MONEY;

import java.util.EnumMap;
import java.util.List;
import java.util.stream.Stream;

public enum Coin {
    COIN_500(500),
    COIN_100(100),
    COIN_50(50),
    COIN_10(10);

    private static final int MIN_COUNT = 0;
    private static final int PLUS_COUNT = 1;

    private static final int MAX_RECURSIVE_COUNT = 100;
    private final int amount;

    Coin(final int amount) {
        this.amount = amount;
    }

    public static MachineCoins generateCoins(MachineMoney machineMoney, Picker picker) {
        EnumMap<Coin, Integer> coins = new EnumMap<>(Coin.class);
        initCoins(coins);
        while (!machineMoney.isZero()) {
            int pickedMoney = pick(machineMoney, picker);
            Coin coin = findCoin(pickedMoney);
            addCoin(coins, coin);
            machineMoney = machineMoney.minus(pickedMoney);
        }

        return MachineCoins.from(coins);
    }

    private static void addCoin(EnumMap<Coin, Integer> coins, Coin coin) {
        coins.put(coin, coins.getOrDefault(coin, MIN_COUNT) + PLUS_COUNT);
    }

    private static void initCoins(EnumMap<Coin, Integer> coins) {
        Stream.of(values())
                .forEach(coin -> coins.put(coin, MIN_COUNT));
    }

    private static int pick(MachineMoney machineMoney, Picker picker) {
        return Stream.generate(() -> picker.pick(coinValues()))
                .limit(MAX_RECURSIVE_COUNT)
                .filter(pickedMoney -> machineMoney.isBiggerThan(pickedMoney))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(INVALID_PICKED_MONEY));
    }


    private static Coin findCoin(int pickedMoney) {
        return Stream.of(values())
                .filter(coin -> coin.isSame(pickedMoney))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(INVALID_COIN_MONEY));
    }

    private boolean isSame(int pickedMoney) {
        return amount == pickedMoney;
    }

    private static List<Integer> coinValues() {
        return Stream.of(values())
                .map(Coin::getAmount)
                .collect(toList());
    }

    public int getAmount() {
        return amount;
    }

}
