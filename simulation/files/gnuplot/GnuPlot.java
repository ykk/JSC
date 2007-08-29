package simulation.files.gnuplot;

import java.io.*;
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

    /** Indicate view, x rotation.
     */
    public int xViewRotate = -1;
    /** Indicate view, y rotation.
     */
    public int yViewRotate = -1;
    /** Indicate if contour is too be shown.
     */
    public boolean hasContour = false;
    /** Indicate if plot has legend/key.
     * Defaults to true.
     */
    public boolean hasKey = true;    
    /** Indicate horizontal position of legend/key.
     */
    public boolean keyIsLeft = false;
    /** Indicate vertical position of legend/key.
     */
    public boolean keyIsTop = true;
    /** Indicate if key is placed outside graph.
     */
    public boolean keyOutside = false;
    /** Indicate if key is placed below graph.
     */
    public boolean keyBelow = false;
    /** Indicate symbols position in key.
     */
    public boolean keySymLeft = false;

    /** Indicate if y-axis is logscale.
     */
    public boolean yIsLog = false;
    /** Indicate if x-axis is logscale.
     */
    public boolean xIsLog = false;
    /** Label for x-axis.
     */
    public String xlabel;
    /** Label for y-axis.
     */
    public String ylabel;
    /** Label for z-axis.
     */
    public String zlabel;

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

    /** Show 2nd y-axis.
     * Default to false.
     */
    public boolean showY2 = false;
    /** Show 2nd x-axis.
     * Default to false.
     */
    public boolean showX2 = false;
    /** Minimum of z axis.
     * Defaulted to Double.MIN_VALUE, giving autoscale.
     */
    public double zmin = Double.MIN_VALUE;
    /** Maximum of z axis.
     * Defaulted to Double.MAX_VALUE, giving autoscale.
     */
    public double zmax = Double.MAX_VALUE;
    /** Minimum of y axis.
     * Defaulted to Double.MIN_VALUE, giving autoscale.
     */
    public double ymin = Double.MIN_VALUE;
    /** Maximum of y axis.
     * Defaulted to Double.MAX_VALUE, giving autoscale.
     */
    public double ymax = Double.MAX_VALUE;
    /** Minimum of x axis.
     * Defaulted to Double.MIN_VALUE, giving autoscale.
     */
    public double xmin = Double.MIN_VALUE;
    /** Maximum of x axis.
     * Defaulted to Double.MAX_VALUE, giving autoscale.
     */
    public double xmax = Double.MAX_VALUE;
    /** Minimum of y2 axis.
     * Defaulted to Double.MIN_VALUE, giving autoscale.
     */
    public double y2min = Double.MIN_VALUE;
    /** Maximum of y2 axis.
     * Defaulted to Double.MAX_VALUE, giving autoscale.
     */
    public double y2max = Double.MAX_VALUE;
    /** Minimum of x2 axis.
     * Defaulted to Double.MIN_VALUE, giving autoscale.
     */
    public double x2min = Double.MIN_VALUE;
    /** Maximum of x2 axis.
     * Defaulted to Double.MAX_VALUE, giving autoscale.
     */
    public double x2max = Double.MAX_VALUE;

    /** Indicate if coordinate is polar.
     * Defaulted to false.
     */
    public boolean isPolar = false;
    /** Indicate if angle is degree.
     * Defaulted to false.
     */
    public boolean angleIsDegree = false;

    /** Vector of data.
     * @see GnuPlotData
     */
    public Vector data = new Vector();
    /** Vector of labels and individual items.
     * @see GnuPlotLabel
     */
    public Vector labels = new Vector();
    
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
	String axes = "set xlabel \'"+xlabel+"\'\n"+
	    "set ylabel \'"+ylabel+"\'";
	if (zlabel != null) axes += "\nset zlabel \'"+zlabel+"\'";

	if (showX2) axes += "\nset xtics nomirror \nset x2tics";
	if (showY2) axes += "\nset ytics nomirror \nset y2tics";

	if ((zmin != Double.MIN_VALUE) || (zmax != Double.MAX_VALUE))
	    axes += "\nset zrange ["+((zmin != Double.MIN_VALUE)?zmin:"")+
		":"+((zmax != Double.MAX_VALUE)?zmax:"")+"]";
	if ((ymin != Double.MIN_VALUE) || (ymax != Double.MAX_VALUE))
	    axes += "\nset yrange ["+((ymin != Double.MIN_VALUE)?ymin:"")+
		":"+((ymax != Double.MAX_VALUE)?ymax:"")+"]";
	if ((xmin != Double.MIN_VALUE) || (xmax != Double.MAX_VALUE))
	    axes += "\nset xrange ["+((xmin != Double.MIN_VALUE)?xmin:"")+
		":"+((xmax != Double.MAX_VALUE)?xmax:"")+"]";
	if ((y2min != Double.MIN_VALUE) || (y2max != Double.MAX_VALUE))
	    axes += "\nset y2range ["+((y2min != Double.MIN_VALUE)?y2min:"")+
		":"+((y2max != Double.MAX_VALUE)?y2max:"")+"]";
	if ((x2min != Double.MIN_VALUE) || (x2max != Double.MAX_VALUE))
	    axes += "\nset x2range ["+((x2min != Double.MIN_VALUE)?x2min:"")+
		":"+((x2max != Double.MAX_VALUE)?x2max:"")+"]";
	if (xIsLog) axes+= "\nset logscale x";
	if (yIsLog) axes+= "\nset logscale y";

	if (hasContour)
	    axes+= "\nset contour";
	if (xViewRotate != -1 && yViewRotate != -1)
	    axes+= "\nset view "+xViewRotate+","+yViewRotate;

	if (isPolar)
	    axes+= "\nset polar";
	if (angleIsDegree)
	    axes+= "\nset angles degrees";

	return axes;
    }

    /** Return plot string.
     * @return plot string
     */
    public String plotString()
    {
	int type=-1;
	String plotStr = new String();

	if (data.get(0) instanceof GnuPlotData)
	{
	    plotStr = "plot";
	    type = 0;
	}
	else if (data.get(0) instanceof GnuPlotSfData)
	{
	    plotStr = "splot";
	    type = 1;
	}

	for (int i = 0; i < data.size(); i++)
	{
	    switch(type)
	    {
	    case 0:
		plotStr += " "+(GnuPlotData) data.get(i);
		break;
	    case 1:
		plotStr += " "+(GnuPlotSfData) data.get(i);
		break;
	    }
	    if (i != (data.size()-1)) plotStr += ",";
	}

	return plotStr;
    }

    /** Return label string.
     */
    public String labelString()
    {
	String labelString = "";

	for (int i = 0; i < labels.size(); i++)
	{
	    labelString += "set "+labels.get(i);
	    if (i != (labels.size()-1))
		labelString+="\n";
	}

	return labelString;
    }

    /** Return key/legend configuration.
     * @return legend configuring strings
     */
    public String keyString()
    {
	String keyString = "set key";
	keyString += (hasKey)? " on" : " off";
	keyString += (keyIsLeft)? " left" : " right";
	keyString += (keyIsTop)? " top" : " bottom";
	if (keyOutside) keyString+=" outside";
	if (keyBelow) keyString+=" below";
	if (keySymLeft) keyString+=" reverse";

	return keyString;
    }

    /** Process inputs and grab file.
     * @param args arguments
     * @return input file
     */
    protected FileVector process(String[] args)
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
	    System.err.println(" [datafile2 columnx columny] ...");
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
    public FileVector getFile()
    {
	FileVector file = new FileVector(filename);
	file.content.add(termString());
	file.content.add(termHeader());
	file.content.add(axesString());
	file.content.add(labelString());
	file.content.add(keyString());
	file.content.add(plotString());

	return file;
    }

    /** Create file and plot the graph.
     */
    public void execPlot()
    {
	String output;
	FileVector file = getFile();
	file.write();

	try
	{
	    Process process = Runtime.getRuntime().exec("gnuplot < "+filename);
	    BufferedReader outcome = new 
		BufferedReader(new InputStreamReader(process.getInputStream()));
	    while ((output = outcome.readLine()) != null)
		System.out.println(output);
	} catch (IOException err)
	{
	    System.err.println(this + " generates "+ err);
	}
    }
}
