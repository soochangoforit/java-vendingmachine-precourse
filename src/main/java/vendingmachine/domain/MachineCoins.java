package vendingmachine.domain;

import java.util.Arrays;
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


    public Map<Coin, Integer> changeToUserCoins(UserMoney userMoney) {
        Map<Coin, Integer> userCoins = new EnumMap<>(Coin.class);
        Arrays.stream(Coin.values())
                .filter(coin -> isCoinAvailableForChange(coin, userMoney.getUserMoney()))
                .forEach(coin -> {
                    int count = determineCoinCount(coin, userMoney.getUserMoney());
                    userCoins.put(coin, count);
                    userMoney.decreaseMoney(coin.getAmount() * count);
                });
        return userCoins;
    }

    // 동전이 하나라도 있어야 한다 && 반환할 코인이 사용자 금액보다 작아야 한다.
    private boolean isCoinAvailableForChange(Coin coin, int userChange) {
        return coins.get(coin) > 0 && userChange >= coin.getAmount();
    }

    // 가지고 있는 동전의 개수와 사용자 금액을 비교하여 반환할 최소 동전의 개수를 결정한다.
    private int determineCoinCount(Coin coin, int userChange) {
        int maxCountWithUserChange = userChange / coin.getAmount();
        return Math.min(coins.get(coin), maxCountWithUserChange);
    }

}
