package calculator;

import camp.nextstep.edu.missionutils.test.NsTest;
import org.junit.jupiter.api.Test;

import static camp.nextstep.edu.missionutils.test.Assertions.assertSimpleTest;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ApplicationTest extends NsTest {
    @Test
    void 커스텀_구분자_사용() {
        assertSimpleTest(() -> {
            run("//;\\n1");
            assertThat(output()).contains("결과 : 1");
        });
    }

    @Test
    void 예외_테스트() {
        assertSimpleTest(() ->
            assertThatThrownBy(() -> runException("-1,2,3"))
                .isInstanceOf(IllegalArgumentException.class)
        );
    }

    @Test
    void 빈문자열_입력() {
        assertSimpleTest(() -> {
            run("");
            assertThat(output()).contains("결과 : 0");
        });
    }

    @Test
    void None_입력() {
        assertSimpleTest(() -> {
            run("None");
            assertThat(output()).contains("결과 : 0");
        });
    }

    @Test
    void 사용불가능_커스텀구분자_지정() {
        assertSimpleTest(() ->
            assertThatThrownBy(() -> runException("//.\n1.2.3"))
                    .isInstanceOf(IllegalArgumentException.class)
        );
    }

    @Test
    void 잘못된_구분자_선언() {
        assertSimpleTest(() ->
            assertThatThrownBy(() -> runException("/;\n1;2;3"))
                    .isInstanceOf(IllegalArgumentException.class)
        );
    }

    @Test
    void 지정되지_않은_구분자_사용() {
        assertSimpleTest(() ->
            assertThatThrownBy(() -> runException("//;\n1/2/3"))
                    .isInstanceOf(IllegalArgumentException.class)
        );
    }

    @Test
    void 숫자가_아닌_값_포함() {
        assertSimpleTest(() ->
            assertThatThrownBy(() -> runException("1:e:2"))
                    .isInstanceOf(IllegalArgumentException.class)
        );
    }

    @Test
    void 음수_포함() {
        assertSimpleTest(() ->
                assertThatThrownBy(() -> runException("1:-2:3"))
                        .isInstanceOf(IllegalArgumentException.class)
        );
    }

    @Test
    void zero_포함() {
        assertSimpleTest(() ->
                assertThatThrownBy(() -> runException("0,5,10"))
                        .isInstanceOf(IllegalArgumentException.class)
        );
    }

    @Test
    void 연속_구분자_사용() {
        assertSimpleTest(() ->
                assertThatThrownBy(() -> runException("1,,2"))
                        .isInstanceOf(IllegalArgumentException.class)
        );
    }

    @Test
    void 구분자로_시작() {
        assertSimpleTest(() ->
                assertThatThrownBy(() -> runException(":1:2"))
                        .isInstanceOf(IllegalArgumentException.class)
        );
    }

    @Test
    void 구분자로_끝() {
        assertSimpleTest(() ->
                assertThatThrownBy(() -> runException("1:2:"))
                        .isInstanceOf(IllegalArgumentException.class)
        );
    }

    @Test
    void 정수_덧셈_오버플로우() {
        assertSimpleTest(() ->
                assertThatThrownBy(() -> runException("9223372036854775807, 500,"))
                        .isInstanceOf(ArithmeticException.class)
        );
    }

    @Test
    void 실수_덧셈_오버플로우() {
        assertSimpleTest(() ->
                assertThatThrownBy(() -> runException("340282350000000000000000000000000000000.0, 100000000000000000000000000000000000000.0"))
                        .isInstanceOf(ArithmeticException.class)
        );
    }

    @Test
    void 정수_파싱_범위초과() {
        assertSimpleTest(() ->
                assertThatThrownBy(() -> runException("9223372036854775808,2"))
                        .isInstanceOf(IllegalArgumentException.class)
        );
    }

    @Test
    void 실수_파싱_범위초과() {
        assertSimpleTest(() ->
                assertThatThrownBy(() -> runException("350282350000000000000000000000000000000.0, 500"))
                        .isInstanceOf(IllegalArgumentException.class)
        );
    }

    @Test
    void 헤더만_존재() {
        assertSimpleTest(() ->
                assertThatThrownBy(() -> runException("//;\n"))
                        .isInstanceOf(IllegalArgumentException.class)
        );
    }

    @Test
    void 정수_형식_위반() {
        assertSimpleTest(() ->
                assertThatThrownBy(() -> runException("1a,2,3"))
                        .isInstanceOf(IllegalArgumentException.class)
        );
    }

    @Test
    void 실수_형식_위반() {
        assertSimpleTest(() ->
                assertThatThrownBy(() -> runException("5..5,3"))
                        .isInstanceOf(IllegalArgumentException.class)
        );
    }

    @Test
    void 양수_표기_위반() {
        assertSimpleTest(() ->
                assertThatThrownBy(() -> runException("+1,+2,+3"))
                        .isInstanceOf(IllegalArgumentException.class)
        );
    }

    @Test
    void 지수_표기_사용() {
        assertSimpleTest(() ->
                assertThatThrownBy(() -> runException("1e3,2"))
                        .isInstanceOf(IllegalArgumentException.class)
        );
    }

    @Test
    void 커스텀_구분자_길이_위반() {
        assertSimpleTest(() ->
                assertThatThrownBy(() -> runException("//**\n1**2**3"))
                        .isInstanceOf(IllegalArgumentException.class)
        );
    }
    @Override
    public void runMain() {
        Application.main(new String[]{});
    }
}
