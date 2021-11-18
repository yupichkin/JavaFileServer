package server.Commands;

import server.Constants;
import server.FileStorage;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class DeleteHandler implements CommandHandler {
    @Override
    public void handleInput(String msg, DataInputStream input, DataOutputStream output) {
        boolean isDeleted = false;
        FileStorage fileStorage = FileStorage.getInstance();
        if(msg.startsWith(Constants.BY_NAME_COMMAND)) {
            String filename = msg.replace(Constants.BY_NAME_COMMAND, "").trim();
            isDeleted = fileStorage.deleteFileByName(filename);
        } else if(msg.startsWith(Constants.BY_ID_COMMAND)) {
            String idAsString = msg.replace(Constants.BY_ID_COMMAND, "").trim();
            Integer id = Integer.parseInt(idAsString);
            isDeleted = fileStorage.deleteFileById(id);
        }
        try {
            if (isDeleted) {
                output.writeInt(Constants.OK); //all okay
            } else {
                output.writeInt(Constants.NOT_FOUND); // not found
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
