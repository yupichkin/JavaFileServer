package server.Commands;

import java.io.DataInputStream;
import java.io.DataOutputStream;

public interface CommandHandler {
    void handleInput(String msg, DataInputStream input, DataOutputStream output);
}
