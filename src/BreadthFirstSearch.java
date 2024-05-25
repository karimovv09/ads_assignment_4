import java.util.*;

public class BreadthFirstSearch<V> implements Search<V> {
    @Override
    public List<Vertex<V>> search(Vertex<V> start, Vertex<V> end) {
        Queue<Vertex<V>> queue = new LinkedList<>();
        Map<Vertex<V>, Vertex<V>> previous = new HashMap<>();
        Set<Vertex<V>> visited = new HashSet<>();

        queue.add(start);
        visited.add(start);

        while (!queue.isEmpty()) {
            Vertex<V> current = queue.poll();

            if (current.equals(end)) {
                return constructPath(previous, start, end);
            }

            for (Vertex<V> neighbor : current.getAdjacentVertices().keySet()) {
                if (!visited.contains(neighbor)) {
                    queue.add(neighbor);
                    visited.add(neighbor);
                    previous.put(neighbor, current);
                }
            }
        }
        return null;
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
