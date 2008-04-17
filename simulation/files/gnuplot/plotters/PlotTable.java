package simulation.files.gnuplot.plotters;

import simulation.files.gnuplot.*;
import simulation.utilities.structures.*;
import simulation.files.text.*;
import java.util.*;

/** Class to generate {@link Table}.
 * @author ykk
 */ 
public class PlotTable
    extends Plotter
{
    //Members
    /** Plot style.
     * Defaults to linespoint.
     * @see GnuPlotData
     */
    public String plotStyle = GnuPlotData.STYLE_LINESPOINT;
    /** Reference to table to plot
     */
    public Table table;
    /** Names for data.
     */
    public Vector names = new Vector();

    //Methods
    /** Constructor.
     * @param table reference to table
     */
    public PlotTable(Table table)
    {
	super();
	this.table = table;
    }

    /** Plot table in file.
     * @param args 1st argument is filename of table; 
                   2nd argument is label for x-axis; 
		   3rd argument is label for y-axis;
		   Other arguments are columns to remove
     */
    public static void main(String[] args)
    {
	FileTable file = new FileTable(args[0],"\t ");
	file.read();
	PlotTable plot = new PlotTable(file.content);
	plot.plot.xlabel = args[1];
	plot.plot.ylabel = args[2];
	if (args.length > 3)
	    for (int i = 3; i < args.length; i++)
		plot.table.removeCol(Integer.parseInt(args[i]));
	plot.plot();
    }

    /** Plot table, with all columns.
     * Uses column 1 as x-axis.
     */
    public void plot()
    {
	writeData();

	int minCol = table.minCol();
	for (int i = 1; i < minCol; i++)
	{
	    GnuPlotData data = new GnuPlotData(dataFilename,1,i+1); 
	    if (names.size() >= i)
		data.name = (String) names.get(i-1);
	    data.plotStyle = plotStyle;
	    plot.data.add(data);
	}

	plot.execPlot();
    }

    /** Write table to file.
     */
    private void writeData()
    {
	FileTable file = new FileTable(dataFilename);
	table.changeSeparator("\t");
	file.content = table;
	file.write();
    }
}