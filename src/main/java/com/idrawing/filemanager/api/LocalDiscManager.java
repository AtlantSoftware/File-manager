package com.idrawing.filemanager.api;

import com.idrawing.filemanager.domain.DirWalker;
import com.idrawing.filemanager.domain.Disc;
import com.idrawing.filemanager.domain.enums.FileFormat;
import com.idrawing.filemanager.domain.LocalFile;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ForkJoinPool;
import java.util.stream.Collectors;

/**
 * Created by Sergej Povzanyuk on 07.08.2016.
 */
public class LocalDiscManager implements FileManager {

    public boolean createFile(String path) {
        try {
            return new File(path).createNewFile();
        } catch (IOException e) {
            return false;
        }
    }

    public boolean deleteFile(String path) {
        return new File(path).delete();
    }

    public boolean renameFile(String oldPath, String newPath) {
        return new File(oldPath).renameTo(new File(newPath));
    }

    public LocalFile getFile(String path) {
        return new LocalFile(new File(path));
    }

    public Queue<LocalFile> getAllFilesByExtention(String extention) {
        Queue<LocalFile> localFiles = new LinkedList<>();
        getDisks().forEach(disc -> localFiles.addAll(getAllFilesByExtentionForDisc(disc, extention)));
        return localFiles;
    }


    public List<LocalFile> getAllFilesByExtentionForDisc(Disc disc, String extention) {
        return new ForkJoinPool().invoke(new DirWalker(new File(disc.getPathString()), extention)).filter(File::isFile).map(LocalFile::new).collect(Collectors.toList());
    }


    public Queue<LocalFile> getAllFilesByExtention(String... extensions) {
        Queue<LocalFile> files = new LinkedList<>();
        for (String ext : extensions)
            files.addAll(getAllFilesByExtention(ext));
        return files;
    }


    public boolean cleanDirectory(String directory) {
        File[] files = new File(directory).listFiles();
        boolean isDeleted = false;
        for (File file : files) {
            if (file != null) {
                if (file.isFile()) {
                    isDeleted = file.delete();
                } else {
                    cleanDirectory(file.getPath());
                    isDeleted = file.delete();
                }
            }
        }
        return isDeleted;
    }


    public boolean deleteDirectory(String directory) {
        return new File(directory).delete();
    }


    public boolean createDirectory(String directory) {
        return new File(directory).mkdir();
    }


    public boolean createPath(String path) {
        return new File(path).mkdirs();
    }


    public List<Disc> getDisks() {
        return Arrays.stream(File.listRoots()).map(Disc::new).collect(Collectors.toList());
    }


    public List<LocalFile> getAllFilesByExtentionForPath(String path, String extention) {
        return new ForkJoinPool().invoke(new DirWalker(new File(path), extention)).filter(File::isFile).map(LocalFile::new).collect(Collectors.toList());
    }


    public Queue<LocalFile> getFilesForDiscsWithExtensions(List<Disc> selectedDiscs, List<FileFormat> selectedFileFormats) {
        Queue<LocalFile> result = new LinkedList<>();
        selectedDiscs.forEach(disc -> {selectedFileFormats.forEach(format -> result.addAll(getAllFilesByExtentionForDisc(disc, format.value())));});
        return result;
    }

    @Override
    public List<LocalFile> findByDiscsAndFileFormats(List<Disc> discs, List<FileFormat> fileFormats) {
        return null;
    }
}