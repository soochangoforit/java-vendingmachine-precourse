package vendingmachine.domain;

public class ExceptionMessage {

    public static final String INVALID_MACHINE_MONEY = "자판기가 보유하고 있는 금액은 10원 단위로 입력해주세요.";
    public static final String INVALID_COIN_MONEY = "코인을 생성할 적합한 금액이 없습니다.";

    private ExceptionMessage() {
    }
}
