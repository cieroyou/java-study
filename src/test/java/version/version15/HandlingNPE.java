package version.version15;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HandlingNPE {


    class Name {
        String firstName;

        public Name(String firstName) {
            this.firstName = firstName;
        }

        public String getFirstName() {
            return firstName;
        }
    }

    /**
     * 기존에는 name 이 null 인지, name.firstName 이 null 인지 NullPointerException 메세지에 적혀있지 않아서 디버깅을 하든, 로그를 찍어야 알 수 있었음
     * NPE 발생위치를 더 잘 알 수 있었다.
     */
    @Test
    public void handleNpeMessageTest() {
        Name name = new Name(null);
        String expected = "Cannot invoke \"String.toUpperCase()\" because the return value of \"version.version15.HandlingNPE$Name.getFirstName()\" is null";
        String npeMessage = null;
        try {
            name.getFirstName().toUpperCase();
        } catch (NullPointerException e) {
            npeMessage = e.getMessage();
        }
        assertEquals(expected, npeMessage);
    }
}
