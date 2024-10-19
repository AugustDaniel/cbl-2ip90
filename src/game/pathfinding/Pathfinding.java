package game.pathfinding;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.awt.*;
import java.awt.geom.Point2D;
import java.net.Inet4Address;
import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;

public class Pathfinding {

    public static Vertex startPoint;
    public static Vertex endPoint;
    public static Map<Vertex, Vertex> path = new HashMap<>();
    public static Graph graph;

    public static void createPath() {
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
                    path.put(current, next);
                }
            }
        }
    }

    public static void initPathfinding(int tileHeight, int tileWidth, int mapHeight, int mapWidth, NodeList objects) {
        initGraph(mapHeight, mapWidth);

        for (int i = 0; i < objects.getLength(); i++) {
            Element element = (Element) objects.item(i);

            Element content = (Element) ((Element) objects.item(i)).getElementsByTagName("object").item(0);

            if (element.getAttribute("name").equals("start")) {
                startPoint = graph.getVertex((int) (Double.parseDouble(content.getAttribute("x")) / 32), (int) (Double.parseDouble(content.getAttribute("y")) / 32));
            }

            if (element.getAttribute("name").equals("lane")) {
                int xPos = Integer.parseInt(content.getAttribute("x"))  / 32;
                int yPos = Integer.parseInt(content.getAttribute("y"))  / 32;
                int height = Integer.parseInt(content.getAttribute("height"))  / 32;
                int width = Integer.parseInt(content.getAttribute("width"))  / 32;

                for (int y = yPos; y < height; y++) {
                    for (int x = xPos; x < width; x++) {

                    }
                }
            }

            if (element.getAttribute("name").equals("end")) {
                endPoint = graph.getVertex((int) (Double.parseDouble(content.getAttribute("x")) / 32), (int) (Double.parseDouble(content.getAttribute("y")) / 32));
            }
        }
    }

    private static void initGraph(int mapHeight, int mapWidth) {
        graph = new Graph(mapHeight, mapWidth);

        for (int i = 0; i < mapHeight; i++) {
            for (int j = 0; j < mapWidth; j++) {
                graph.setVertex(
                        new Vertex(new Point2D.Double(j * 32, i * 32)),
                        i,
                        j);
            }
        }
    }

    public static Point2D getStartPoint() {
        return new Point2D.Double(0,0);
    }

    public static Point2D getEndPoint() {
        return new Point2D.Double(200, 100);
    }
}
