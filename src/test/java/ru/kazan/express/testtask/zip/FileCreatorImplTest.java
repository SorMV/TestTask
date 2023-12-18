package ru.kazan.express.testtask.zip;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import ru.kazan.express.testtask.zip.interfaces.FileCreator;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class FileCreatorImplTest {

    @Value("${file.extension}")
    private String extension;

    @Value("${file.name}")
    private String fileName;

    @Autowired
    FileCreator fileCreator;

    @Test
    void fillFirstNElemTest() {
        File file = fileCreator.createFile();
        assertFalse(file.exists());
        assertEquals(file.getName(), fileName+extension);
    }


}
