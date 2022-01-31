package io.github.renjujv.meor.fileio;

import io.github.renjujv.meor.database.DataBase;

import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;

    /**
     * @author HEXcube
     */
public class ProcessFile extends SimpleFileVisitor<Path> {
    private DataBase dbase = new DataBase();

    ProcessFile() throws Exception {
        dbase.create();
    }

    @Override
    public FileVisitResult visitFile(Path aFile, BasicFileAttributes aAttrs) {
        try {
            dbase.insert(aFile, aAttrs.size());
        } catch (Exception ignore) {
            System.out.println(ignore.getMessage());
        }
        return FileVisitResult.CONTINUE;
    }

    protected void finalize() {
        dbase.closeConnection();
    }
}
