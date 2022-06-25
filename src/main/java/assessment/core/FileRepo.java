package assessment.core;

import java.io.File;

public class FileRepo {

    private File fileName;
    private Integer PORT;
    private String docRoot;

    public FileRepo(String docRoot, Integer PORT) {
        this.docRoot = docRoot;
        this.PORT = PORT;
        fileName = new File(docRoot);
    }

    public void checkFile() {
        boolean dirExist = fileName.isDirectory();
        boolean canRead = fileName.canRead();


        // System.out.println(fileName.getAbsolutePath() + " is exist? = " + isExist);
        if (dirExist && canRead) {
            System.out.println(("Starting server on PORT " + PORT));
        } else {
            if (dirExist == false) {
                System.out.println(("Directory does not exist"));
            } else {
                System.out.println(("File cannot be read by the server"));
            }
            System.out.println(("Shutting down server"));
            System.exit(1);
        }
    }

}
