package version.version15;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StringFormattedMethod {
    /**
     * java 15 에서는 String 에 formatted method 를 추가하였다.
     * 이를 통해 String 을 포맷팅하여 리턴할 수 있다.
     */
    @Test
    public void stringFormattedMethod(){
        String old = String.format("name: %s, my-name: %s", "java", "sera");
        String v15 = "name: %s, my-name: %s".formatted("java", "sera");

        assertEquals(old, v15);
    }
}
