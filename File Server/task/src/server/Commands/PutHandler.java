package server.Commands;

import server.Constants;
import server.FileStorage;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class PutHandler implements CommandHandler {
    @Override
    public void handleInput(String msg, DataInputStream input, DataOutputStream output) {
        String[] arguments;//mock
        FileStorage fileStorage = FileStorage.getInstance();
        try {
            int size = input.readInt();
            byte[] inputByteArray = new byte[size];
            if (input.read(inputByteArray) != size) {
                output.writeInt(Constants.FORBIDDEN); //TODO: other code
                return;
            }
            String newFilename = msg; //we assume that what follows is the filename

            int idOfNewFile = fileStorage.saveFile(newFilename, inputByteArray);
            if (idOfNewFile == 0) {
                output.writeInt(Constants.FORBIDDEN); // resend it to the client
            } else {
                output.writeInt(Constants.OK); // resend it to the client
                output.writeInt(idOfNewFile); // resend it to the client
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
