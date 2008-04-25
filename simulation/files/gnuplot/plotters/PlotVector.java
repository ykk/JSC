package simulation.files.gnuplot.plotters;

import java.util.*;
import simulation.utilities.structures.*;

/** Class to plot vectors.
 * @author ykk
 */
public class PlotVector
    extends PlotTable
{
    //Methods
    /** Constructor.
     */
    public PlotVector()
    {
	super(null);
    }

    /** Constructor.
     * Adds index column by default.
     * @param vec reference to vector
     */
    public PlotVector(Vector vec)
    {
	super(new Table(vec));
	addIndex();
    }

    /** Add index column.
     */
    public void addIndex()
    {
	Vector tmp = new Vector();

	for (int i = 0; i < table.size(); i++)
	    tmp.add(new Integer(i));

	table.addCol(0,tmp);
    }

    /** Add vector.
     * @param vec reference to vector
     */
    public void addVector(Vector vec)
    {
	if (table.size() == 0)
	    table = new Table(vec);
	else
	    table.addCol(vec);
    }
}