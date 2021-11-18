package server;

import server.Commands.CommandHandler;
import server.Commands.CommandsFactory;

import java.io.*;
import java.net.*;
import java.util.Locale;

public class Main {
    private static final String address = "127.0.0.1";
    private static final int port = 23456;

    public static final CommandsFactory commandsFactory = new CommandsFactory();

    public static void main(String[] args) throws IOException {
        System.out.println("Server started!");
        SerializationUtils.deserializeFileIdInfo();
        try (ServerSocket server = new ServerSocket(port, 50, InetAddress.getByName(address))) {
            while (true) {
                try (
                        Socket socket = server.accept(); // accepting a new client
                        DataInputStream input = new DataInputStream(socket.getInputStream());
                        DataOutputStream output = new DataOutputStream(socket.getOutputStream())
                ) {
                    String receivedMsg = input.readUTF(); // reading a message
                    System.out.format("Received: %s\n", receivedMsg);

                    int firstSpaceIndex = receivedMsg.indexOf(" ");
                    String commandWord = firstSpaceIndex > 0 ? receivedMsg.substring(0, firstSpaceIndex) : receivedMsg; //getting first word from msg
                    if(commandWord.toUpperCase(Locale.ROOT).equals(Constants.EXIT_COMMAND)) {
                        socket.close();
                        SerializationUtils.serializeFileIdInfo(FileStorage.getInstance());
                        return;
                    }
                    receivedMsg = receivedMsg.replace(commandWord, "").trim(); //deleting command word from msg

                    CommandHandler command = commandsFactory.create(commandWord);
                    if(command == null) {
                        output.writeInt(Constants.NOT_FOUND);
                    } else {
                        command.handleInput(receivedMsg, input, output);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

