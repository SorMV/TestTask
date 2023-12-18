package ru.kazan.express.testtask.zip;


import net.lingala.zip4j.io.outputstream.ZipOutputStream;
import net.lingala.zip4j.model.ZipParameters;
import net.lingala.zip4j.model.enums.AesKeyStrength;
import net.lingala.zip4j.model.enums.CompressionLevel;
import net.lingala.zip4j.model.enums.CompressionMethod;
import net.lingala.zip4j.model.enums.EncryptionMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.kazan.express.testtask.calculations.interfaces.CalcMaker;
import ru.kazan.express.testtask.zip.interfaces.FileCreator;

import java.io.*;
import java.nio.charset.StandardCharsets;

@Service
public class Zipper
{
    @Value("${zip.password}")
    private String password;
    private static final String EXTENSION = ".zip";

    @Autowired
    private CalcMaker calcMaker;

    @Autowired
    private FileCreator fileCreator;

    public void packFileToZip() throws Exception {
        String content = calcMaker.getAbsValue().toString();
        // параметры создания архива так же можно вынести в отдельные проперти
        packFiles(content,
                CompressionMethod.DEFLATE, CompressionLevel.NORMAL,
                true, EncryptionMethod.ZIP_STANDARD, AesKeyStrength.KEY_STRENGTH_256);
    }

    public void packFiles(String content, CompressionMethod compressionMethod,
                          CompressionLevel compressionLevel, boolean isEncrypted, EncryptionMethod encryptionMethod,
                          AesKeyStrength aesKeyStrength)
    {
        File file = createFile();
        String zipName = getZipName(file);
        try(
                FileOutputStream fos = new FileOutputStream(zipName);
                ZipOutputStream zos = new ZipOutputStream(fos, password.toCharArray());
        ) {

            ZipParameters zipParameters = getZipParameters(compressionMethod, compressionLevel,
                    isEncrypted, encryptionMethod, aesKeyStrength);
            zipParameters.setFileNameInZip(file.getName());
            zos.putNextEntry(zipParameters);
            byte[] bytes = content.getBytes(StandardCharsets.UTF_8);
            zos.write(bytes, 0, bytes.length);
        }
         catch (FileNotFoundException e) {
             System.out.format("The file does not exist", file.getAbsolutePath());
         }
         catch (IOException e) {
             System.out.format("General I/O exception: {}", e.getMessage());
         }
    }

    private String getZipName(File file) {
        String extPattern = "(?<!^)[.][^.]*$";
        return file.getAbsolutePath().replaceAll(extPattern, EXTENSION);
    }

    private ZipParameters getZipParameters(CompressionMethod cMethod, CompressionLevel cLevel, boolean isEncrypted,
                                           EncryptionMethod eMethod, AesKeyStrength keyStrength) {
        ZipParameters zipParameters = new ZipParameters();
        zipParameters.setCompressionMethod(cMethod);
        zipParameters.setCompressionLevel(cLevel);
        zipParameters.setEncryptFiles(isEncrypted);
        zipParameters.setEncryptionMethod(eMethod);
        zipParameters.setAesKeyStrength(keyStrength);
        return zipParameters;
    }

    private File createFile(){
        return fileCreator.createFile();
    }



}