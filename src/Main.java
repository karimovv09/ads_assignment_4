public class Main {
    public static void main(String[] args) {
        Vertex<String> stationA = new Vertex<>("Station A");
        Vertex<String> stationB = new Vertex<>("Station B");
        Vertex<String> stationC = new Vertex<>("Station C");
        Vertex<String> stationD = new Vertex<>("Station D");
        Vertex<String> stationE = new Vertex<>("Station E");

        stationA.addAdjacentVertex(stationB, 5);
        stationA.addAdjacentVertex(stationC, 10);
        stationB.addAdjacentVertex(stationC, 3);
        stationB.addAdjacentVertex(stationD, 7);
        stationC.addAdjacentVertex(stationD, 2);
        stationC.addAdjacentVertex(stationE, 4);
        stationD.addAdjacentVertex(stationE, 1);

        WeightedGraph<String> trainNetwork = new WeightedGraph<>();
        trainNetwork.addVertex(stationA);
        trainNetwork.addVertex(stationB);
        trainNetwork.addVertex(stationC);
        trainNetwork.addVertex(stationD);
        trainNetwork.addVertex(stationE);

        Search<String> bfs = new BreadthFirstSearch<>();
        Search<String> dijkstra = new DijkstraSearch<>();

        System.out.println("BFS path from Station A to Station E: " + bfs.search(stationA, stationE));
        System.out.println("Dijkstra path from Station A to Station E: " + dijkstra.search(stationA, stationE));
    }
}
