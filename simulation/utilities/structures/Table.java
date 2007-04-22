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
    /** Constructor to create empty table.
     */
    public Table()
    {
    }

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

    /** Changed column from string to double.
     * @param index index of column
     */
    public void stringToDouble(int index)
    {
        for (int i = 0; i < size(); i++)
            get(i).stringToDouble(index);
    }

    /** Changed column from string to long.
     * @param index index of column
     */
    public void stringToLong(int index)
    {
        for (int i = 0; i < size(); i++)
            get(i).stringToLong(index);
    }

    /** Changed column from string to integer.
     * @param index index of column
     */
    public void stringToInt(int index)
    {
        for (int i = 0; i < size(); i++)
            get(i).stringToInt(index);
    }

    /** Add column to table with index specified.
     * @param index index to add column to
     * @param column column to add
     */
    public void addCol(int index, Vector column)
    {
	for (int i = 0 ; i < size(); i++)
	    get(i).add(index,column.get(i));
    }

    /** Add column to table.
     * @param column column to add
     */
    public void addCol(Vector column)
    {
        for (int i = 0 ; i < size(); i++)
            get(i).add(column.get(i));
    }

    /** Remove column from table.
     * @param index index of column to remove
     */
    public void removeCol(int col)
    {
        for (int i = 0 ; i < size(); i++)
            get(i).remove(col);
    }

    /** Create table from vector of strings.
     * @param stringVector vector of strings
     */
    private void vectorToTable(Vector stringVector)
    {
        for (int i = 0; i < stringVector.size(); i++)
            add(new TableRow((String) stringVector.get(i), separator));
    }

    /** Get column from table.
     * @param index index of column to grab
     * @return column of specified index
     */
    public Vector getCol(int index)
    {
	Vector col = new Vector();

	for (int i = 0; i < size(); i++)
	    col.add(getItem(i,index));

	return col;
    }

    /** Get row from table.
     * @param index index of row to grab
     * @return row of specified index
     */
    public TableRow get(int index)
    {
	return (TableRow) super.get(index);
    }

    /** Get maximum number of columns in all rows.
     * Returns Integer.MIN_VALUE for empty table.
     * @return maximum number of columns in all rows.
     */
    public int maxCol()
    {
	int max = Integer.MIN_VALUE;

	for (int i = 0 ; i < size(); i++)
            if(get(i).size() > max)
		max = get(i).size();

	return max;
    }

    /** Get minimum number of columns in all rows.
     * Returns Integer.MAX_VALUE for empty table.
     * @return minimum number of columns in all rows.
     */
    public int minCol()
    {
	int min = Integer.MAX_VALUE;

	for (int i = 0 ; i < size(); i++)
            if(get(i).size() < min)
		min = get(i).size();

	return min;
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
