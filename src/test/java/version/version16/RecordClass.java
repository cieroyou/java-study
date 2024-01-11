package version.version16;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * 기본으로 private final 필드, 이름과 동일한 getter 메서드, hashCode, equals, toString 제공됨
 */
public class RecordClass {
    record FullName(String firstName, String lase) {
        public String formatter() {
            return firstName + " " + lase;
        }
    }

    @Test
    void test() {
        FullName fullName = new FullName("Sera", "Yoon");
        assertEquals("Sera Yoon", fullName.formatter());
        assertEquals("Sera", fullName.firstName());
    }
}
