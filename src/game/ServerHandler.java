package game;

import server.Server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;

public class ServerHandler {

    private static Socket socket;
    private static ObjectInputStream input;
    private static ObjectOutputStream output;

    private static void connect() throws IOException {
        try {
            socket = new Socket();
            socket.connect(new InetSocketAddress(Server.IP_ADDR, Server.PORT), 2000);
            input = new ObjectInputStream(socket.getInputStream());
            output = new ObjectOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            disconnect();
            throw e;
        }
    }

    private static void disconnect() {
        try {
            if (socket != null && !socket.isClosed()) socket.close();
            socket = null;
            input = null;
            output = null;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static Object readObject() throws IOException, ClassNotFoundException {
        checkNullPointers();

        try {
            return input.readObject();
        } catch (Exception e) {
            disconnect();
            throw e;
        }
    }

    private static void writeObject(Object o) throws IOException {
        checkNullPointers();

        try {
            output.writeObject(o);
            output.flush();
        } catch (Exception e) {
            disconnect();
            throw e;
        }
    }

    private static void checkNullPointers() throws IOException {
        if (socket == null || input == null || output == null) {
            connect();
        }
    }
}
