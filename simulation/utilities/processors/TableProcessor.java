package simulation.utilities.processors;

import java.util.*;
import simulation.utilities.structures.*;

/** Processor for table.
 * @see Table
 * @author ykk
 */
public class TableProcessor
{
    //Members
    /** Table to process.
     */
    public Table table;
    /** Operations used.
     */
    public static final int ADD = 0;
    public static final int SUBTRACT = 1;
    public static final int MULTIPLY = 2;
    public static final int DIVIDE = 3;

    //Methods
    /** Constructor for table processor.
     * @param table table to process
     */
    public TableProcessor(Table table)
    {
	this.table = table;
    }

    /** Return operation within single column of Double.
     * Operate using adjacent members of column.
     * @param index index of column
     * @param operation operation to perform
     * @see #ADD
     * @see #SUBTRACT
     */
    public Vector singColDoubleOperate(int index, int operation)
    {
	Vector ans = new Vector();
	double last, current;

	last = ((Double) table.getItem(0,index)).doubleValue();
	for (int i = 1; i < table.size(); i++)
	{
            current = ((Double) table.getItem(i,index)).doubleValue();
	    switch (operation)
	    {
	    case ADD:
		ans.add(new Double(last+current));
		break;
	    case SUBTRACT:
		ans.add(new Double(current-last));
		break;
	    default:
		throw new RuntimeException("Unknown operation "+operation+
					   " requested in "+this);
	    }
	    last = current;
	}

	return ans;
    }

    /** Return operation between columns of Double.
     * @param firstIndex index of first column
     * @param secondIndex index of second column
     * @param operation operation to perform
     * @see #ADD
     * @see #SUBTRACT
     * @see #MULTIPLY
     * @see #DIVIDE
     * @return operation of first column with second column
     */
    public Vector colDoubleOperate(int firstIndex, int secondIndex, 
				   int operation)
    {
	Vector ans = new Vector();
	double first, second;

	for (int i = 0; i < table.size(); i++)
	{
	    first = ((Double) table.getItem(i,firstIndex)).doubleValue();
            second = ((Double) table.getItem(i,secondIndex)).doubleValue();
	    switch (operation)
	    {
	    case ADD:
		ans.add(new Double(first+second));
		break;
	    case SUBTRACT:
                ans.add(new Double(first-second));
                break;
	    case MULTIPLY:
                ans.add(new Double(first*second));
                break;
	    case DIVIDE:
                ans.add(new Double(first/second));
                break;
	    default:
		throw new RuntimeException("Unknown operation "+operation+
					   " requested in "+this);
	    }
	}

	return ans;
    }

}
