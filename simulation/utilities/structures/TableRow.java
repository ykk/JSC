package simulation.utilities.structures;

import java.util.*;

/** Class to hold a row in a table.
 * @see Table
 * @author ykk
 */
public class TableRow
    extends Vector
{
    //Members
    /** Row separator.
     * Defaulted to tab.
     */
    public String separator="\t";

    //Methods
    /** Constructor of row from string, with separator.
     * @param string input string
     * @param separator separator to use
     */
    public TableRow(String string, String separator)
    {
	this.separator = separator;
	stringToRow(string);
    }

    /** Constructor of row from string.
     * @param string input string
     */
    public TableRow(String string)
    {
	stringToRow(string);
    }

    /** Function to parse string into row.
     * @param string string to parse
     */
    private void stringToRow(String string)
    {
	StringTokenizer tokens = new StringTokenizer(string,separator);

	while (tokens.hasMoreTokens())
	    this.add(tokens.nextToken());
    }

    /** Change item from string to long.
     * @param index index of item to convert
     */
    public void stringToLong(int index)
    {
	replace(index, new Long((String) get(index)));
    }

    /** Change item from string to double.
     * @param index index of item to convert
     */
    public void stringToDouble(int index)
    {
        replace(index, new Double((String) get(index)));
    }

    /** Change item from string to integer.
     * @param index index of item to convert
     */
    public void stringToInt(int index)
    {
        replace(index, new Integer((String) get(index)));
    }

    /** Replace item of specified index.
     * @param index index of item to replace
     * @param item object to return item with
     */
    public void replace(int index, Object item)
    {
	this.remove(index);
	this.add(index,item);
    }

    /** Print row with separator.
     * @return string with items in row separated by separator.
     */
    public String toString()
    {
	String str = new String();

	for (int i = 0; i < this.size(); i++)
	{
	    str += get(i);
	    if (i != (size()-1)) str += separator;
	}

	return str;
    }
}
