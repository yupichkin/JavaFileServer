package server.Commands;

import server.Constants;
import server.FileStorage;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

//class that handles get command
public class GetHandler implements CommandHandler {
    @Override
    public void handleInput(String msg, DataInputStream input, DataOutputStream output) {
        byte [] fileAsByteArray = null;
        FileStorage fileStorage = FileStorage.getInstance();
        if(msg.startsWith(Constants.BY_NAME_COMMAND)) {
            String filename = msg.replace(Constants.BY_NAME_COMMAND, "").trim();
            fileAsByteArray = fileStorage.getFileByName(filename);
        } else if(msg.startsWith(Constants.BY_ID_COMMAND)) {
            Integer id = Integer.parseInt(msg.replace(Constants.BY_ID_COMMAND, "").trim());
            fileAsByteArray = fileStorage.getFileById(id);
        }
        try {
            if (fileAsByteArray == null) {
                output.writeInt(Constants.NOT_FOUND);
                return;
            }
            output.writeInt(Constants.OK); // resend it to the client
            output.writeInt(fileAsByteArray.length);
            output.write(fileAsByteArray);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
