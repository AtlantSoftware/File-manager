package com.idrawing.filemanager.domain;

import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;

import static org.junit.Assert.*;

/**
 * Created by Sergej Povzanyuk on 07.08.2016.
 */
public class LocalFileTest {

    private static final String FILE_NAME = "test1";
    private static final String FILE_EXTENTION = "cdw";
    private static final Path path = Paths.get("D:/test/" + FILE_NAME + "." + FILE_EXTENTION);
    private static LocalFile localFile;

    @Before
    public void init () throws Exception{
        localFile = new LocalFile(new File(path.toString()));
    }

    @Test
    public void getExtention() throws Exception {
        String extention = localFile.getExtention();
        assertEquals(FILE_EXTENTION, extention);
    }

    @Test
    public void getName() throws Exception {
        String name = localFile.getName();
        assertEquals(FILE_NAME, name);
    }

    @Test
    public void getPath() throws Exception {
        Path gotPath = localFile.getPath();
        assertEquals(path, gotPath);
    }

    @Test
    public void shouldReturnPathInString(){
        String path = localFile.getPathString();
        assertEquals(path, path);
    }

    @Test
    public void getCreate() throws Exception {
        Date date = localFile.getCreate();
        assertNotNull(date);
    }

    @Test
    public void shouldReturnUpdatedDate() throws Exception {
        Date date = localFile.getUpdated();
        assertNotNull(date);
    }

    @Test
    public void getLastAccessDate() throws Exception {
        Date date = localFile.getLastAccessDate();
        assertNotNull(date);
    }

    @Test
    public void getCreator() throws Exception {
        String creator = localFile.getCreator();
        assertNotNull(creator);
    }

    @Test
    public void getContentType() throws Exception {
        Date date = localFile.getUpdated();
        assertNotNull(date);
    }

    @Test
    public void shouldShowToString(){
        System.out.println(localFile.toString());
    }
}