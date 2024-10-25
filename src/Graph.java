import java.util.HashMap;
import java.util.Map;

public class Graph {
    private Map<String, Node> nodes;

    public Graph() {
        nodes = new HashMap<>();
    }

    public void addNode(String name) {
        nodes.put(name, new Node(name));
    }

    public Node getNode(String name) {
        return nodes.get(name);
    }

    public void addEdge(String source, String destination, int weight) {
        Node sourceNode = nodes.get(source);
        Node destinationNode = nodes.get(destination);
        if (sourceNode != null & destinationNode != null) {
            sourceNode.addEdge(destinationNode, weight);
        }
    }

    // Method to return all nodes
    public Map<String, Node> getNodes() {
        return nodes;
    }
}
