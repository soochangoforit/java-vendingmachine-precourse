package vendingmachine.domain;

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
}
