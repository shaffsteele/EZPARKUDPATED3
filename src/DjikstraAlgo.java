import java.util.*;

public class DjikstraAlgo {

    public Map<Node, Integer> calculateShortestPath(Graph graph, Node startNode) {
        // Store shortest distances to node
        Map<Node, Integer> distances = new HashMap<>();
        // Store the shortest path tree (visited nodes)
        Set<Node> visitedNodes = new HashSet<>();
        // Priority queue to explore nodes by shortest distance
        PriorityQueue<NodeDistance> pq = new PriorityQueue<>(Comparator.comparingInt(NodeDistance::getDistance));

        // Initialize distance with infinity, except the start node
        for (String nodeName : graph.getNodes().keySet()) {
            Node node = graph.getNode(nodeName);
            distances.put(node, Integer.MAX_VALUE);
        }
        distances.put(startNode, 0);

        // Start with the source node
        pq.add(new NodeDistance(startNode, 0));

        while (!pq.isEmpty()) {
            Node currentNode = pq.poll().getNode();

            // Skip visited nodes
            if (visitedNodes.contains(currentNode)) continue;
            visitedNodes.add(currentNode);

            // Explore neighbors
            for (Edge edge : currentNode.getEdges()) {
                Node neighbor = edge.getDestination();
                int newDist = distances.get(currentNode) + edge.getWeight();

                // if a shorter path is found
                if (newDist < distances.get(neighbor)) {
                    distances.put(neighbor, newDist);
                    pq.add(new NodeDistance(neighbor, newDist));
                }
            }
        }

        return distances;
    }

    // Helper class to store nodes and their distances
    private class NodeDistance {
        private Node node;
        private int distance;

        public NodeDistance(Node node, int distance) {
            this.node = node;
            this.distance = distance;
        }

        public Node getNode() {
            return node;
        }

        public int getDistance() {
            return distance;
        }
    }
}
