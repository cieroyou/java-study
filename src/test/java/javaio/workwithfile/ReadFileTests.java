package javaio.workwithfile;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.hamcrest.CoreMatchers;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * src/test/resources/fileTest.txt 를 읽는 다양한 방법
 */
@TestMethodOrder(OrderAnnotation.class)
public class ReadFileTests {

    String SYSTEM_DEPENDENT_FILENAME = "src/test/resources/fileTest.txt";
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
        // when
        String data;
        try (FileInputStream fis = new FileInputStream(SYSTEM_DEPENDENT_FILENAME)) {
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
        // when
        String currentLine;
        try (BufferedReader reader = new BufferedReader(new FileReader(SYSTEM_DEPENDENT_FILENAME))) {
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
        // when
        Path path = Paths.get(SYSTEM_DEPENDENT_FILENAME);
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
        // when
        Path path = Paths.get(SYSTEM_DEPENDENT_FILENAME);
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
        // when
        try (Scanner scanner = new Scanner(new File(SYSTEM_DEPENDENT_FILENAME))) {
            scanner.useDelimiter(" ");

            assertTrue(scanner.hasNext());
            // then
            assertEquals("Hello,", scanner.next());
            assertEquals("world!!", scanner.next());
        }
    }

    // 구분자가 있는 파일을 읽을 때 용이함
    @Order(9)
    @DisplayName("StreamTokenizer로 읽기")
    @Test
    public void whenReadWithStreamTokenizer_thenCorrectTokens()
            throws IOException {
        String file = "src/test/resources/fileTestTokenizer.txt";
        FileReader reader = new FileReader(file);
        StreamTokenizer tokenizer = new StreamTokenizer(reader);

        // token 1
        tokenizer.nextToken();
        assertEquals(StreamTokenizer.TT_WORD, tokenizer.ttype);
        assertEquals("Hello", tokenizer.sval);

        // token 2
        tokenizer.nextToken();
        assertEquals(StreamTokenizer.TT_NUMBER, tokenizer.ttype);
        assertEquals(1, tokenizer.nval, 0.0000001);

        // token 3
        tokenizer.nextToken();
        assertEquals(StreamTokenizer.TT_EOF, tokenizer.ttype);
        reader.close();
    }

    @Order(10)
    @DisplayName("DataInputStream으로 읽기")
    @Test
    public void whenReadWithDataInputStream_thenCorrect() throws IOException {
        // given
        String expectedValue = "Hello, world!!";
        String result = null;
        // when
        try (DataInputStream reader = new DataInputStream(new FileInputStream(SYSTEM_DEPENDENT_FILENAME))) {
            int nBytesToRead = reader.available();
            if (nBytesToRead > 0) {
                byte[] bytes = new byte[nBytesToRead];
                reader.read(bytes);
                result = new String(bytes);
            }
        }
        assertEquals(expectedValue, result);
    }

    @Order(11)
    @DisplayName("FileChannel로 읽기")
    @Test
    public void whenReadWithFileChannel_thenCorrect() throws IOException {
        // given
        String expected_value = "Hello, world!!";
        // when
        try (RandomAccessFile reader = new RandomAccessFile(SYSTEM_DEPENDENT_FILENAME, "r");
             FileChannel channel = reader.getChannel();) {
            int bufferSize = 1024;
            if (bufferSize > channel.size()) {
                bufferSize = (int) channel.size();
            }
            ByteBuffer buff = ByteBuffer.allocate(bufferSize);
            channel.read(buff);
            buff.flip();
            // then
            assertEquals(expected_value, new String(buff.array()));
        }
    }

    @Order(12)
    @DisplayName("UTF-8 EncodedFile(중국어) 읽기")
    @Test
    public void whenReadUTFEncodedFile_thenCorrect() throws IOException {
        // given
        String expected_value = "青空";
        String file = "src/test/resources/fileTestUtf8Chinese.txt";
        String currentLine;
        // when
        try (BufferedReader reader = new BufferedReader
                (new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_8))) {
            currentLine = reader.readLine();
        }
        // then
        assertEquals(expected_value, currentLine);
    }

    @Order(13)
    @DisplayName("URL로부터 Content 읽기")
    @Test
    public void givenURLName_whenUsingURL_thenFileData() throws IOException {
        // given
        String expectedData = "Example Domain";
        URL urlObject = new URL("http://www.example.com/");
        String data;
        // when
        URLConnection urlConnection = urlObject.openConnection();
        try (InputStream inputStream = urlConnection.getInputStream()) {
            data = readFromInputStream(inputStream);
        }
        // then
        assertThat(data.trim(), CoreMatchers.containsString(expectedData));
    }

    @Order(14)
    @DisplayName("JAR로 File 읽기")
    @Test
    public void givenFileName_whenUsingJarFile_thenFileData() throws IOException {
        String expectedData = "BSD License";

        Class clazz = Matchers.class;
        InputStream inputStream = clazz.getResourceAsStream("/LICENSE.txt");
        String data = readFromInputStream(inputStream);

        assertThat(data.trim(), CoreMatchers.containsString(expectedData));
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
