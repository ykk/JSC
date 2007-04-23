package simulation.files.gnuplot.plotters;

import java.io.*;
import simulation.files.gnuplot.*;

/** Abstract class to plot object directly.
 * Uses gnuplot.
 * @author ykk
 */
public abstract class Plotter
{
    //Members
    /** Filename for temporary data file.
     * Defaulted to plotterdata.
     */
    public String dataFilename = "plotterdata";
    /** Reference to {@link GnuPlot} object.
     * Default to PNG image format with filename plotteroutput.png.
     */
    public GnuPlot plot = new Png();
    /** Console command to view graph.
     * Default to open (for MAC OS X).
     */
    public String viewCommand = "open";

    //Methods
    /** Constructor.
     */
    public Plotter()
    {
	plot.graphFilename = "plotteroutput.png";
    }

    /** View graph.
     */
    public void view()
    {
	String output;

	try
	{
	    Process process = Runtime.getRuntime().exec(viewCommand+" "+plot.graphFilename);
	    BufferedReader outcome = new 
		BufferedReader(new InputStreamReader(process.getInputStream()));
	    while ((output = outcome.readLine()) != null)
		System.out.println(output);
	} catch (IOException err)
	{
	    System.err.println(this + " generates "+ err);
	}
    }


    /** Plot and view table.
     */
    public void plotNView()
    {
	plot();
	view();
    }

    /** Plot.
     */
    public abstract void plot();
}