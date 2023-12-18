package ru.kazan.express.testtask.zip.interfaces;

import java.io.File;

public interface FileCreator {

    public File createFile();

    public String getExtension();

    public String getFolder();

    public String getFileName();

}
