package application;

import java.io.IOException;

import constants.IOConstants;
import graph.algorithm.DijkstraSequentialAlgorithm;
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
   * 
   * @throws IOException 
   */
  public static void main(String... args) throws IOException {
   new DijkstraSequentialAlgorithmRunnable(new DijkstraSequentialAlgorithm(
       IOUtil.parseGraph(IOConstants.INPUT_FILE))).run();
  }

}
