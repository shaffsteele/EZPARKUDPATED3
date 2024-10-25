import java.util.Map;

public class Test {
    public static void main(String[] args) {
        User user = new User(16000);

        try {
            // fetchEvents method is the 'query' we'd made in SQL.
            EZParkDatabase.fetchEvents(user);
        } catch (Exception e) {
            e.printStackTrace();
        }


        Graph lot = new Graph();

        // Add nodes (Locations)
        lot.addNode("LotA");
        lot.addNode("MainRoad1");
        lot.addNode("Entrance1");

        lot.addNode("LotB");
        lot.addNode("MainRoad2");
        lot.addNode("Entrance2");

        lot.addNode("EventVenue");

        lot.addNode("SecondLocation");

        // Add edges (roads between nodes, with weigh signifying if they are busy/far away from exit)
        lot.addEdge("MainRoad1", "Entrance1", 10);
        lot.addEdge("MainRoad2", "Entrance2", 10);
        lot.addEdge("MainRoad1", "Entrance2", 30);
        lot.addEdge("MainRoad2", "Entrance2", 35);

        lot.addEdge("LotA", "LotB", 5);
        lot.addEdge("LotB", "LotA", 5);

        lot.addEdge("Entrance1", "LotA", 5);
        lot.addEdge("Entrance2", "LotB", 5);
        lot.addEdge("Entrance1", "EventVenue", 25);
        lot.addEdge("Entrance2", "EventVenue", 30);

        lot.addEdge("LotA", "EventVenue", 5);
        lot.addEdge("LotB", "EventVenue", 5);

        lot.addEdge("LotA", "Entrance1", 5);
        lot.addEdge("LotB", "Entrance2", 5);

        lot.addEdge("LotB", "SecondLocation", 150);


        DjikstraAlgo djikstra = new DjikstraAlgo();
        // Starting location
        Node startNode = lot.getNode("MainRoad1");
        Node endNode = lot.getNode("LotB");

        if (startNode == null) {
            System.out.println("Start Node NULL | Check graph initalizaiton.");
        } else {
            // Calculate the shortest paths from the starting node to the end node.
            Map<Node, Integer> distances = djikstra.calculateShortestPath(lot, startNode);

            Integer distanceToLotB = distances.getOrDefault(endNode, Integer.MIN_VALUE);

            if (distanceToLotB > 2000) {
                distanceToLotB = 0;
            }

            System.out.println("Distance from MainRoad1 to LotB: " + distanceToLotB);


            // Print the distances
            // for (Map.Entry<Node, Integer> entry : distances.entrySet()) {
            //    if (entry.getValue() > 2000) {
            //        entry.setValue(0);
            //    }
            //    System.out.println("Distance from LotA to " + entry.getKey().getName() + ": " + entry.getValue());
            //}
        }

        // Is the user going to another location other than their house after the event?
        boolean returningHome = true;

        if (returningHome != true) {
            // assign the starting node to be the location their currently at

        }
        startNode = lot.getNode("LotB");
        endNode = lot.getNode("SecondLocation");
        Map<Node, Integer> distances2 = djikstra.calculateShortestPath(lot, startNode);
        Integer distanceToSecondaryLocation = distances2.getOrDefault(endNode, Integer.MIN_VALUE);

        System.out.println("Distance from LotB to SecondLocation: " + distanceToSecondaryLocation);


    }
}
