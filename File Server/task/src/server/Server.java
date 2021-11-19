package server;

import server.Commands.CommandHandler;
import server.Commands.CommandsFactory;

import java.io.*;
import java.net.*;
import java.util.Locale;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {
    private static final String address = "127.0.0.1";
    private static final int port = 23456;
    private static ServerSocket server;
    public static final CommandsFactory commandsFactory = new CommandsFactory();

    public static void main(String[] args) throws IOException {
        System.out.println("Server started!");
        SerializationUtils.deserializeFileIdInfo();
        ExecutorService executorService = Executors.newCachedThreadPool();
        server = new ServerSocket(port, 50, InetAddress.getByName(address));
        while (true) {
            try {
                Socket socket = server.accept();
                executorService.submit(() -> {
                    try (DataInputStream input = new DataInputStream(socket.getInputStream());
                         DataOutputStream output = new DataOutputStream(socket.getOutputStream())) {
                        handleClient(input, output);//server socket already closed and finally need to shutsown
                    } catch (IOException ignored) { }

                });
            } catch (Exception e) {
                System.out.println("server closed");
            }
        }
    }

    private static void handleClient(DataInputStream input, DataOutputStream output) {
        try {
            String receivedMsg = input.readUTF(); // reading a message
            System.out.format("Received: %s\n", receivedMsg);

            int firstSpaceIndex = receivedMsg.indexOf(" ");
            String commandWord = firstSpaceIndex > 0 ? receivedMsg.substring(0, firstSpaceIndex) : receivedMsg; //getting first word from msg
            if (commandWord.toUpperCase(Locale.ROOT).equals(Constants.EXIT_COMMAND)) {
                server.close();
                SerializationUtils.serializeFileIdInfo(FileStorage.getInstance());
                return;
            }
            receivedMsg = receivedMsg.replace(commandWord, "").trim(); //deleting command word from msg

            CommandHandler command = commandsFactory.create(commandWord);
            if (command == null) {
                output.writeInt(Constants.NOT_FOUND);
            } else {
                command.handleInput(receivedMsg, input, output);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
