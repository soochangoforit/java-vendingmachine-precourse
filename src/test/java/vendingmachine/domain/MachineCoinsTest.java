package vendingmachine.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Map;

import org.junit.jupiter.api.Test;

class MachineCoinsTest {

    @Test
    void changeToUserCoins는_사용자의_잔돈을_받아서_최소_동전의_개수로_반환한다() {
        MachineCoins machineCoins = MachineCoins.from(Map.of(
                Coin.COIN_500, 0,
                Coin.COIN_100, 1,
                Coin.COIN_50, 1,
                Coin.COIN_10, 5
        ));
        UserMoney userMoney = UserMoney.from(150);

        Map<Coin, Integer> userChanges = machineCoins.changeToUserCoins(userMoney);

        assertThat(userChanges).contains(Map.entry(Coin.COIN_100, 1), Map.entry(Coin.COIN_50, 1));
    }

    @Test
    void changeToUserCoins는_기계에_돈은_있지만_사용자_잔돈_반환할_만큼만의_동전이_하나도_없는_경우_거슬러줄_돈은없다() {
        MachineCoins machineCoins = MachineCoins.from(Map.of(
                Coin.COIN_500, 1,
                Coin.COIN_100, 1,
                Coin.COIN_50, 0,
                Coin.COIN_10, 0
        ));
        UserMoney userMoney = UserMoney.from(90);

        Map<Coin, Integer> userChanges = machineCoins.changeToUserCoins(userMoney);

        assertThat(userChanges).isEmpty();
    }

    @Test
    void changeToUserCoins는_사용자_잔돈만큼의_충분한_기계돈이_없다면_남은_동전이라도_다_반환한다() {
        MachineCoins machineCoins = MachineCoins.from(Map.of(
                Coin.COIN_500, 0,
                Coin.COIN_100, 0,
                Coin.COIN_50, 1,
                Coin.COIN_10, 1
        ));
        UserMoney userMoney = UserMoney.from(500);

        Map<Coin, Integer> userChanges = machineCoins.changeToUserCoins(userMoney);

        assertThat(userChanges).contains(Map.entry(Coin.COIN_50, 1), Map.entry(Coin.COIN_10, 1));
    }


}
