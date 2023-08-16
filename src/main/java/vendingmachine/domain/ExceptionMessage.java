package vendingmachine.domain;

public class ExceptionMessage {

    public static final String INVALID_MACHINE_MONEY = "자판기가 보유하고 있는 금액은 10원 단위로 입력해주세요.";
    public static final String INVALID_COIN_MONEY = "코인을 생성할 적합한 금액이 없습니다.";
    public static final String INVALID_PRODUCT_PRICE = "상품의 가격은 100원 이상여야 합니다.";
    public static final String INVALID_USER_MONEY = "투입 금액은 0원 이상이어야 합니다.";
    public static final String INVALID_PRODUCT_NAME = "존재하지 않는 상품 이름입니다.";
    public static final String INVALID_PRODUCT = "상품이 존재하지 않습니다.";
    public static final String SOLD_OUT_PRODUCT = "품절된 상품입니다.";
    public static final String NOT_ENOUGH_MONEY = "해당 상품을 구매하기 위한 잔돈이 부족합니다.";
    public static final String INVALID_PICKED_MONEY = "선택된 동전으로 자판기가 소유한 돈을 표현할 수 없습니다.";


    private ExceptionMessage() {
    }
}
