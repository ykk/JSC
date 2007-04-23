package simulation.files.gnuplot.plotters;

import simulation.files.gnuplot.*;
import simulation.utilities.structures.*;
import simulation.files.text.*;

/** Class to generate {@link Table}.
 * @author ykk
 */ 
public class PlotTable
    extends Plotter
{
    //Members
    /** Reference to table to plot
     */
    public Table table;

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
	    plot.data.add(new GnuPlotData(dataFilename,1,i+1));

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