package version.version15;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

/**
 * 테스트블록 들여쓰기를 적용할 수 있다.
 * 실제로 파일 저장하여 저장된 String 확인을 한다.
 */
public class TextBlock {
    @Test
    void test() throws IOException {
        String json = """
                {
                    "name": "Sera",
                    "age": 30,
                    "job": "developer"
                }
                """;

        String json2 = """
                {\
                "name": "Sera",\
                "age": 30,\
                "job": "developer"\
                }\
                """;

        assertNotEquals(json, json2);
        assertEquals(json2, "{\"name\": \"Sera\",\"age\": 30,\"job\": \"developer\"}");

        Files.writeString(Path.of("src/test/resources/json.txt"), json);
        Files.writeString(Path.of("src/test/resources/json2.txt"), json2);
    }
}
