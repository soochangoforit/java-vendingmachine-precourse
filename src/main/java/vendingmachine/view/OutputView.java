package vendingmachine.view;

import java.util.Map;

import vendingmachine.domain.Coin;

public class OutputView {

    private static final String COINS_MESSAGE = "자판기가 보유한 동전";
    // 500원 - 0개
    private static final String COINS_FORMAT = "%d원 - %d개\n";

    private OutputView() {
    }

    public static OutputView init() {
        return new OutputView();
    }

    public void printExceptionMessage(String message) {
        System.out.println(message);
    }

    public void printCoins(Map<Coin, Integer> coins) {
        System.out.println(COINS_MESSAGE);
        coins.forEach((coin, count) -> System.out.printf(COINS_FORMAT, coin.getAmount(), count));
        System.out.println();
    }
}
