package game.pathfinding;

import java.awt.geom.Point2D;
import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;

public class Pathfinding {

    public static Vertex startPoint;
    public static Vertex goal;
    public static Map<Vertex, Vertex> path;

    public static void bfs() {
        Queue<Vertex> frontier = new ArrayDeque<>();
        frontier.offer(startPoint);
        Map<Vertex, Vertex> cameFrom = new HashMap<>();
        cameFrom.put(startPoint, null);

        while (!frontier.isEmpty()) {
            Vertex current = frontier.poll();

            for (Vertex next : current.getNeighbours()) {
                if (!cameFrom.containsKey(next)) {
                    frontier.offer(next);
                    cameFrom.put(next, current);
                }
            }
        }
    }

    public static Point2D getEndPoint() {
        return new Point2D.Double(200, 100);
    }

    public static Point2D getStartPoint() {
        return new Point2D.Double(0,0);
    }
}
