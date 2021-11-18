package client.Commands;

import client.Constants;
import client.View;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.util.Scanner;

public interface CommandHandler {
    void handleInput(Scanner scanner, DataOutputStream output);
    void handleResponse(Scanner scanner, DataInputStream input);

    default String getFilenameOrIdCommand(Scanner scanner) {
        StringBuilder sendToServerMsg = new StringBuilder();
        View.print(Constants.NAME_OR_ID);
        String byNameOrId = scanner.nextLine();
        while (!(byNameOrId.equals("1") || byNameOrId.equals("2"))) {
            View.print(Constants.NOT_CORRECT_MENU);
            byNameOrId = scanner.nextLine();
        }
        if(byNameOrId.equals("1")) {
            sendToServerMsg.append(" ").append(Constants.BY_NAME_COMMAND);
            View.print(Constants.ENTER_FILENAME);
        } else {
            sendToServerMsg.append(" ").append(Constants.BY_ID_COMMAND);
            View.print(Constants.ENTER_ID);
        }
        String idOrFilename = scanner.nextLine();
        sendToServerMsg.append(" ").append(idOrFilename);
        return sendToServerMsg.toString();
    }
}
