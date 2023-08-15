package vendingmachine.domain;

import static java.util.stream.Collectors.toList;
import static vendingmachine.domain.ExceptionMessage.INVALID_COIN_MONEY;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

public enum Coin {
    COIN_500(500),
    COIN_100(100),
    COIN_50(50),
    COIN_10(10);

    private final int amount;

    Coin(final int amount) {
        this.amount = amount;
    }

    public static Map<Coin, Integer> generateCoins(MachineMoney machineMoney, Picker picker) {
        EnumMap<Coin, Integer> coins = new EnumMap<>(Coin.class);
        initCoins(coins);
        while (!machineMoney.isZero()) {
            int pickedMoney = pick(machineMoney, picker);
            Coin coin = findCoin(pickedMoney);
            coins.put(coin, coins.getOrDefault(coin, 0) + 1);
            machineMoney = machineMoney.subtract(pickedMoney);
        }

        return coins;
    }

    private static void initCoins(EnumMap<Coin, Integer> coins) {
        Stream.of(values())
                .forEach(coin -> coins.put(coin, 0));
    }

    private static int pick(MachineMoney machineMoney, Picker picker) {
        int pickedMoney = picker.pick(coinValues());
        boolean canBeCoin = machineMoney.isLeftMoreThan(pickedMoney);
        if (canBeCoin) {
            return pickedMoney;
        }
        return pick(machineMoney, picker);
    }

    private static Coin findCoin(int pickedMoney) {
        return Stream.of(values())
                .filter(coin -> coin.getAmount() == pickedMoney)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(INVALID_COIN_MONEY));
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
