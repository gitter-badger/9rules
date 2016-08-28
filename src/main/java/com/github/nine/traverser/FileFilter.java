package com.github.nine.traverser;

import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;

public interface FileFilter {
    FileFilter NULL_FILTER = (path, attributes) -> true;

    boolean accept(Path path, BasicFileAttributes attributes);
}
