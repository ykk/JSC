package simulation.files.gnuplot;

import java.util.*;
import simulation.files.text.*;

/** Class to generate GNUPlot input files.
 * @author ykk
 */
public abstract class GnuPlot
{
    //Members
    /** Terminal type.
     */
    public String terminal;
    /** Filename for GnuPlot input file.
     */
    public String filename = "gnuplotfile";

    /** Indicate if plot has legend/key.
     * Defaults to true.
     */
    public boolean hasKey = true;

    /** Label for x-axis.
     */
    public String xlabel;
    /** Label for y-axis.
     */
    public String ylabel;

    /** Name for graphics file.
     */
    public String graphFilename = "gnuplotgraph";

    /** Indicate if Output title.
     * Defaults to false.
     */
    public boolean hasTitle = false;
    /** Title of graph.
     */
    public String title = "Please add some title";

    /** Vector of data.
     * @see GnuPlotData
     */
    public Vector data = new Vector();

    //Methods
    /** Output terminal string.
     * @return set term string
     */
    public abstract String termString();
    
    /** Output terminal specific header.
     * @return header string
     */
    public String termHeader()
    {
	String header = "set output \""+graphFilename+"\"";
	if (hasTitle) header +="\nset title \""+title+"\"";
	return header;
    }

    /** Return axes configuration.
     * @return configuration strings for axes
     */
    public String axesString()
    {
	return "set xlabel \'"+xlabel+"\'\n"+
	    "set ylabel \'"+ylabel+"\'";
    }

    /** Return plot string.
     * @return plot string
     */
    public String plotString()
    {
	String plotStr = "plot";
	for (int i = 0; i < data.size(); i++)
	{
	    plotStr += " "+(GnuPlotData) data.get(i);
	    if (i != (data.size()-1)) plotStr += ",";
	}

	return plotStr;
    }

    /** Return key/legend configuration.
     * @return legend configuring strings
     */
    public String keyString()
    {
	String keyString = "set key";
	keyString += (hasKey)? " on" : " off";

	return keyString;
    }

    /** Process inputs and grab file.
     * @param args arguments
     * @return input file
     */
    public FileVector process(String[] args)
    {
	parseInput(args);
	return getFile();
    }

    /** Parse input for GnuPlot.
     * @param args arguments
     */
    private void parseInput(String[] args)
    {
        //Check parameters.
        if (args.length < 7)
	{
	    System.err.println("Usage:");
	    System.err.println("<filename> <graphic output file> "+
			       "<xlabel> <ylabel>");
	    System.err.print("datafile1 columnx columny");
	    System.err.println("[datafile2 columnx columny] ...");
	    System.exit(1);
	}
	
	this.filename = args[0];
	this.graphFilename = args[1];
	this.xlabel = args[2];
	this.ylabel = args[3];

	for (int i = 4; i < args.length; i+=3)
	{
	    if (args.length < i+3)
	    {
		System.err.println("Incomplete data file specification");
		System.exit(1);
	    }
	    data.add(new GnuPlotData(args[i],
				     Integer.parseInt(args[i+1]),
				     Integer.parseInt(args[i+2])));
	}
    }

    /** Return input file for GnuPlot.
     * @return input file
     */
    private FileVector getFile()
    {
	FileVector file = new FileVector(filename);
	file.content.add(termString());
	file.content.add(termHeader());
	file.content.add(axesString());
	file.content.add(keyString());
	file.content.add(plotString());

	return file;
    }

}
