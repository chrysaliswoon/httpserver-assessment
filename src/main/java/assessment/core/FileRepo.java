package assessment.core;

import java.io.File;

public class FileRepo {

    private File fileName;
    private HttpServer server;
    private Integer PORT;
    private String docRoot;

    public FileRepo(String docRoot, Integer PORT) {
        this.docRoot = docRoot;
        this.PORT = PORT;
        fileName = new File(docRoot);
    }

    public void checkFile() {
        boolean isExist = fileName.exists();
        System.out.println(fileName.getAbsolutePath() + " is exist? = " + isExist);
        if (isExist) {
            System.out.println(("Starting server"));
            server = new HttpServer(docRoot, PORT);
            server.start();
        } else {
            System.exit(1);
        }
    }

}
