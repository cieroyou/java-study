package version.version14;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SwichExcample {
    /**
     * java 14 에서는 switch 문을 개선하였다.
     * switch 문에서 사용할 수 있는 타입이 확장되었다.
     * switch 문에서 사용할 수 있는 문법이 확장되었다.
     */
    public String getGrade(int score) {
        return switch (score) {
            case 10 -> "A";
            case 9 -> "B";
            case 8 -> "C";
            case 7 -> "D";
            default -> "F";
        };
    }
    @Test
    void testSwitch() {
        assertEquals("A", getGrade(10));
        assertEquals("B", getGrade(9));
        assertEquals("C", getGrade(8));
        assertEquals("D", getGrade(7));
        assertEquals("F", getGrade(6));
        assertEquals("F", getGrade(0));
    }
}
