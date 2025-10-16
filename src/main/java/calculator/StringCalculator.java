package calculator;

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

        return 0;
    }
}
