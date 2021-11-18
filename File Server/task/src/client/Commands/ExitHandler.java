package client.Commands;

import client.Constants;
import client.View;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Scanner;

//exit command handler
public class ExitHandler implements CommandHandler {
    @Override
    public void handleInput(Scanner scanner, DataOutputStream output) {
        try {
            output.writeUTF(Constants.EXIT_COMMAND); // sending message to the server
        } catch (IOException e) {
            e.printStackTrace();
        }
        View.println(Constants.EXIT);
    }

    @Override
    public void handleResponse(Scanner scanner, DataInputStream input) {

    }
}
