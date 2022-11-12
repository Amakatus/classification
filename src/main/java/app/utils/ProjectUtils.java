package app.utils;

import java.io.File;
import java.nio.file.Path;

public interface ProjectUtils {
    public static File getFile(String filePath) {
        return new File(ProjectUtils.class.getResource(filePath).getFile());
    }

    public static Path getFilePath(String filePath) {
        return getFile(filePath).toPath();
    }
}
