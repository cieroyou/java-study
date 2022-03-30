package example.javaio;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

/**
 * /test/resources/fileTest.txt 를 읽는 다양한 방법
 */
@TestMethodOrder(OrderAnnotation.class)
public class ReadFileTests {

    @Order(1)
    @DisplayName("Classloader-상대경로를 이용한 파일읽기")
    @Test
    public void givenFileName_whenUsingClassloader_thenFileData() throws IOException {
        // given
        String expectedData = "Hello, world!!";
        String fileName = "fileTest.txt";

        // when
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource(fileName).getFile());
        String data;
        try (InputStream inputStream = new FileInputStream(file)) {
            data = readFromInputStream(inputStream);
        }
        // then
        assertEquals(expectedData, data.trim());
    }

    @Order(2)
    @DisplayName("절대경로를 이용한 파일읽기")
    @Test
    public void givenFileNameAsAbsolutePath_whenUsingClasspath_thenFileData() throws IOException {
        // given
        String expectedData = "Hello, world!!";
        String filePath = "/fileTest.txt";
        // when
        String data;
        Class clazz = ReadFileTests.class;
        try (InputStream inputStream = clazz.getResourceAsStream(filePath)) {
            data = readFromInputStream(inputStream);
        }
        // then
        assertEquals(expectedData, data.trim());
    }

    @Order(3)
    @DisplayName("commons-io.FileUtils를 이용한 파일읽기")
    @Test
    public void givenFileName_whenUsingFileUtils_thenFileData() throws IOException {
        // given
        String expectedData = "Hello, world!!";
        String fileName = "fileTest.txt";
        // when
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource(fileName).getFile());
        String data = FileUtils.readFileToString(file,
            "UTF-8"); // FileUtils 에서 File -> InputStream -> String 을 순차적으로 진행한다.
        // then
        assertEquals(expectedData, data.trim());
    }

    @Order(4)
    @DisplayName("commons-io.IOUtils를 이용한 파일읽기")
    @Test
    public void givenFileName_whenUsingIOUtils_thenFileData() throws IOException {
        // given
        String expectedData = "Hello, world!!";
        String file = "src/test/resources/fileTest.txt";
        // when
        String data;
        try (FileInputStream fis = new FileInputStream(file)) {
            data = IOUtils.toString(fis, StandardCharsets.UTF_8);
        }
        // then
        assertEquals(expectedData, data.trim());
    }

    @Order(5)
    @DisplayName("BufferedReader를 이용한 파일읽기")
    @Test
    public void whenReadWithBufferedReader_thenCorrect() throws IOException {
        // given
        String expected_value = "Hello, world!!";
        String file = "src/test/resources/fileTest.txt";
        // when
        String currentLine;
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            currentLine = reader.readLine();
        }
        // then
        assertEquals(expected_value, currentLine);
    }

    @Order(6)
    @DisplayName("Java7(nio)을 이용한 작은용량 파일읽기")
    @Test
    public void whenReadSmallFileJava7_thenCorrect() throws IOException {
        // given
        String expected_value = "Hello, world!!";
        String file = "src/test/resources/fileTest.txt";
        // when
        Path path = Paths.get(file);
        String read = Files.readAllLines(path).get(0);
        // then
        assertEquals(expected_value, read);
    }

    @Order(7)
    @DisplayName("Java7(nio)을 이용한 대용량 파일읽기")
    @Test
    public void whenReadLargeFileJava7_thenCorrect() throws IOException {
        // given
        String expected_value = "Hello, world!!";
        String file = "src/test/resources/fileTest.txt";
        // when
        Path path = Paths.get(file);
        BufferedReader reader = Files.newBufferedReader(path);
        String line = reader.readLine();
        // then
        assertEquals(expected_value, line);
    }

    @Order(7)
    @DisplayName("Java8(nio,Files.lines())을 이용한 파일읽기")
    @Test
    public void givenFilePath_whenUsingFilesLines_thenFileData()
        throws URISyntaxException, IOException {
        // given
        String expectedData = "Hello, world!!";
        String fileName = "fileTest.txt";
        // when
        Path path = Paths.get(getClass().getClassLoader().getResource(fileName).toURI());
        String data;
        try (Stream<String> lines = Files.lines(path)) {
            data = lines.collect(Collectors.joining("\n"));
        }
        // then
        assertEquals(expectedData, data.trim());
    }

    // 구분자가 있는 파일을 읽을 때 용이함
    @Order(8)
    @DisplayName("스캐너를 이용한 파일읽기")
    @Test
    public void whenReadWithScanner_thenCorrect() throws IOException {
        // given
        String file = "src/test/resources/fileTest.txt";
        // when
        try (Scanner scanner = new Scanner(new File(file))) {
            scanner.useDelimiter(" ");

            assertTrue(scanner.hasNext());
            // then
            assertEquals("Hello,", scanner.next());
            assertEquals("world!!", scanner.next());
        }
    }

    /**
     * InputStream 을 읽어서 String으로 변환
     */
    private String readFromInputStream(InputStream inputStream) throws IOException {
        StringBuilder resultStringBuilder = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            while ((line = br.readLine()) != null) {
                resultStringBuilder.append(line).append("\n");
            }
        }
        return resultStringBuilder.toString();
    }
}
