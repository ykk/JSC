package simulation.files.graphviz;

import java.io.*;
import java.util.*;
import simulation.files.text.*;

/** Class to generate Graphviz input files.
 * @author ykk
 */
public abstract class GraphViz
{
    //Members
    /** Option to invoke dot. 
     * dot draws directed graphs. It works well on DAGs and other graphs 
     * that can be drawn as hierarchies. It reads attributed graph files 
     * and writes drawings. By default, the output format dot is the 
     * input file with layout coordinates appended.
     */
    public static final String CMD_DOT ="dot ";
    /** Option to invoke neato.
     * neato draws undirected graphs using ``spring'' models (see Kamada 
     * and Kawai, Information Processing Letters 31:1, April 1989). Input 
     * files must be formatted in the dot attributed graph language. By 
     * default, the output of neato is the input graph with layout coordinates
     * appended.
     */
    public static final String CMD_NEATO ="neato ";
    /** Option to invoke twopi.
     * twopi draws graphs using a radial layout (see G. Wills, Symposium on 
     * Graph Drawing GD'97, September, 1997). Basically, one node is chosen 
     * as the center and put at the origin. The remaining nodes are placed 
     * on a sequence of concentric circles centered about the origin, each a 
     * fixed radial distance from the previous circle. All nodes distance 1 
     * from the center are placed on the first circle; all nodes distance 1 
     * from a node on the first circle are placed on the second circle; 
     * and so forth.
     */
    public static final String CMD_TWOPI ="twopi ";
    /** Option to invoke circo.
     * circo draws graphs using a circular layout (see Six and Tollis, 
     * GD '99 and ALENEX '99, and Kaufmann and Wiese, GD '02.) The tool 
     * identifies biconnected components and draws the nodes of the component 
     * on a circle. The block-cutpoint tree is then laid out using a 
     * recursive radial algorithm. Edge crossings within a circle are 
     * minimized by placing as many edges on the circle's perimeter as 
     * possible. In particular, if the component is outerplanar, the 
     * component will have a planar layout.  If a node belongs to multiple 
     * non-trivial biconnected components, the layout puts the node in one of 
     * them. By default, this is the first non-trivial component found in the 
     * search from the root component.
     */
    public static final String CMD_CIRCO ="circo ";
    /** Option to invooke fdp.
     * fdp draws undirected graphs using a ``spring'' model. It relies on a 
     * force-directed approach in the spirit of Fruchterman and Reingold 
     * (cf. Software-Practice & Experience 21(11), 1991, pp. 1129-1164).
     */
    public static final String CMD_FDP ="fdp ";
    /** Graphviz program to invoke.
     * Default is {@link #CMD_DOT}
     */
    public String command = CMD_DOT;
    /** File format to output.
     * Default is {@link #FMT_PS}
     */
    public String format = FMT_PS;
    public static final String FMT_PS ="PS";
    public static final String FMT_PNG ="PNG";
    public static final String FMT_GIF ="GIF";
    /** Filename for GnuPlot input file.
     */
    public String filename = "graphvizfile";
    /** Name for graphics file.
     */
    public String graphFilename = "graphvizgraph";

    //Methods
    /** Return input file for Graphviz.
     * @return input file
     */
    public abstract FileVector getFile();

    /** Create file and plot the graph.
     */
    public void execPlot()
    {
	String output;

	try
	{
	    Process process = Runtime.
		getRuntime().exec(command+" -T"+format+
				  " "+filename+".dot"+
				  " > "+graphFilename+format);
	    BufferedReader outcome = new 
		BufferedReader(new InputStreamReader(process.
						     getInputStream()));
	    while ((output = outcome.readLine()) != null)
		System.out.println(output);
	} catch (IOException err)
	{
	    System.err.println(this + " generates "+ err);
	}
    }
}