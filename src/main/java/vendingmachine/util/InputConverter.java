package vendingmachine.util;

import static java.util.stream.Collectors.toList;

import java.util.List;

import vendingmachine.dto.ProductDto;

public class InputConverter {
    // 콤마 구분자
    private static final String COMMA_DELIMITER = ",";

    // 콤마 + 공백 구분자
    private static final String COMMA_SPACE_DELIMITER = ", ";

    private static final String SEMICOLON_DELIMITER = ";";


    // TODO : 본격적으로 프러덕션 코드에서 사용할 메서드들을 구현
    public static int convertMachineMoney(String machineMoney) {
        return parseToInt(machineMoney);
    }


    /**
     * 콤마 구분자로 문자열을 나누어 리스트로 반환
     */
    private static List<String> split(String format, String input) {
        // TODO : 원하는 구분자에 맞게 구현
        return List.of(input.split(format));
    }

    private static List<Integer> splitToInt(String format, String input) {
        // TODO : 원하는 구분자에 맞게 구현
        return List.of(input.split(format)).stream()
                .map(Integer::parseInt)
                .collect(toList());
    }

    private static int parseToInt(String input) {
        return Integer.parseInt(input);
    }


    public static List<ProductDto> convertProducts(String inputProducts) {
        return split(SEMICOLON_DELIMITER, inputProducts).stream()
                .map(InputConverter::convertToProductDto)
                .collect(toList());
    }

    private static ProductDto convertToProductDto(String productInfoStr) {
        List<String> productInfo = split(COMMA_DELIMITER, getSubstring(productInfoStr));
        String name = productInfo.get(0);
        int price = parseToInt(productInfo.get(1));
        int quantity = parseToInt(productInfo.get(2));
        return new ProductDto(name, price, quantity);
    }

    private static String getSubstring(String productInfoStr) {
        return productInfoStr.substring(1, productInfoStr.length() - 1);
    }

    public static int convertUserMoney(String rawUserMoney) {
        return parseToInt(rawUserMoney);
    }
}
