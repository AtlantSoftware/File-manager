package com.idrawing.filemanager.api;


import org.junit.Before;
import org.junit.Test;

import java.io.File;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;
import static junit.framework.TestCase.assertTrue;

/**
 * Created by Sergej Povzanyuk on 07.08.2016.
 */
public class LocalLocalDiscManagerTest {


    private static final String DIRECTORY = "D:/files/test/";
    private static final String PATH = "D:/files/test/test.pdf";


    private DiscManager fileManager;

    @Before
    public void init() {
        fileManager = new LocalDiscManager();
    }

    @Test
    public void createFile() throws Exception {
        //given
        String path = PATH;
        File file = new File(path);

        //when
        boolean isCreated = fileManager.createFile(PATH);

        //then
        assertTrue(isCreated);
        assertTrue(file.exists());
    }

    @Test
    public void delereFile() throws Exception {
        //given
        String path = PATH;
        File file = new File(path);

        //when
        boolean isDeleted = fileManager.deleteFile(PATH);

        //then
        assertTrue(isDeleted);
        assertFalse(file.exists());
    }

    @Test
    public void shouldCleanDirectory(){
        //given
        File file = new File(DIRECTORY);

        //when
        fileManager.cleanDirectory(DIRECTORY);

        //then
        assertEquals(0, file.listFiles().length);
    }

    @Test
    public void shouldDeleteDirectory(){
        //given
        File dir = new File(DIRECTORY);

        //when
        fileManager.deleteDirectory(DIRECTORY);

        //then
        assertFalse(dir.exists());
    }

    @Test
    public void shouldCreateDirectory(){
        //given
        File dir = new File(DIRECTORY);

        //when
        fileManager.createDirectory(DIRECTORY);

        //then
        assertTrue(dir.exists());
    }


    @Test
    public void shoulGetDickSet(){
        fileManager.getDisks().forEach(disc -> System.out.println(disc.getPathString()));
    }



}