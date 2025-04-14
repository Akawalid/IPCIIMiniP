package Model.Position;

import Model.Farm;

import java.awt.*;
import java.util.*;
import java.util.List;

public class FindPath {
    public static Farm farm;

    public static ArrayDeque<Spot> findPath(Spot start, Spot dest) {
        // If the destination is not traversable, find the closest traversable spot
        if (!dest.isTraversable()) {
            dest = findClosestTraversableSpot(dest);
            if (dest == null) {
                // No traversable spot found near the destination
                return new ArrayDeque<>();
            }
        }

        // Priority queue for open spots, sorted by total cost (f = g + h)
        PriorityQueue<Node> openSet = new PriorityQueue<>(Comparator.comparingInt(n -> n.f));

        // Map to store the cost to reach each spot
        Map<Spot, Integer> gScore = new HashMap<>();
        gScore.put(start, 0);

        // Map to store the estimated total cost from start to dest through each spot
        Map<Spot, Integer> fScore = new HashMap<>();
        fScore.put(start, heuristic(start, dest));

        // Map to keep track of the path
        Map<Spot, Spot> cameFrom = new HashMap<>();

        // Add the start node to the open set
        openSet.add(new Node(start, fScore.get(start)));

        while (!openSet.isEmpty()) {
            // Get the node with the lowest f score
            Node current = openSet.poll();
            Spot currentPos = current.position;

            // If we've reached the destination, reconstruct the path
            if (currentPos.getRow() == dest.getRow() && currentPos.getCol() == dest.getCol()) {
                ArrayDeque<Spot> res = reconstructPath(cameFrom, currentPos);
                res.poll();
                return res;
            }

            // Explore neighbors
            for (Spot neighbor : getNeighbors(currentPos)) {
                // Calculate tentative g score
                int tentativeGScore = gScore.get(currentPos) + 1; // Assuming each step has a cost of 1

                // If this path to the neighbor is better, update the maps
                if (tentativeGScore < gScore.getOrDefault(neighbor, Integer.MAX_VALUE)) {
                    cameFrom.put(neighbor, currentPos);
                    gScore.put(neighbor, tentativeGScore);
                    fScore.put(neighbor, tentativeGScore + heuristic(neighbor, dest));

                    // Add the neighbor to the open set if it's not already there
                    if (!openSet.contains(new Node(neighbor, fScore.get(neighbor)))) {
                        openSet.add(new Node(neighbor, fScore.get(neighbor)));
                    }
                }
            }
        }

        // If no path is found, return an empty queue
        return new ArrayDeque<>();
    }

    // Heuristic function (Manhattan distance)
    private static int heuristic(Spot a, Spot b) {
        return Math.abs(a.getRow() - b.getRow()) + Math.abs(a.getCol() - b.getCol());
    }

    private static int heuristic(Point a, Spot b) {
        return Math.abs(a.x - b.getRow()) + Math.abs(a.y - b.getCol());
    }

    // Get valid neighbors for a given position
    private static List<Spot> getNeighbors( Spot pos) {
        List<Spot> neighbors = new ArrayList<>();
        int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}}; // Up, Down, Left, Right

        for (int[] dir : directions) {
            int newRow = pos.getRow() + dir[0];
            int newCol = pos.getCol() + dir[1];

            // Check if the neighbor is within the farm boundaries and traversable
            if (farm.validCoordinates(newRow, newCol)) {
                Spot neighborSpot = farm.getSpot(newRow, newCol);
                if (neighborSpot.isTraversable()) { // Only add if the spot is traversable
                    neighbors.add(neighborSpot);
                }
            }
        }

        return neighbors;
    }

    // Reconstruct the path from the destination to the start
    private static ArrayDeque<Spot> reconstructPath(Map<Spot, Spot> cameFrom, Spot current) {
        ArrayDeque<Spot> path = new ArrayDeque<>();
        while (current != null) {
            path.push(current);
            current = cameFrom.get(current);
        }
        return path;
    }

    // Find the closest traversable spot to the destination using BFS
    private static Spot findClosestTraversableSpot( Spot dest) {
        Queue<Spot> queue = new LinkedList<>();
        Set<Spot> visited = new HashSet<>();

        queue.add(dest);
        visited.add(dest);

        while (!queue.isEmpty()) {
            Spot current = queue.poll();

            // Check if the current spot is traversable
            if (current.isTraversable()) {
                return current;
            }

            // Explore neighbors
            for (Spot neighborPos : getNeighbors(current)) {
                Spot neighbor = farm.getSpot(neighborPos.getRow(), neighborPos.getCol());
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    queue.add(neighbor);
                }
            }
        }

        // No traversable spot found
        return null;
    }

    // Node class to represent a spot in the priority queue
    private static class Node {
        Spot position;
        int f; // Total cost (g + h)

        Node(Spot position, int f) {
            this.position = position;
            this.f = f;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Node node = (Node) o;
            return position.equals(node.position);
        }

        @Override
        public int hashCode() {
            return Objects.hash(position);
        }
    }

    public static void main(String[] args) {
        // Test the findPath method
        Farm farm = new Farm();
        farm.getSpot(0, 0).setIsTraversable(false);
        farm.getSpot(1, 1).setIsTraversable(false);
        farm.getSpot(1, 2).setIsTraversable(false);
        Spot start = farm.getSpot(0, 0);
        Spot dest = farm.getSpot(5, 5);
        dest.setIsTraversable(false); // Set destination as non-traversable for testing

        Queue<Spot> path = FindPath.findPath(start, dest);
        System.out.println("Path from (" + start.getRow() + ", " + start.getCol() + ") to closest traversable spot near (" + dest.getRow() + ", " + dest.getCol() + "):");
        System.out.println("Size: " + path.size());
        for (Spot p : path) {
            System.out.println("(" + p.getRow() + ", " + p.getCol() + ")");
        }
    }
}