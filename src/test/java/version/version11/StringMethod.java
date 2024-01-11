package version.version11;

import org.junit.jupiter.api.Test;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class StringMethod {
    /**
     * java 9 에서는 String 에 몇가지 메소드를 추가하였다.
     * isBlank() : 문자열이 비어있는지 확인
     * lines() : 문자열을 라인별로 분리하여 Stream<String> 을 리턴
     * strip() : 문자열의 앞뒤 공백을 제거
     * stripLeading() : 문자열의 앞 공백을 제거
     * stripTrailing() : 문자열의 뒤 공백을 제거
     * repeat(int) : 문자열을 반복하여 리턴
     */
    @Test
    public void stringMethod(){
        String str = "  abc  ";
             boolean isBlank = str.isBlank();
        Stream<String> lines = str.lines();
        String strip = str.strip();
        String stripLeading = str.stripLeading();
        String stripTrailing = str.stripTrailing();
        String repeat = str.repeat(3);

        assertFalse(isBlank);
        assertEquals(lines.findFirst().get(), "  abc  ");
        assertEquals(strip, "abc");
        assertEquals(stripLeading, "abc  ");
        assertEquals(stripTrailing, "  abc");
        assertEquals(repeat, "  abc    abc    abc  ");

    }
}
