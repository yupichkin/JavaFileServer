package client.Commands;

import client.Constants;
import client.FileHandler;
import client.View;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class PutHandler implements CommandHandler {
    @Override
    public void handleInput(Scanner scanner, DataOutputStream output) {
        StringBuilder sendToServerMsg = new StringBuilder();
        sendToServerMsg.append(Constants.PUT_COMMAND);
        View.print(Constants.ENTER_FILENAME); //TODO: there maybe be problem
        //System.out.print("Enter filename:");
        String filename = scanner.nextLine();
        View.print(Constants.ENTER_NEW_FILENAME);
        String newFilename = scanner.nextLine();
        sendToServerMsg.append(" ").append(newFilename.isEmpty() ? filename : newFilename);
        FileHandler fileHandler = new FileHandler(Constants.dataSourceFolder);
        byte [] fileAsByteArray = fileHandler.getFileByName(filename);
        while(fileAsByteArray == null) {
            View.print(Constants.NOT_CORRECT_FILENAME);
            fileAsByteArray = fileHandler.getFileByName(filename);
        }
        //System.out.println("Sending " + filename + "(" + fileAsByteArray.length + " bytes)");
        try {
            output.writeUTF(sendToServerMsg.toString()); // sending message to the server
            output.writeInt(fileAsByteArray.length);
            output.write(fileAsByteArray);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void handleResponse(Scanner scanner, DataInputStream input) {
        int receivedMsg = 0; // response message
        try {
            receivedMsg = input.readInt();
            if(receivedMsg == Constants.OK) {
                System.out.print("Response says that file is saved! ID = ");
                int fileId = input.readInt();
                System.out.println(fileId);

            } else {
                System.out.println("The response says that this file is not found!"); //TODO: maybe other output
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
