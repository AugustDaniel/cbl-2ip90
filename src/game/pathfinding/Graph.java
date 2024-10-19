package game.pathfinding;

public class Graph {

    Vertex[][] vertices;

    public Graph(int height, int width) {
        vertices = new Vertex[height][width];
    }

    public Vertex getVertex(int x, int y) {
        return vertices[y][x];
    }

    public void setVertex(Vertex vertex, int x, int y) {
        vertices[y][x] = vertex;
    }
}
