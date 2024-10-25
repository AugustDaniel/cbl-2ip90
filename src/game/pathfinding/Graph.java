package game.pathfinding;

/**
 * Graph that contains Vertex.
 */
public class Graph {

    Vertex[][] vertices;

    /**
     * Constructor Graph.
     * @param height height of the graph
     * @param width width of the graph
     */
    public Graph(int height, int width) {
        vertices = new Vertex[height][width];
    }

    /**
     * Get Vertex.
     * @param x x
     * @param y y
     * @return vertex at given position
     */
    public Vertex getVertex(int x, int y) {
        return vertices[y][x];
    }

    /**
     * Set vertex.
     * @param vertex vertex to add to graph
     * @param x x
     * @param y y
     */
    public void setVertex(Vertex vertex, int x, int y) {
        vertices[y][x] = vertex;
    }
}
