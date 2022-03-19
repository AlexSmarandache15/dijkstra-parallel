package application;

import java.io.IOException;

import constants.IOConstants;
import graph.algorithm.DijkstraParallelAlgorithm;
import graph.algorithm.DijkstraSequentialAlgorithm;
import graph.algorithm.runnable.DijkstraParallelAlgorithmRunnable;
import graph.algorithm.runnable.DijkstraSequentialAlgorithmRunnable;
import graph.util.IOUtil;

/**
 * Used to run the application.
 * 
 * @author alex_smarandache
 *
 */
public class Application {

  /**
   * Hidden constructor.
   */
  private Application() {
    // not needed
  }

  /**
   * Used to run the application.
   * 
   * @param args - args[0] contains strategy to run this application: 
   * <br>
   * <code>all</code>(run both, sequential and parallel) 
   * <br>
   * <code>seq</code>(run sequential variant) 
   * <br>
   * <code>par</code>(run parallel variant) 
   * <br>
   * The default value is <code>all</code>.
   * <br>
   * You an specify the number of threads for the parallel algorithm with args[1].
   * 
   * @throws IOException 
   */
  public static void main(String... args) throws IOException {
    if(args.length == 0) {
      new DijkstraParallelAlgorithmRunnable(new DijkstraParallelAlgorithm(
          IOUtil.parseGraph(IOConstants.INPUT_FILE))).run();
      new DijkstraSequentialAlgorithmRunnable(new DijkstraSequentialAlgorithm(
          IOUtil.parseGraph(IOConstants.INPUT_FILE))).run();
    } else {
      if(args[0].equals("all") || args[0].equals("par")) {
        if(args.length > 1) {
          new DijkstraParallelAlgorithmRunnable(new DijkstraParallelAlgorithm(
              IOUtil.parseGraph(IOConstants.INPUT_FILE), Integer.parseInt(args[1]))).run();

        } else {
          new DijkstraParallelAlgorithmRunnable(new DijkstraParallelAlgorithm(
              IOUtil.parseGraph(IOConstants.INPUT_FILE))).run();
        }
        if(args[0].equals("all")) {
          new DijkstraSequentialAlgorithmRunnable(new DijkstraSequentialAlgorithm(
              IOUtil.parseGraph(IOConstants.INPUT_FILE))).run();
        }
      } else {
        new DijkstraSequentialAlgorithmRunnable(new DijkstraSequentialAlgorithm(
            IOUtil.parseGraph(IOConstants.INPUT_FILE))).run();
      }
    }
  }

}
