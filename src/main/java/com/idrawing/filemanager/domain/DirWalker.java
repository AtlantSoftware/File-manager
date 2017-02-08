package com.idrawing.filemanager.domain;

import java.io.File;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;
import java.util.stream.Stream;

/**
 * Created by Sergej Povzanyuk on 07.08.2016.
 */
public class DirWalker extends RecursiveTask<Stream<File>> {

    private final File dir;
    private final String name;

    public DirWalker(File dir, String name) {
        directoryValidate(dir);
        this.name = name;
        this.dir = dir;
    }

    private void directoryValidate(File dir) {
        if (!dir.isDirectory())
            throw new IllegalArgumentException(dir + " is not a dir");
    }

    @Override
    protected Stream<File> compute() {
        List<File> all = toList(dir.listFiles());

        List<ForkJoinTask<Stream<File>>> tasks = new LinkedList<>();
        tasks.add(new FileLooker(all.stream(), name).fork());

        Stream<File> dirs = all.stream().filter(File::isDirectory);
        Stream<ForkJoinTask<Stream<File>>> dirTasks = dirs.map(subdir -> new DirWalker(subdir, name).fork());
        dirTasks.forEach(tasks::add);

        return tasks.stream().flatMap(ForkJoinTask::join);
    }

    private static <E> List<E> toList(E[] a) {
        return a == null ? Collections.emptyList() : Arrays.asList(a);
    }

}
