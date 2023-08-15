package vendingmachine.domain;

import java.util.EnumMap;
import java.util.Map;

public class MachineCoins {

    private final Map<Coin, Integer> coins;

    private MachineCoins(final Map<Coin, Integer> coins) {
        this.coins = coins;
    }

    public static MachineCoins from(final Map<Coin, Integer> coins) {
        return new MachineCoins(coins);
    }

    public Map<Coin, Integer> getCoins() {
        return coins;
    }

    public Map<Coin, Integer> changeToCoins(int userChange) {
        Map<Coin, Integer> userCoins = new EnumMap<>(Coin.class);
        for (Coin coin : Coin.values()) {
            // 해당 금액으로는 반환할 동전이 없는 경우
            if (coins.get(coin) == 0) {
                continue;
            }
            // 현재 기준이 되는 동전이 사용자 금액보다 큰 경우
            if (userChange < coin.getAmount()) {
                continue;
            }
            // 해당 동전으로 반환할 수 있는 경우
            // 가지고 있는 동전만큼만 반환
            Integer coinCount = coins.get(coin);
            int actualCount = userChange / coin.getAmount();
            int count = Math.min(coinCount, actualCount);
            userCoins.put(coin, count);
            userChange -= coin.getAmount() * count;
        }
        return userCoins;
    }
}
