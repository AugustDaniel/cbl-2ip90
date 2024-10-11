package server;

import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {

    private static final List<Connection> connectionList = new ArrayList<>();
    private static final ExecutorService executor = Executors.newCachedThreadPool();

    public static void main(String[] args) {

        try {
            ServerSocket socket = new ServerSocket(29170);

            while (true) {
                Connection connection = new Connection(socket.accept());
                connectionList.add(connection);
                executor.execute(connection);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
