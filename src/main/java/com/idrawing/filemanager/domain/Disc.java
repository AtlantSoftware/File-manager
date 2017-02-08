package com.idrawing.filemanager.domain;

import java.io.File;

/**
 * Created by Sergej Povzanyuk on 07.08.2016.
 */
public class Disc {

    private File disk;

    public Disc(File disk) {
        this.disk = disk;
    }

    public String getPathString(){
        return disk.getPath();
    }

    @Override
    public String toString() {
        return "Disc " + getPathString();
    }
}
