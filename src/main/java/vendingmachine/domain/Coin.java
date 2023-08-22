package vendingmachine.domain;

import static java.util.stream.Collectors.toList;
import static vendingmachine.domain.ExceptionMessage.INVALID_COIN_MONEY;
import static vendingmachine.domain.ExceptionMessage.INVALID_PICKED_MONEY;

import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

public enum Coin {
    COIN_500(500),
    COIN_100(100),
    COIN_50(50),
    COIN_10(10);

    private static final int MIN_COUNT = 0;
    private static final int MAX_RECURSIVE_COUNT = 100;
    private final int amount;

    Coin(final int amount) {
        this.amount = amount;
    }


    public static void initCoins(Map<Coin, Integer> coins) {
        Stream.of(values())
                .forEach(coin -> coins.put(coin, MIN_COUNT));
    }

    public static Coin pick(MachineMoney machineMoney, Picker picker) {
        return Stream.generate(() -> picker.pick(coinValues()))
                .limit(MAX_RECURSIVE_COUNT)
                .filter(pickedMoney -> machineMoney.isBiggerThan(pickedMoney))
                .findFirst()
                .map(pickedMoney -> findCoin(pickedMoney))
                .orElseThrow(() -> new IllegalArgumentException(INVALID_PICKED_MONEY));
    }


    public static Coin findCoin(int pickedMoney) {
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

    public int totalMoney(int count) {
        return amount * count;
    }
}
