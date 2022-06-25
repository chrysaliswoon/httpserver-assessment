package assessment.core;

import java.io.File;

public class FileRepo {
    private File fileName;

    public FileRepo(String URL) {
        fileName = new File(URL);
    }

    public void checkFile() {
        boolean isExist = fileName.exists();
        System.out.println(fileName.getAbsolutePath() + " is exist? = " + isExist);
    }
}
