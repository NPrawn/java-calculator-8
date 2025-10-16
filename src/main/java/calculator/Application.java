package calculator;

import camp.nextstep.edu.missionutils.Console;

public class Application {
    public static void main(String[] args) {
        // 문자열 입력받기
        System.out.println("덧셈할 문자열을 입력해 주세요.");
        String input = Console.readLine();

        try {
            float result = StringCalculator.add(input);
            printResult(result);
        } catch (IllegalArgumentException | ArithmeticException e) {
            System.out.println("[ERROR] " + e.getMessage());
        } catch (Exception e) {
            System.out.println("[ERROR] 예상치 못한 오류가 발생했습니다.");
        }
    }

    // 결과 출력
    private static void printResult(float result) {
        if (result % 1 == 0) { // 정수 출력
            System.out.println("결과 : " + (long) result);
        } else { // 실수 출력
            System.out.println("결과 : " + result);
        }
    }
}
