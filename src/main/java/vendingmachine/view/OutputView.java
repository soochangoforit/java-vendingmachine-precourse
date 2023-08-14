package vendingmachine.view;

import java.util.Map;

import vendingmachine.domain.Coin;

public class OutputView {

    private OutputView() {
    }

    public static OutputView init() {
        return new OutputView();
    }

    public void printExceptionMessage(String message) {
        System.out.println(message);
    }

    public void printCoins(Map<Coin, Integer> coins) {
        System.out.println("자판기가 보유한 동전");
        coins.forEach((coin, count) -> System.out.println(coin + " : " + count + "개"));
        System.out.println();
    }
}
