import java.util.*;

public class DijkstraSearch<V> implements Search<V> {
    @Override
    public List<Vertex<V>> search(Vertex<V> start, Vertex<V> end) {
        Map<Vertex<V>, Double> distances = new HashMap<>();
        Map<Vertex<V>, Vertex<V>> previous = new HashMap<>();
        PriorityQueue<Vertex<V>> queue = new PriorityQueue<>(Comparator.comparing(distances::get));
        Set<Vertex<V>> visited = new HashSet<>();

        for (Vertex<V> vertex : start.getAdjacentVertices().keySet()) {
            distances.put(vertex, Double.POSITIVE_INFINITY);
        }
        distances.put(start, 0.0);

        queue.add(start);

        while (!queue.isEmpty()) {
            Vertex<V> current = queue.poll();

            if (current.equals(end)) {
                return constructPath(previous, start, end);
            }

            if (visited.contains(current)) {
                continue;
            }
            visited.add(current);

            for (Map.Entry<Vertex<V>, Double> neighborEntry : current.getAdjacentVertices().entrySet()) {
                Vertex<V> neighbor = neighborEntry.getKey();
                double edgeWeight = neighborEntry.getValue();
                double newDist = distances.get(current) + edgeWeight;

                if (newDist < distances.getOrDefault(neighbor, Double.POSITIVE_INFINITY)) {
                    distances.put(neighbor, newDist);
                    previous.put(neighbor, current);
                    queue.add(neighbor);
                }
            }
        }
        return null; // No path found
    }

    private List<Vertex<V>> constructPath(Map<Vertex<V>, Vertex<V>> previous, Vertex<V> start, Vertex<V> end) {
        List<Vertex<V>> path = new ArrayList<>();
        for (Vertex<V> at = end; at != null; at = previous.get(at)) {
            path.add(at);
        }
        Collections.reverse(path);
        return path;
    }
}
