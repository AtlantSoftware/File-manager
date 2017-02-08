package com.idrawing.filemanager.domain;

import java.io.File;
import java.util.concurrent.RecursiveTask;
import java.util.stream.Stream;

/**
 * Created by Sergej Povzanyuk on 07.08.2016.
 */
class FileLooker extends RecursiveTask<Stream<File>> {

    private final Stream<File> files;
    private final String name;

    public FileLooker(Stream<File> files, String name) {
        this.files = files;
        this.name = name;
    }

    @Override
    protected Stream<File> compute() {
        return files.filter((File f) -> f.getName().contains(name));
    }
}
