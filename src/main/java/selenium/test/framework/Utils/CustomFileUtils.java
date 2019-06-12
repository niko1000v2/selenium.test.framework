package selenium.test.framework.Utils;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

public class CustomFileUtils {

    public static String getProjectPath(){
        return System.getProperty("user.dir");
    }

    public void deleteDirectory(File directoryPath){
        try {
            FileUtils.deleteDirectory(directoryPath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getResourcesPath (String fileName){
        return Paths.get(new File(
                getClass()
                        .getClassLoader()
                        .getResource(fileName)
                        .getFile())
                .getAbsolutePath()).toString();
    }
}
