package server.Commands;

import server.Commands.CommandHandler;
import server.Commands.GetHandler;
import server.Commands.PutHandler;
import server.Constants;

public class CommandsFactory {
    public CommandHandler create(String commandType) {
        switch (commandType) {
            case Constants.GET_COMMAND:    return new GetHandler();
            case Constants.PUT_COMMAND:    return new PutHandler();
            case Constants.DELETE_COMMAND: return new DeleteHandler();
            default: return null;
        }
    }
}
