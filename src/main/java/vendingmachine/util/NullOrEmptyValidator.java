package vendingmachine.util;


import java.util.Collection;
import java.util.Objects;

/**
 * 주로 Dto 혹은 Domain에서 주로 사용
 */
public final class NullOrEmptyValidator {

    private static final String NULL_MESSAGE = "NULL 이 포함되면 안 됩니다.";
    private static final String BLANK_MESSAGE = "매개변수에 Null 혹은 빈문자열 이 포함되면 안 됩니다.";
    private static final String EMPTY_MESSAGE = "비어있는 값(Null, Empty)가 있으면 안 됩니다.";

    private NullOrEmptyValidator() {
    }

    /**
     * null 체크
     *
     * @param object String, Wrapper 클래스가 대상이 된다.
     */
    public static void throwIfNull(Object object) {
        if (Objects.isNull(object)) {
            throw new IllegalArgumentException(NULL_MESSAGE);
        }
    }


    /**
     * 주로 String 값에 대해, 공백과 빈 문자열로만 이루어졌느지 검증
     *
     * @param filed 매개변수 값
     */
    public static void throwIfBlank(String filed) {
        if (filed.isBlank()) {
            throw new IllegalArgumentException(BLANK_MESSAGE);
        }
    }

    /**
     * isEmpty 체크
     * if(list == null || list.isEmpty()){} 대신 사용
     *
     * @param collection Collection을 implements한 클래스가 대상이 된다.
     */
    public static void throwIfEmpty(Collection<?> collection) {
        if (collection.isEmpty()) {
            throw new IllegalArgumentException(EMPTY_MESSAGE);
        }
    }

}
