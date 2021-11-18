package server.Commands;

import java.io.DataInputStream;
import java.io.DataOutputStream;

//interface for different commands that server accepts
public interface CommandHandler {
    void handleInput(String msg, DataInputStream input, DataOutputStream output);
}
