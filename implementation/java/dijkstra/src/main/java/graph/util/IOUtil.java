package graph.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import graph.entities.Graph;

/**
 * Contains methods for IO for @Graph.
 * 
 * @author alex_smarandache
 *
 */
public class IOUtil {

  /**
   * Hidden constructor.
   */
  private IOUtil() {
    // not needed
  }

  /**
   * Write the given graph to a file.
   * 
   * @param fileName The file name for graph writing.
   * @param graph    The graph to be written.
   * 
   * @throws IOException When IO problems appears.
   */
  public static void writeGraph(final String fileName, final Graph graph) throws IOException  {
    final File file = new File(fileName);
    try (BufferedWriter bufferedWriter = Files.newBufferedWriter(file.toPath())) {
        bufferedWriter.append(String.valueOf(graph.getNoOfNodes())).append(" ")
                .append(String.valueOf(graph.getSourceNode())).append('\n');
        graph.getAdjacencyMatrix().forEach(row -> {
            try {
                for (Integer val : row) {
                    bufferedWriter.append(String.valueOf(val)).append(' ');
                }
                bufferedWriter.append('\n');
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    } 
}
  
}
