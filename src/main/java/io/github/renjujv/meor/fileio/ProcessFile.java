package io.github.renjujv.meor.fileio;

import io.github.renjujv.meor.core.Database;

import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;

    /**
     * @author HEXcube
     */
public class ProcessFile extends SimpleFileVisitor<Path> {
    private final Database dbase = new Database();

    ProcessFile() {
        if(!dbase.filelistTableExists()) dbase.create();
    }

    @Override
    public FileVisitResult visitFile(Path aFile, BasicFileAttributes aAttrs) {
        try {
            dbase.insert(aFile, aAttrs.size());
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return FileVisitResult.CONTINUE;
    }
}
