package server.Commands;

import server.Constants;
import server.FileStorage;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

//class that handles put command
public class PutHandler implements CommandHandler {
    @Override
    public void handleInput(String msg, DataInputStream input, DataOutputStream output) {
        String[] arguments;//mock
        FileStorage fileStorage = FileStorage.getInstance();
        try {
            int size = input.readInt();
            byte[] inputByteArray = new byte[size];
            if (input.read(inputByteArray) != size) {
                output.writeInt(Constants.BAD_REQUEST); //the wrong number of bytes transferred as declared by the client
                return;
            }
            String newFilename = msg; //we assume that what follows is the filename

            int idOfNewFile = fileStorage.saveFile(newFilename, inputByteArray);
            if (idOfNewFile == 0) {
                output.writeInt(Constants.INTERNAL_SERVER_ERROR); // cannot save file by the fault of the server
            } else {
                output.writeInt(Constants.OK); // resend it to the client
                output.writeInt(idOfNewFile); // resend it to the client
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
