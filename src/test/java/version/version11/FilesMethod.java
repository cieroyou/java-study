package version.version11;

import org.junit.jupiter.api.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * java 9 에서는 Files 에 몇가지 메소드를 추가하였다.
 * readString() : 파일의 내용을 읽어 String 으로 리턴
 * writeString() : String 을 파일에 쓰기
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class FilesMethod {

    final String FILE_STRING = "Hello, world Sera!!";
    final Path FILE_PATH = Path.of("src/test/resources/fileTest2.txt");


    // 테스트 시작 전 해당 파일 삭제하고 파일 생성
    @BeforeEach
    public void deleteFile() throws IOException {
        Files.deleteIfExists(FILE_PATH);
        Files.writeString(FILE_PATH, FILE_STRING);
    }

    @Order(0 )
    @Test
    public void testReadString() throws IOException {
        // given
        String fileString = Files.readString(Path.of("src/test/resources/fileTest2.txt"));
        assertEquals("Hello, world Sera!!", fileString);

    }

}
