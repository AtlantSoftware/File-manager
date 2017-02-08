package com.idrawing.filemanager.domain;

import com.idrawing.filemanager.api.DiscManager;
import com.idrawing.filemanager.api.LocalDiscManager;
import org.junit.Before;
import org.junit.Test;

import java.util.Queue;

/**
 * Created by Sergej Povzanyuk on 07.08.2016.
 */
public class FileSearchTtest {

    private DiscManager discManager;

    @Before
    public void init() {
        discManager = new LocalDiscManager();
    }

    @Test
    public void shouldFindAllFilesWithExtention() {
        //given


        //when
        Queue<LocalFile> files = discManager.getAllFilesByExtention("dwg", "spl7", "cdw", "frw", "spl6");

        //then

        System.out.println(files.size());

    }

}
