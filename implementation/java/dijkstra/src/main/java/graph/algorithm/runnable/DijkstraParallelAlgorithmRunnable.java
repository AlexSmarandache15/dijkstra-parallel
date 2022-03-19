package graph.algorithm.runnable;

import java.io.IOException;
import java.time.Instant;

import constants.IOConstants;
import graph.algorithm.DijkstraParallelAlgorithm;
import graph.util.IOUtil;

/**
 * Used to run an instance of @DijkstraParallelAlgorithm.
 * 
 * @author alex_smarandache
 *
 */
public class DijkstraParallelAlgorithmRunnable implements Runnable {
  
  /**
   * The Dijkstra algorithm to run.
   */
  private final DijkstraParallelAlgorithm dijkstraPar;
  
  /**
   * Constructor.
   * 
   * @param dijkstraPar The Dijkstra algorithm to run.
   */
  public DijkstraParallelAlgorithmRunnable(final DijkstraParallelAlgorithm dijkstraPar)  {
    this.dijkstraPar = dijkstraPar;
  }

  @Override
  public void run() {
    final Instant start = Instant.now();
    dijkstraPar.solve();
    final Instant end = Instant.now();
    final long startTimeMicros = start.getEpochSecond() * 1000_000 + start.getNano() / 1000;
    final long endTimeMicros = end.getEpochSecond() * 1000_000 + end.getNano() / 1000;
    System.out.println("Dijkstra parallel time: " + (endTimeMicros - startTimeMicros) + " microseconds.");
    
    try {
      IOUtil.writeResults(IOConstants.OUTPUT_FILE_PAR, dijkstraPar.getDistances(), 
          dijkstraPar.getGraph().getSourceNode());
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

}
