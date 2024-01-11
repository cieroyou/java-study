package version.version09;

import java.util.List;

/**
 * 인터페이스 내에서 코드를 재사용하고, 유지보수를 쉽게 하기 위한 목적으로 도입됨
 * 예를 들면, 인터페이스 내부에서 공통으로 사용하는 코드를 private method 로 정의하여 반복을 줄일 수 있음
 */
public interface InterfaceInPrivateMethod {
    void exchange();
    default void get() {
        loggingStatic("this is static method");
        exchange();
        logging("this is private method");
    }
    private void logging(String message){
        System.out.println(message);
    }
    private static void loggingStatic(String message){
        System.out.println(message);
    }
}
