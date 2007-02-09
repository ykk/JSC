package simulation.utilities.structures;

import java.util.*;

/** Class to hold a table.
 * @see TableRow
 * @author ykk
 */
public class Table
    extends Vector
{
    //Members
    /** Row separator.
     * Defaulted to tab.
     */
    private String separator="\t";

    //Methods
    /** Constructor to create table from vector of strings with separator.
     * @param stringVector vector of strings
     * @param separator separator for strings
     */
    public Table(Vector stringVector, String separator)
    {
	this.separator = separator;
        vectorToTable(stringVector);
    }

    /** Constructor to create table from vector of strings.
     * @param stringVector vector of strings
     */
    public Table(Vector stringVector)
    {
	vectorToTable(stringVector);
    }

    /** Changed separator for table.
     * @param separator new separator
     */
    public void changeSeparator(String separator)
    {
	this.separator = separator;
	for (int i = 0; i < size(); i++)
	    ((TableRow) get(i)).separator = separator;
    }

    /** Create table from vector of strings.
     * @param stringVector vector of strings
     */
    private void vectorToTable(Vector stringVector)
    {
        for (int i = 0; i < stringVector.size(); i++)
            add(new TableRow((String) stringVector.get(i), separator));
    }

    /** Get row from table.
     * @param index index of row to grab
     * @return row of specified index
     */
    public TableRow get(int index)
    {
	return (TableRow) super.get(index);
    }

    /** Get item from table.
     * @param row index of row
     * @param column index of column
     * @return item of specified position in table
     */
    public Object getItem(int row, int column)
    {
	return ((TableRow) this.get(row)).get(column);
    }
}
