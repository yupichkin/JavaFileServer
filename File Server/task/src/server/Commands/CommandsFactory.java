package server.Commands;

import server.Constants;

//factory for producing different class instances that implements CommandHandler
public class CommandsFactory {
    public synchronized CommandHandler create(String commandType) {
        switch (commandType) {
            case Constants.GET_COMMAND:    return new GetHandler();
            case Constants.PUT_COMMAND:    return new PutHandler();
            case Constants.DELETE_COMMAND: return new DeleteHandler();
            default: return null;
        }
    }
}
