package game.pathfinding;

import java.awt.geom.Point2D;
import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

/**
 * Utility class for pathfinding inside the tileMap.
 * Using the path hashmap the next vertex in the path can be found
 * by putting in the current vertex.
 * Before pathfinding can be used the initPathfinding method has to be called first.
 * In the tileMap there needs to be a start, end and lane object.
 * The path will be from the start to the end along the lane.
 */
public final class Pathfinding {

    public static Vertex startPoint;
    public static Vertex endPoint;
    public static Map<Vertex, Vertex> path = new HashMap<>();
    public static Graph graph;

    /**
     * Finds path using a BFS.
     * Source: https://www.redblobgames.com/pathfinding/a-star/introduction.html
     */
    private static void createPath() {
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

    /**
     * Will initialize the pathfinding for the tileMap.
     * Reads the object nodeList and creates the necessary start and end points in the graph.
     * For the lane it will add the appropriate vertexes as neighbour so a path can be found.
     * Afterward, a BFS will be done to find a path.
     * @param tileHeight tileHeight
     * @param tileWidth tilWidth
     * @param mapHeight mapHeight
     * @param mapWidth mapWidth
     * @param objects nodeList with objects received read from tileMap
     */
    public static void initPathfinding(int tileHeight, 
        int tileWidth, int mapHeight, int mapWidth, NodeList objects) {
        initGraph(mapHeight, mapWidth);

        for (int i = 0; i < objects.getLength(); i++) {
            Element element = (Element) objects.item(i);
            Element content = (Element) ((Element) objects.item(i))
                .getElementsByTagName("object").item(0);

            if (element.getAttribute("name").equals("start")) {
                startPoint = graph.getVertex((int) (Double.parseDouble(content
                    .getAttribute("x")) / tileWidth),
                (int) (Double.parseDouble(content.getAttribute("y")) / tileWidth));
            }

            if (element.getAttribute("name").equals("lane")) {
                createLane(element, tileWidth, tileHeight);
            }

            if (element.getAttribute("name").equals("end")) {
                endPoint = graph.getVertex((int) (Double.parseDouble(content
                    .getAttribute("x")) / tileHeight),
                    (int) (Double.parseDouble(content.getAttribute("y")) / tileWidth));
            }
        }

        createPath();
    }

    private static void createLane(Element element, int tileWidth, int tileHeight) {
        NodeList lanes = element.getElementsByTagName("object");

        for (int j = 0; j < lanes.getLength(); j++) {
            Element content = (Element) lanes.item(j);

            int xPos = (int) (Double.parseDouble(content.getAttribute("x")) / tileWidth);
            int yPos = (int) (Double.parseDouble(content.getAttribute("y")) / tileHeight);
            int height = (int) (Double.parseDouble(content
                    .getAttribute("height")) / tileHeight);
            int width = (int) (Double.parseDouble(content
                    .getAttribute("width")) / tileWidth);

            Vertex current;
            Vertex previous = null;
            for (int y = yPos; y < yPos + height + 1; y++) {
                for (int x = xPos; x < xPos + width + 1; x++) {
                    current = graph.getVertex(x, y);

                    addVertexAsNeighbour(current, previous);
                    previous = current;
                }
            }
        }
    }

    private static void addVertexAsNeighbour(Vertex current, Vertex toAdd) {
        if (toAdd != null && current != null) {
            current.addNeighbour(toAdd);
            if (!toAdd.getNeighbours().contains(current)) {
                toAdd.addNeighbour(current);
            }
        }
    }

    private static void initGraph(int mapHeight, int mapWidth) {
        graph = new Graph(mapHeight, mapWidth);

        for (int i = 0; i < mapHeight; i++) {
            for (int j = 0; j < mapWidth; j++) {
                graph.setVertex(
                        new Vertex(new Point2D.Double(j * 32, i * 32)),
                        j,
                        i);
            }
        }
    }

    /**
     * Checks whether a vertex does not have any neighbours.
     * @param x x
     * @param y y
     * @return true if the vertex has neighbours false when not
     */
    public static boolean isLane(int x, int y) {
        Vertex v = graph.getVertex(x, y);
        return v == null || !v.getNeighbours().isEmpty();
    }
}
