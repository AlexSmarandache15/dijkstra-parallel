package graph.algorithm;

import constants.Constants;
import graph.entities.Graph;
import graph.entities.Node;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.PriorityQueue;

public class DijkstraParallelStreamsAlgorithm implements IAlgorithm {

  /**
   * The graph to perform the Dijkstra's algorithm sequentially.
   */
  private final Graph graph;

  private final List<NodeItem> nodes = new ArrayList<>();


  /**
   * Constructor.
   *
   * @param graph The graph to perform the Dijkstra's algorithm sequentially.
   */
  public DijkstraParallelStreamsAlgorithm(final Graph graph) {
    this.graph = graph;
    final int noOfNodes = graph.getNoOfNodes();

    for (int i = 0; i < noOfNodes; i++) {
      final NodeItem newNode = new NodeItem();
      newNode.id = i;
      nodes.add(newNode);
    }

    nodes.get(graph.getSourceNode()).distance = 0;
  }


  @Override
  public void solve() {
    final int noOfNodes = nodes.size();
    for(int i = 0; i < noOfNodes - 1; i++) {
      final Optional<NodeItem> currentMinNode = nodes.parallelStream().min((a, b) -> (!a.visited && b.visited || (!a.visited && !b.visited && a.distance < b.distance)) ? -1 : 1);
      if(currentMinNode.isPresent()) {
        final NodeItem nodeItem = currentMinNode.get();
        final int index = nodeItem.id;
        nodeItem.visited = true;
        nodes.parallelStream().forEach((node -> {
          if (!nodes.get(node.id).visited && graph.getAdjacencyMatrix().get(index).get(node.id) != 0
                  && nodes.get(index).distance != Constants.INFINITE
                  && nodes.get(index).distance + graph.getAdjacencyMatrix().get(index).get(node.id) < nodes.get(node.id).distance)
            node.distance = nodes.get(index).distance + graph.getAdjacencyMatrix().get(index).get(node.id);
        }));
      }
    }
  }

  public List<Integer> getDistances() {
    List<Integer> toReturn = new ArrayList<>();
    nodes.forEach(node -> toReturn.add(node.distance));
    return toReturn;
  }

  public Graph getGraph() {
    return graph;
  }

  private class NodeItem  {
    public int distance = Constants.INFINITE;
    public boolean visited = false;
    public int id;
  }
}
