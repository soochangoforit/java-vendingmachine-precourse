package vendingmachine.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.EnumMap;
import java.util.Map;

import org.junit.jupiter.api.Test;

class MachineCoinsTest {

    @Test
    void generateCoins는_picker을_이용해서_동전_반환() {
        MachineMoney machineMoney = MachineMoney.init(1000);
        Picker picker = (list) -> 500;
        Map<Coin, Integer> expectedCoins = new EnumMap<>(Coin.class) {
            {
                put(Coin.COIN_500, 2);
                put(Coin.COIN_100, 0);
                put(Coin.COIN_50, 0);
                put(Coin.COIN_10, 0);
            }
        };

        MachineCoins machineCoins = MachineCoins.init();
        machineCoins.generateCoins(machineMoney, picker);

        assertThat(machineCoins.getCoins()).isEqualTo(expectedCoins);
    }

    @Test
    void changeToUserCoins는_사용자의_잔돈을_받아서_최소_동전의_개수로_반환한다() {
        MachineCoins machineCoins = MachineCoins.from(Map.of(
                Coin.COIN_500, 0,
                Coin.COIN_100, 1,
                Coin.COIN_50, 1,
                Coin.COIN_10, 5
        ));
        UserMoney userMoney = UserMoney.from(150);

        UserChangeCoins userChangeCoins = machineCoins.returnUserChange(userMoney);

        assertThat(userChangeCoins.getUserCoins()).contains(Map.entry(Coin.COIN_100, 1), Map.entry(Coin.COIN_50, 1));
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
        UserChangeCoins userChangeCoins = machineCoins.returnUserChange(userMoney);

        assertThat(userChangeCoins.getUserCoins()).isEmpty();
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
        UserChangeCoins userChangeCoins = machineCoins.returnUserChange(userMoney);

        assertThat(userChangeCoins.getUserCoins()).contains(Map.entry(Coin.COIN_50, 1), Map.entry(Coin.COIN_10, 1));
    }


}
