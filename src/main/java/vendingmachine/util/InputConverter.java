package vendingmachine.util;

import static java.util.stream.Collectors.toList;

import java.util.List;

public class InputConverter {
    // 콤마 구분자
    private static final String COMMA_DELIMITER = ",";

    // 콤마 + 공백 구분자
    private static final String COMMA_SPACE_DELIMITER = ", ";


    // TODO : 본격적으로 프러덕션 코드에서 사용할 메서드들을 구현
    public static int convertMachineMoney(String machineMoney) {
        return parseToInt(machineMoney);
    }


    /**
     * 콤마 구분자로 문자열을 나누어 리스트로 반환
     */
    private static List<String> split(String input) {
        // TODO : 원하는 구분자에 맞게 구현
        return List.of(input.split(COMMA_DELIMITER));
    }

    private static List<Integer> splitToInt(String input) {
        // TODO : 원하는 구분자에 맞게 구현
        return List.of(input.split(COMMA_DELIMITER)).stream()
                .map(Integer::parseInt)
                .collect(toList());
    }

    private static int parseToInt(String input) {
        return Integer.parseInt(input);
    }


}
