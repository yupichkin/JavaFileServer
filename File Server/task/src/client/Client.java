package client;

import client.Commands.CommandHandler;
import client.Commands.CommandsFactory;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Client {
    private static final String address = "127.0.0.1";
    private static final int port = 23456;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        CommandsFactory commandsFactory = new CommandsFactory();
        String userInput;
        try (
                Socket socket = new Socket(InetAddress.getByName(address), port);
                DataInputStream input = new DataInputStream(socket.getInputStream());
                DataOutputStream output = new DataOutputStream(socket.getOutputStream())
        ) {
            View.println(Constants.MAIN_MENU);
            userInput = scanner.nextLine();
            CommandHandler getCommand = commandsFactory.create(userInput.trim());
            if(getCommand == null) {
                View.println(Constants.NOT_CORRECT_MENU);
                return;
            }
            getCommand.handleInput(scanner, output);
            View.println(Constants.REQUEST_SENT);
            getCommand.handleResponse(scanner, input);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}




