package vendingmachine.domain;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;

class RandomPickerTest {

    @Test
    void pick는_0부터_9까지의_랜덤한_숫자_1개를_반환한다() {
        Picker picker = new RandomPicker();
        int randomNumber = picker.pick(List.of(0, 1, 2, 3, 4, 5, 6, 7, 8, 9));
        
        assertTrue(randomNumber >= 0 && randomNumber <= 9);
    }

}
