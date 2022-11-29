package app.utils;

import java.io.File;
import java.nio.file.Path;

public interface ProjectUtils {
    static File getFile(String filePath) {
        return new File(ProjectUtils.class.getResource(filePath).getFile());
    }

    static Path getFilePath(String filePath) {
        return getFile(filePath).toPath();
    }

    static boolean stringToDouble(String string) {
        return string.equalsIgnoreCase("true");
    }

    static double roundToTwoDecimal(double value) {
        return Math.round(value * 100.0) / 100.0;
    }
}
