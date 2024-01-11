package version.version09;


import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class TryWithResourceWithFinal {
    /**
     * java 8 에서는 try 안에 저렇게 변수를 넣으면 에러를 내뱉음
     * java 9에서는 try 밖에서 객체를 생성해서 try 안에 넣어도 에러를 뱉지 않는다.
     * 코드가 간결해지는 이점을 얻을 수 있다.
     *
     * @throws FileNotFoundException
     */
    private void readVersion8() throws FileNotFoundException {
        String file = "src/test/resources/fileTest.txt";
        String currentLine;
        BufferedReader reader = new BufferedReader(new FileReader(file));
        try (reader) {
            currentLine = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
