package simulation.files.text;

import simulation.utilities.structures.*;

/** Sequential file in JAR to and from {@link Table} class.
 * @author ykk
 */
public class JarTable
    extends JarFile
{
    //Members
    /** Table to hold content. 
     */
    public Table content = new Table();
    /** Separator to use when reading file.
     * Defaulted to tab.
     */
    public String separator = "\t";

    //Methods
    /** Constructor new file in a vector.
     * @param filename name of resource
     */
    public JarTable(String filename)
    {
	super(filename);
    }

    /** Constructor new file in a vector.
     * @param filename name of file
     * @param separator separator to use when reading file.
     */
    public JarTable(String filename, String separator)
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