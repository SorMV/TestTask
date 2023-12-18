package ru.kazan.express.testtask.zip;

import net.lingala.zip4j.ZipFile;
import net.lingala.zip4j.exception.ZipException;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import ru.kazan.express.testtask.calculations.interfaces.CalcMaker;
import ru.kazan.express.testtask.exceptions.SequenceSizeException;
import ru.kazan.express.testtask.zip.interfaces.FileCreator;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
public class ZipperTest {

    @Value("${file.extension}")
    private String extension;

    @Value("${file.name}")
    private String fileName;

    @Value("${file.folder}")
    private String folder;

    @Value("${zip.password}")
    private String password;

    @Autowired
    Zipper zipper;

    @Mock
    FileCreator fileCreator;
    @Mock
    CalcMaker calcMaker;

    @Test
    void fillFirstNElemTest() throws SequenceSizeException, ZipException, FileNotFoundException {
        when(fileCreator.createFile()).thenReturn(new File(fileName+extension));
        when(calcMaker.getAbsValue()).thenReturn(123L);
        zipper.packFileToZip();
        String fileWithoutExtension = folder + "//" + fileName;
        String pathName = fileWithoutExtension + ".zip";
        assertTrue(new File(pathName).exists());
        ZipFile zipFile = new ZipFile(pathName);
        if (zipFile.isEncrypted())
            zipFile.setPassword(password.toCharArray());
        zipFile.extractAll(folder);
        File file = new File(folder + "//" + fileName + extension);
        Scanner in = new Scanner(file);
        in.useDelimiter("\n");
        String line = null;
        while (in.hasNext()) {
            line = in.next();
        }
        assertEquals(line, "123");
    }

    // аналогично провести и негативные тесты


}
