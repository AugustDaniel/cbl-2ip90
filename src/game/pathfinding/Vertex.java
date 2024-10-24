package game.pathfinding;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

/**
 * Vertex used for the graph.
 * Has a position on the screen and a list of neighbours.
 */
public class Vertex {

    private List<Vertex> neighbours;
    private Point2D position;

    /**
     * Constructor Vertex
     * @param position position of the vertex on the screen
     */
    public Vertex(Point2D position) {
        this.neighbours = new ArrayList<>();
        this.position = position;
    }

    /**
     * Add a neighbour to the neighbours list
     * @param vertex vertex to add
     */
    public void addNeighbour(Vertex vertex) {
        neighbours.add(vertex);
    }

    /**
     * Get al the neighbours
     * @return list of vertex with all the neighbours
     */
    public List<Vertex> getNeighbours() {
        return neighbours;
    }

    /**
     * Get position on the screen
     * @return the position of the vertex on the screen
     */
    public Point2D getPosition() {
        return position;
    }
}
