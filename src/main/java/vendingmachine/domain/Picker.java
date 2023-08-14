package vendingmachine.domain;

import java.util.List;

@FunctionalInterface
public interface Picker {

    int pick(List<Integer> numbers);
}
