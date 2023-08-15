package vendingmachine.dto;

public class UserMoneyDto {
    private final int userMoney;

    public UserMoneyDto(int userMoney) {
        this.userMoney = userMoney;
    }

    public int getUserMoney() {
        return userMoney;
    }
}
