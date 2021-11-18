package client.Commands;

//factory for instances that implements CommandHandler interface
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
