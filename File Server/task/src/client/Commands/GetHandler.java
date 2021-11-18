package client.Commands;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Scanner;

import client.Constants;
import client.FileHandler;
import client.View;

//get command handler
public class GetHandler implements CommandHandler{
    @Override
    public void handleInput(Scanner scanner, DataOutputStream output) {
        StringBuilder sendToServerMsg = new StringBuilder();
        sendToServerMsg.append(Constants.GET_COMMAND);
        String addCommand = getFilenameOrIdCommand(scanner);
        sendToServerMsg.append(addCommand);
        try {
            output.writeUTF(sendToServerMsg.toString()); // sending message to the server
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void handleResponse(Scanner scanner, DataInputStream input) {
        int receivedMsg; // response message
        try {
            receivedMsg = input.readInt();
            if(receivedMsg == Constants.OK) {
                int size = input.readInt();
                byte[] inputByteArray = new byte[size];
                input.read(inputByteArray); //TODO: deal with result of function
                View.println(Constants.FILE_DOWNLOADED);
                String filename = scanner.nextLine();
                FileHandler fileHandler = new FileHandler(Constants.dataSourceFolder);
                while(!fileHandler.saveFile(filename, inputByteArray)) {
                    View.println(Constants.FILE_RENAME);
                    filename = scanner.nextLine();
                }
                View.println(Constants.FILE_SAVED);
            }
            if(receivedMsg == Constants.NOT_FOUND) { //not found
                View.println(Constants.FILE_LOCAL_NOT_FOUND);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
