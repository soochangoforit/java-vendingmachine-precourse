package vendingmachine.domain;

import java.util.EnumMap;
import java.util.Map;

public class UserChangeCoins {

    private final Map<Coin, Integer> userChangeCoins;

    private UserChangeCoins(final Map<Coin, Integer> userChangeCoins) {
        this.userChangeCoins = userChangeCoins;
    }

    public UserChangeCoins() {
        this.userChangeCoins = new EnumMap<>(Coin.class);
    }

    public static UserChangeCoins init() {
        return new UserChangeCoins();
    }


    public void put(Coin coin, int count) {
        userChangeCoins.put(coin, count);
    }

    public Map<Coin, Integer> getUserCoins() {
        return userChangeCoins;
    }
}
