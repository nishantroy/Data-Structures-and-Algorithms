import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.LinkedList;
import java.util.Set;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.HashMap;

/**
 * Your implementations of various graph algorithms.
 *
 * @author Nishant Roy
 * @version 1.0
 */
public class GraphAlgorithms {

    /**
     * Perform breadth first search on the given graph, starting at the start
     * Vertex.  You will return a List of the vertices in the order that
     * you visited them.  Make sure to include the starting vertex at the
     * beginning of the list.
     * <p>
     * When exploring a Vertex, make sure you explore in the order that the
     * adjacency list returns the neighbors to you.  Failure to do so may
     * cause you to lose points.
     * <p>
     * You may import/use {@code java.util.Queue}, {@code java.util.Set},
     * {@code java.util.Map}, {@code java.util.List}, and any classes
     * that implement the aforementioned interfaces.
     *
     * @param start the Vertex you are starting at
     * @param graph the Graph we are searching
     * @param <T>   the data type representing the vertices in the graph.
     * @return a List of vertices in the order that you visited them
     * @throws IllegalArgumentException if any input is null, or if
     *                                  {@code start} doesn't exist in the graph
     */
    public static <T> List<Vertex<T>> breadthFirstSearch(Vertex<T> start,
                                                         Graph<T> graph) {


        if (start == null || graph == null) {
            throw new IllegalArgumentException("Inputs cannot be null");
        }

        if (!graph.getAdjacencyList().containsKey(start)) {
            throw new IllegalArgumentException("Vertex does not exist");
        }

        Map<Vertex<T>, List<VertexDistancePair<T>>> adjList =
                graph.getAdjacencyList();

        List<Vertex<T>> vertices = new ArrayList<>();
        vertices.add(start);

        Queue<Vertex<T>> nodes = new LinkedList<>();
        nodes.add(start);
        while (!nodes.isEmpty()) {
            Vertex<T> searching = nodes.remove();
            List<VertexDistancePair<T>> adjVerts =
                    adjList.get(searching);
            for (int i = 0; i < adjVerts.size(); i++) {
                VertexDistancePair<T> vd = adjVerts.get(i);
                Vertex<T> child = vd.getVertex();
                if (!vertices.contains(child)) {
                    nodes.add(child);
                    vertices.add(child);
                }
            }
        }
        return vertices;
    }

    /**
     * Perform depth first search on the given graph, starting at the start
     * Vertex.  You will return a List of the vertices in the order that
     * you visited them.  Make sure to include the starting vertex at the
     * beginning of the list.
     * <p>
     * When exploring a Vertex, make sure you explore in the order that the
     * adjacency list returns the neighbors to you.  Failure to do so may
     * cause you to lose points.
     * <p>
     * You MUST implement this method recursively.
     * Do not use any data structure as a stack to avoid recursion.
     * Implementing it any other way WILL cause you to lose points!
     * <p>
     * You may import/use {@code java.util.Set}, {@code java.util.Map},
     * {@code java.util.List}, and any classes that implement the
     * aforementioned interfaces.
     *
     * @param start the Vertex you are starting at
     * @param graph the Graph we are searching
     * @param <T>   the data type representing the vertices in the graph.
     * @return a List of vertices in the order that you visited them
     * @throws IllegalArgumentException if any input is null, or if
     *                                  {@code start} doesn't exist in the graph
     */
    public static <T> List<Vertex<T>> depthFirstSearch(Vertex<T> start,
                                                       Graph<T> graph) {

        if (start == null || graph == null) {
            throw new IllegalArgumentException("Inputs cannot be null");
        }

        if (!graph.getAdjacencyList().containsKey(start)) {
            throw new IllegalArgumentException("Vertex does not exist");
        }

        List<Vertex<T>> vertices = new ArrayList<>();
        Map<Vertex<T>, List<VertexDistancePair<T>>> adjList =
                graph.getAdjacencyList();
        Set<Vertex<T>> vertexSet = new HashSet<>();
        dfs(adjList, start, vertexSet, vertices);
        return vertices;

    }

    /**
     * Private helper method for Depth-First Search
     *
     * @param adjList   Adjacency List
     * @param start     Vertex to start from
     * @param vertexSet Set of vertices visited
     * @param vertices  Vertices visited
     * @param <T>       Type of elements stored
     */
    private static <T> void dfs(Map<Vertex<T>,
            List<VertexDistancePair<T>>> adjList, Vertex<T> start,
                                Set<Vertex<T>> vertexSet,
                                List<Vertex<T>> vertices) {
        if (!vertexSet.contains(start)) {
            vertexSet.add(start);
            vertices.add(start);
            List<VertexDistancePair<T>> vdpairList = adjList.get(start);
            for (VertexDistancePair<T> vdpair : vdpairList) {
                Vertex<T> child = vdpair.getVertex();
                dfs(adjList, child, vertexSet, vertices);
            }
        }

    }


    /**
     * Find the shortest distance between the start vertex and all other
     * vertices given a weighted graph where the edges only have positive
     * weights.
     * <p>
     * Return a map of the shortest distances such that the key of each entry is
     * a node in the graph and the value for the key is the shortest distance
     * to that node from start, or Integer.MAX_VALUE (representing infinity)
     * if no path exists. You may assume that going from a vertex to itself
     * has a distance of 0.
     * <p>
     * There are guaranteed to be no negative edge weights in the graph.
     * <p>
     * You may import/use {@code java.util.PriorityQueue},
     * {@code java.util.Map}, and any class that implements the aforementioned
     * interface.
     *
     * @param start the Vertex you are starting at
     * @param graph the Graph we are searching
     * @param <T>   the data type representing the vertices in the graph.
     * @return a map of the shortest distances from start to every other node
     * in the graph.
     * @throws IllegalArgumentException if any input is null, or if
     *                                  {@code start} doesn't exist in the graph
     */
    public static <T> Map<Vertex<T>, Integer> dijkstras(Vertex<T> start,
                                                        Graph<T> graph) {

        if (start == null || graph == null) {
            throw new IllegalArgumentException("Inputs cannot be null");
        }

        if (!graph.getAdjacencyList().containsKey(start)) {
            throw new IllegalArgumentException("Start node does not exist in "
                    + "graph");
        }


        Map<Vertex<T>, List<VertexDistancePair<T>>> adjList =
                graph.getAdjacencyList();

        Map<Vertex<T>, Integer> dijkstra = new HashMap<>();

        for (Vertex<T> vertex : adjList.keySet()) {
            dijkstra.put(vertex, Integer.MAX_VALUE);
        }

        dijkstra.put(start, 0);

        PriorityQueue<VertexDistancePair<T>> pq = new PriorityQueue<>();
        pq.add(new VertexDistancePair<T>(start, 0));

        while (!pq.isEmpty()) {
            VertexDistancePair<T> curr = pq.remove();
            List<VertexDistancePair<T>> vdpairs =
                    graph.getAdjacencyList().get(curr.getVertex());
            for (VertexDistancePair<T> vd : vdpairs) {
                int dist = curr.getDistance() + vd.getDistance();
                if (dist < dijkstra.get(vd.getVertex())) {
                    dijkstra.put(vd.getVertex(), dist);
                    pq.add(new VertexDistancePair<>(vd.getVertex(),
                            dist));
                }
            }

        }

        return dijkstra;
    }

    /**
     * Run Prim's algorithm on the given graph and return the minimum spanning
     * tree in the form of a set of Edges.  If the graph is disconnected, and
     * therefore there is no valid MST, return null.
     * <p>
     * When exploring a Vertex, make sure you explore in the order that the
     * adjacency list returns the neighbors to you.  Failure to do so may
     * cause you to lose points.
     * <p>
     * You may assume that for a given starting vertex, there will only be
     * one valid MST that can be formed. In addition, only an undirected graph
     * will be passed in.
     * <p>
     * You may import/use {@code java.util.PriorityQueue},
     * {@code java.util.Set}, and any class that implements the aforementioned
     * interface.
     *
     * @param start the Vertex you are starting at
     * @param graph the Graph we are searching
     * @param <T>   the data type representing the vertices in the graph.
     * @return the MST of the graph; null if no valid MST exists.
     * @throws IllegalArgumentException if any input is null, or if
     *                                  {@code start} doesn't exist in the graph
     */
    public static <T> Set<Edge<T>> prims(Vertex<T> start, Graph<T> graph) {

        if (start == null || graph == null) {
            throw new IllegalArgumentException("Inputs cannot be null");
        }

        if (!graph.getAdjacencyList().containsKey(start)) {
            throw new IllegalArgumentException("Start node does not exist in "
                    + "graph");
        }
        Set<Vertex<T>> visited = new HashSet<>();
        Set<Edge<T>> mst = new HashSet<>();
        Map<Vertex<T>, List<VertexDistancePair<T>>> adjList =
                graph.getAdjacencyList();
        PriorityQueue<Edge<T>> pq = new PriorityQueue<>();


        Vertex<T> curr = start;

        while (mst.size() != adjList.size() - 1) {
            List<VertexDistancePair<T>> vdpairs = adjList.get(curr);
            for (VertexDistancePair<T> vd : vdpairs) {
                Vertex<T> vert = vd.getVertex();
                if (!visited.contains(vert)) {
                    pq.add(new Edge<>(curr, vert, vd.getDistance(), false));
                }
            }

            if (!pq.isEmpty()) {
                Edge<T> currEdge = pq.remove();
                mst.add(currEdge);
                visited.add(currEdge.getU());
                visited.add(currEdge.getV());
                curr = currEdge.getV();

            }
        }

        return mst;
    }

}
