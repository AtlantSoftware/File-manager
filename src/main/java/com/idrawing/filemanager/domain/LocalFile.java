package com.idrawing.filemanager.domain;

import org.apache.commons.io.FilenameUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Date;

/**
 * Created by Sergej Povzanyuk on 07.08.2016.
 */
public class LocalFile {

    private File file;

    public LocalFile(File file) {
        if (!file.isFile()) {
            throw new IllegalArgumentException("Argument is not a file");
        }
        this.file = file;
    }

    public String getName() {
        return Paths.get(file.toURI()).getFileName().toString();
    }

    public String getExtention() {
        return FilenameUtils.getExtension(file.getAbsolutePath());
    }

    public String getPathString() {
        return file.getPath();
    }

    public Path getPath() {
        return file.toPath();
    }

    public Date getCreate() {
        try {
            return new Date(Files.readAttributes(file.toPath(), BasicFileAttributes.class).creationTime().toMillis());
        } catch (IOException e) {
            return new Date();
        }
    }

    public Date getUpdated() {
        try {
            return new Date(Files.readAttributes(file.toPath(), BasicFileAttributes.class).lastModifiedTime().toMillis());
        } catch (IOException e) {
            return new Date();
        }
    }

    public Date getLastAccessDate() {
        try {
            return new Date(Files.readAttributes(file.toPath(), BasicFileAttributes.class).lastAccessTime().toMillis());
        } catch (IOException e) {
            return new Date();
        }
    }

    public String getCreator() {
        try {
            return Files.getOwner(file.toPath(), LinkOption.NOFOLLOW_LINKS).toString();
        } catch (IOException e) {
            return "unknown";
        }
    }

    public String getContentType() {
        try {
            return Files.probeContentType(file.toPath());
        } catch (IOException e) {
            return "unknown";
        }
    }

    public Double getFileSizeMegaBytes() {
        return (double) file.length() / (1024 * 1024);
    }

    public Double getFileSizeKiloBytes() {
        return (double) file.length() / 1024;
    }

    public Double getFileSizeBytes() {
        return (double) file.length();
    }

    public File getFile() {
        return file;
    }

    @Override
    public String toString() {
        return getName();
    }
}
