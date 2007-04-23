package simulation.files.text;

import simulation.utilities.structures.*;

/** Sequential File to and from {@link Table} class.
 * @author ykk
 */
public class FileTable
    extends FileVector
{
    //Members
    /** Table to hold content. 
     */
    public Table content;
    /** Separator to use when reading file.
     * Defaulted to tab.
     */
    public String separator = "\t";

    //Methods
    /** Constructor new file in a vector.
     * @param filename name of file
     */
    public FileTable(String filename)
    {
	super(filename);
    }

    /** Constructor new file in a vector.
     * @param filename name of file
     * @param separator separator to use when reading file.
     */
    public FileTable(String filename, String separator)
    {
	super(filename);
	this.separator = separator;
    }

    /** Read file into table.
     */
    public void read()
    {
	super.read();
	this.content = new Table(super.content, separator);
    }
}