package client;

import java.io.File;

public final class Constants {
    public static final int OK = 200;
    public static final int NOT_FOUND = 404;
    //codes that client expects from server

    public static final String PUT_COMMAND = "PUT";
    public static final String GET_COMMAND = "GET";
    public static final String DELETE_COMMAND = "DELETE";
    public static final String EXIT_COMMAND = "EXIT";
    public static final String BY_NAME_COMMAND = "BY_NAME";
    public static final String BY_ID_COMMAND = "BY_ID";
    //command words that client sends to server

    public static final String MAIN_MENU = "Enter action (1 - get a file, 2 - create a file, 3 - delete a file):";
    public static final String NAME_OR_ID = "Do you want to get the file by name or by id (1 - name, 2 - id):";

    public static final String ENTER_FILENAME = "Enter name of the file:";
    public static final String ENTER_ID = "Enter id:";
    public static final String ENTER_NEW_FILENAME = "Enter name of the file to be saved on server:";

    public static final String FILE_SAVED = "File saved on the hard drive!";
    public static final String FILE_DOWNLOADED = "The file was downloaded! Specify a name for it:";
    public static final String FILE_LOCAL_NOT_FOUND = "The response says that this file is not found!";
    public static final String FILE_SERVER_NOT_FOUND = "The response says that the file was not found!";
    public static final String FILE_DELETED = "The response says that the file was successfully deleted!";
    public static final String FILE_RENAME = "You already has file named that way. Please write another name for file";

    public static final String NOT_CORRECT_MENU = "Please choose correct option";
    public static final String NOT_CORRECT_FILENAME = "You don't have file named that way. Please write another filename";

    public static final String EXIT = "Good bye!";
    public static final String REQUEST_SENT = "The request was sent.";
    //string outputs for communication with user

    public static final String dataSourceFolder = System.getProperty("user.dir") + File.separator +
            //"File Server" + File.separator + "task" + File.separator + //for local testing
            "src" + File.separator + "client" + File.separator + "data";
    //default folder for file source location
}
