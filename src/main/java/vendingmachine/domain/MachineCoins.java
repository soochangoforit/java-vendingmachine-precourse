package vendingmachine.domain;

import java.util.EnumMap;
import java.util.Map;

public class MachineCoins {

    private static final int MIN_COUNT = 0;
    private static final int PLUS_COUNT = 1;

    private final Map<Coin, Integer> coins;

    private MachineCoins(final Map<Coin, Integer> coins) {
        this.coins = coins;
    }

    private MachineCoins() {
        this.coins = new EnumMap<>(Coin.class);
        Coin.initCoins(coins);
    }

    public static MachineCoins from(final Map<Coin, Integer> coins) {
        return new MachineCoins(coins);
    }

    public static MachineCoins init() {
        return new MachineCoins();
    }

    public Map<Coin, Integer> getCoins() {
        return coins;
    }


    public MachineCoins generateCoins(MachineMoney machineMoney, Picker picker) {
        while (!machineMoney.isZero()) {
            Coin pickedCoin = Coin.pick(machineMoney, picker);
            addCoin(pickedCoin);
            machineMoney = machineMoney.minus(pickedCoin.getAmount());
        }

        return this;
    }

    private void addCoin(Coin coin) {
        coins.put(coin, coins.getOrDefault(coin, MIN_COUNT) + PLUS_COUNT);
    }

    public boolean hasCoin(Coin coin) {
        return coins.getOrDefault(coin, MIN_COUNT) > MIN_COUNT;
    }

    public int getCoinCount(Coin coin) {
        return coins.getOrDefault(coin, MIN_COUNT);
    }

    public UserChangeCoins returnUserChange(UserMoney userMoney) {
        UserChangeCoins userChangeCoins = UserChangeCoins.init();
        for (Coin coin : Coin.values()) {
            if (hasCoin(coin) && userMoney.isBiggerThan(coin.getAmount())) {
                int machineCoinCount = getCoinCount(coin);
                int minCoinCount = userMoney.getMinCoinCount(coin.getAmount());
                int count = determineCoinCount(machineCoinCount, minCoinCount);
                userChangeCoins.put(coin, count);
                int decreaseMoney = coin.totalMoney(count);
                userMoney.decreaseMoney(decreaseMoney);
            }
        }
        return userChangeCoins;
    }

    // 가지고 있는 동전의 개수와 사용자 금액을 비교하여 반환할 최소 동전의 개수를 결정한다.
    private int determineCoinCount(int machineCoinCount, int minCoinCount) {
        return Math.min(machineCoinCount, minCoinCount);
    }

}
