package client.Commands;

import client.Constants;
import client.View;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Scanner;

public class DeleteHandler implements CommandHandler {
    @Override
    public void handleInput(Scanner scanner, DataOutputStream output) {
        StringBuilder sendToServerMsg = new StringBuilder();
        sendToServerMsg.append(Constants.DELETE_COMMAND);
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
        try {
            int receivedMsg = input.readInt();
            if(receivedMsg == Constants.OK) {
                View.print(Constants.FILE_DELETED);
            }
            if(receivedMsg == Constants.NOT_FOUND) {
                View.print(Constants.FILE_SERVER_NOT_FOUND);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
