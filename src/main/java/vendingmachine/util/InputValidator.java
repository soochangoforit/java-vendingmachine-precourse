package vendingmachine.util;

import java.util.regex.Pattern;

/**
 * 주로 View에서 입력받은 값을 검증하는 클래스
 */
public final class InputValidator {

    // 한글만 가능, "," 구분자
    private static final Pattern KOR_COMMA_FORMAT = Pattern.compile("[가-힣]+(,[가-힣]+)*");

    // 영어만 가능, "," 구분자
    private static final Pattern ENG_COMMA_FORMAT = Pattern.compile("[A-Za-z]+(,[A-Za-z]+)*");

    // 한글만 가능, ", " 구분자
    private static final Pattern KOR_COMMA_SPACE_FORMAT = Pattern.compile("[가-힣]+(,\\s[가-힣]+)*");

    // 영어만 가능, ", " 구분자
    private static final Pattern ENG_COMMA_SPACE_FORMAT = Pattern.compile("[A-Za-z-]+(,\\s[A-Za-z-]+)*");

    // 한글, 영어 모두 가능 , "," 구분자
    private static final Pattern ENG_KOR_COMMA_FORMAT = Pattern.compile("[A-Za-z가-힣]+(,[A-Za-z가-힣]+)*");

    // 한글, 영어 모두 가능 , ", " 구분자
    private static final Pattern ENG_KOR_COMMA_SPACE_FORMAT = Pattern.compile("[A-Za-z가-힣]+(,\\s[A-Za-z가-힣]+)*");

    // 숫자만 가능, 구분자 없음
    private static final Pattern NUMBER_FORMAT = Pattern.compile("[0-9]+");

    // 숫자만 가능, "," 구분자
    private static final Pattern NUMBER_COMMA_FORMAT = Pattern.compile("[0-9]+(,[0-9]+)*");

    // 숫자만 가능, ", " 구분자
    private static final Pattern NUMBER_COMMA_SPACE_FORMAT = Pattern.compile("[0-9]+(,\\s[0-9]+)*");


    private static final String BLANK_EXCEPTION_MESSAGE = "공백은 입력할 수 없습니다.";
    private static final String FORMAT_EXCEPTION_MESSAGE = "형식에 맞지 않습니다.";

    private InputValidator() {
    }

    // TODO : 본격적으로 프로덕션 코드에 사용할 메서드들을 구현
    public static void validateMachineMoney(String machineMoney) {
        if (isBlank(machineMoney)) {
            throw new IllegalArgumentException(BLANK_EXCEPTION_MESSAGE);
        }
        if (!isRightFormat(NUMBER_FORMAT, machineMoney)) {
            throw new IllegalArgumentException(FORMAT_EXCEPTION_MESSAGE);
        }
    }

    /**
     * 공백 및 빈 문자열 체크
     * 주로 사용자 입력으로 부터의 검증이기에 빈문자열 혹은 공백 위주로 검증
     */
    private static boolean isBlank(String input) {
        return input.isBlank();
    }

    /**
     * 이상적인 형식인지 체크
     */
    private static boolean isRightFormat(Pattern pattern, String input) {
        return pattern.matcher(input).matches();
    }


}
