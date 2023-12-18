package ru.kazan.express.testtask.zip.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.kazan.express.testtask.zip.interfaces.FileCreator;

import java.io.File;

@Service
public class FileCreatorImpl implements FileCreator {

    @Value("${file.extension}")
    private String extension;

    @Value("${file.folder}")
    private String folder;

    @Value("${file.name}")
    private String fileName;

    @Override
    public File createFile() {
        File filePath = new File(getFolder());
        filePath.mkdir();
        File file = new File(filePath + "\\" + getFileName() + getExtension());
        return file;
    }

    @Override
    public String getExtension() {
        return this.extension;
    }

    @Override
    public String getFolder() {
        return this.folder;
    }

    @Override
    public String getFileName() {
        return this.fileName;
    }

}
