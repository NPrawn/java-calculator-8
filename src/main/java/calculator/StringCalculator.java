package calculator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

public class StringCalculator {

    /**
     * 문자열 덧셈 계산기의 진입점
     *  - 이후 단계에서 문자열 파싱, 검증, 계산 로직을 구현할 예정
     */
    public static float add(String input) {
        // null 혹은 빈 문자열 입력시 0 반환
        if (input == null || input.isBlank()){
            return 0;
        }

        // 문자열 내부 공백 제거 기능
        input = input.replaceAll("\\s+", "");

        // 기본 구분자 등록
        List<String> delimiters = new ArrayList<>();
        delimiters.add(",");
        delimiters.add(":");

        String numbers = input;

        // 문자열 시작 유효성 검사
        if (!input.startsWith("//") && !Character.isDigit(input.charAt(0))) {
            throw new IllegalArgumentException("입력은 '//' 또는 숫자로 시작해야 합니다.");
        }
        // 문자열 끝 유효성 검사
        if (!Character.isDigit(numbers.charAt(numbers.length() - 1))) {
            throw new IllegalArgumentException("입력은 숫자로 끝나야 합니다.");
        }

        // 커스텀 구분자 식별
        if (input.startsWith("//")) {
            input = input.replace("\\n", "\n");
            int newlineIndex = input.indexOf("\n");
            if (newlineIndex == -1) {
                throw new IllegalArgumentException("잘못된 구분자 선언 형식입니다. (\\n 누락)");
            }

            // "//"와 "\n" 사이 문자열을 전부 커스텀 구분자로 인식
            String header = input.substring(2, newlineIndex);
            numbers = input.substring(newlineIndex + 1);

            if (header.isEmpty()){
                throw new IllegalArgumentException("커스텀 구분자가 지정되지 않았습니다.");
            }
            if (header.contains(".")) {
                throw new IllegalArgumentException("온점(.)은 구분자로 사용할 수 없습니다.");
            }

            // 각 문자를 개별 커스텀 구분자로 등록
            for (char c : header.toCharArray()) {
                delimiters.add(String.valueOf(c));
            }
        }

        // 구분자 정규식 생성
        String regex = delimiters.stream()
                .map(Pattern::quote)
                .reduce((a, b) -> a + "|" + b)
                .orElse("[,:]");

        // 숫자를 구분자로 분리
        String[] tokens = numbers.split(regex);

        return 0;
    }
}
