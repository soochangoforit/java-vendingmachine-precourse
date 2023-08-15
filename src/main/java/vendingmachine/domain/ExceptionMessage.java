package vendingmachine.domain;

public class ExceptionMessage {

    public static final String INVALID_MACHINE_MONEY = "자판기가 보유하고 있는 금액은 10원 단위로 입력해주세요.";
    public static final String INVALID_COIN_MONEY = "코인을 생성할 적합한 금액이 없습니다.";
    public static final String INVALID_PRODUCT_PRICE = "상품의 가격은 100원 이상여야 합니다.";
    public static final String INVALID_USER_MONEY = "투입 금액은 0원 이상이어야 합니다.";

    public static final String INVALID_PRODUCT_NAME = "존재하지 않는 상품 이름입니다.";

    private ExceptionMessage() {
    }
}
