package com.idrawing.filemanager.api;


import com.idrawing.filemanager.domain.Disc;
import com.idrawing.filemanager.domain.LocalFile;
import com.idrawing.filemanager.domain.enums.FileFormat;

import java.util.List;

public interface FileManager {
    List<LocalFile> findByDiscsAndFileFormats(List<Disc> discs, List<FileFormat> fileFormats);
}
