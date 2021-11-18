package client.Commands;

import client.Constants;

public class CommandsFactory {
    public CommandHandler create(String commandType) {
        switch (commandType) {
            case "1":    return new GetHandler();
            case "2":    return new PutHandler();
            case "3":    return new DeleteHandler();
            case "exit": return new ExitHandler();
            default:     return null;
        }
    }
}
