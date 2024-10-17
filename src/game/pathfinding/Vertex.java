package game.pathfinding;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

public class Vertex {

    private List<Vertex> neighbours;

    public Vertex(Point2D position) {
        this.neighbours = new ArrayList<>();
    }

    public void addNeighbour(Vertex vertex) {
        neighbours.add(vertex);
    }

    public List<Vertex> getNeighbours() {
        return neighbours;
    }
}
