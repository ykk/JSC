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