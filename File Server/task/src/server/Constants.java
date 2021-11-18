package server;

import java.io.File;

public class Constants {
    public static final int OK = 200;
    public static final int BAD_REQUEST = 400;
    public static final int NOT_FOUND = 404;
    public static final int INTERNAL_SERVER_ERROR = 500;
    //possible http codes for server

    public static final String PUT_COMMAND = "PUT";
    public static final String GET_COMMAND = "GET";
    public static final String DELETE_COMMAND = "DELETE";
    public static final String EXIT_COMMAND = "EXIT";
    public static final String BY_NAME_COMMAND = "BY_NAME";
    public static final String BY_ID_COMMAND = "BY_ID";
    //command words that server accepts

    public static final String defaultFolderForData = System.getProperty("user.dir") + File.separator +
            //"File Server" + File.separator + "task" + File.separator + //for local testing
            "src" + File.separator + "server" + File.separator + "data";
    //default folder for file storage

    public static final String filenameForSerialization = "idFiles.data";
    //filename for serialized FileStorage instance
}
