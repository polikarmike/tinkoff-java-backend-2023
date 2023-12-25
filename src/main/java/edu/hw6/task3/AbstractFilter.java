package edu.hw6.task3;

import java.nio.file.DirectoryStream;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.PathMatcher;

public interface AbstractFilter extends DirectoryStream.Filter<Path> {

    boolean accept(Path entry);

    default AbstractFilter and(AbstractFilter other) {
        return entry -> this.accept(entry) && other.accept(entry);
    }

    static AbstractFilter readable() {
        return Files::isReadable;
    }

    static AbstractFilter writable() {
        return Files::isWritable;
    }

    static AbstractFilter largerThan(long size) {
        return entry -> {
            try {
                return Files.size(entry) > size;
            } catch (Exception e) {
                return false;
            }
        };
    }

    static AbstractFilter magicNumber(int... magicBytes) {
        return entry -> {
            try {
                byte[] bytes = Files.readAllBytes(entry);
                if (bytes.length >= magicBytes.length) {
                    for (int i = 0; i < magicBytes.length; i++) {
                        if ((byte) magicBytes[i] != bytes[i]) {
                            return false;
                        }
                    }
                    return true;
                }
            } catch (Exception e) {
                // handle exception
            }
            return false;
        };
    }

    static AbstractFilter globMatches(String glob) {
        PathMatcher pathMatcher = FileSystems.getDefault().getPathMatcher("glob:" + glob);
        return entry -> pathMatcher.matches(entry.getFileName());
    }

    static AbstractFilter regexContains(String regex) {
        return entry -> entry.getFileName().toString().matches(".*" + regex + ".*");
    }
}



