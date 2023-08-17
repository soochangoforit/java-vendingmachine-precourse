package vendingmachine.view;

import java.util.Map;

import vendingmachine.domain.Coin;
import vendingmachine.domain.UserMoney;

public class OutputView {

    private static final String COINS_MESSAGE = "자판기가 보유한 동전";
    // 500원 - 0개
    private static final String COINS_FORMAT = "%d원 - %d개\n";
    private static final String EXCEPTION_MESSAGE_FORMAT = "[ERROR] %s\n";

    private OutputView() {
    }

    public static OutputView init() {
        return new OutputView();
    }

    public void printExceptionMessage(String message) {
        System.out.printf(EXCEPTION_MESSAGE_FORMAT, message);
    }

    public void printCoins(Map<Coin, Integer> coins) {
        System.out.println(COINS_MESSAGE);
        coins.forEach((coin, count) -> System.out.printf(COINS_FORMAT, coin.getAmount(), count));
        System.out.println();
    }

    public void printUserChange(UserMoney userMoney, Map<Coin, Integer> userChangeCoins) {
        System.out.println("투입 금액: " + userMoney.getUserMoney() + "원");
        System.out.println("잔돈: ");
        userChangeCoins.forEach((coin, count) -> System.out.printf(COINS_FORMAT, coin.getAmount(), count));
    }
}
