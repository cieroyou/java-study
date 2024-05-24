package modernjava.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LoggerUtil {

    public static void log(String message){
        // 로그에 시간 정보를 [yyyy-MM-dd HH:mm:ss:SSS] 형식으로 출력
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss:SSS");
        System.out.println(STR."[\{dateTimeFormatter.format(LocalDateTime.now())}][\{Thread.currentThread().getName()}] - \{message}");

    }
}
