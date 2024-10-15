package server;

import game.ClientState;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Connection implements Runnable {

    private Socket socket;
    private ObjectOutputStream output;
    private ObjectInputStream input;

    public Connection(Socket socket) throws IOException {
        this.socket = socket;
        this.output = new ObjectOutputStream(socket.getOutputStream());
        this.input = new ObjectInputStream(socket.getInputStream());
    }

    public void disconnect() {
        try {
            if (socket != null) socket.close();
            if (input != null) input.close();
            if (output != null) output.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void writeObject(Object o) throws IOException {
        output.writeObject(o);
        output.flush();
        output.reset();
    }

    private void writeBoolean(boolean bool) throws IOException {
        output.writeBoolean(bool);
        output.flush();
        output.reset();
    }

    @Override
    public void run() {
        while (true) {
            try {
                ClientState state = (ClientState) input.readObject();

                switch (state) {
                    case HOST -> hostLobby();
                    case JOIN -> joinLobby();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void joinLobby() {

    }

    private void hostLobby() {

    }
}
