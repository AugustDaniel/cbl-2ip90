package server;

import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {

    private static final ConcurrentLinkedQueue<String> lobbyList = new ConcurrentLinkedQueue<>();
    private static final ExecutorService executor = Executors.newCachedThreadPool();
    public static final int PORT = 29170;
    public static final String IP_ADDR = "127.0.0.1";

    public static void main(String[] args) {
        ServerSocket socket = null;

        try {
            socket = new ServerSocket(PORT);
        } catch (Exception e) {
            System.exit(0);
        }

        while (true) {
            try {
                Connection connection = new Connection(socket.accept());
                executor.execute(connection);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static List<String> getLobbies() {
        return new ArrayList<>(lobbyList);
    }

    public static void addLobby(String name) {
        lobbyList.add(name);
    }
}
