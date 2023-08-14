package vendingmachine.domain;

import java.util.List;

import camp.nextstep.edu.missionutils.Randoms;

public class RandomPicker implements Picker {
    @Override
    public int pick(List<Integer> numbers) {
        return Randoms.pickNumberInList(numbers);
    }
}
