package javaio.workwithfile;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CreateFileTests {

	private final String FILE_NAME = "src/test/resources/fileToCreate.txt";

	@AfterEach
	@BeforeEach
	public void cleanUpFiles() {
		File targetFile = new File(FILE_NAME);
		targetFile.delete();
	}

	@DisplayName("nio를 사용하여 파일을 생성한다.")
	@Test
	public void givenUsingNio_whenCreatingFile_thenCorrect() throws IOException {
		Path newFilePath = Paths.get(FILE_NAME);
		Files.createFile(newFilePath);
	}

	@DisplayName("File(java.io)을 사용하여 파일을 생성한다.")
	@Test
	public void givenUsingFile_whenCreatingFile_thenCorrect() throws IOException {
		File newFile = new File(FILE_NAME);
		boolean success = newFile.createNewFile();
		assertTrue(success);
	}

	@DisplayName("FileOutputStream을 사용하여 파일을 생성한다.")
	@Test
	void givenUsingFileOutputStream_whenCreatingFile_thenCorrect() throws IOException {
		try (FileOutputStream fileOutputStream = new FileOutputStream(FILE_NAME)) {
		}
	}
}
