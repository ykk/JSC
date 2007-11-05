package simulation.files.text;

import java.util.*;
import java.io.*;

/** Sequential File to and from Vector class.
 * @author ykk
 */
public class FileVector
{
    //Members
    /** Vector to hold sequential file content.
     */
    public Vector content = new Vector();
    /** Filename.
     */
    public String filename;

    //Methods
    /** Constructor for new file into a vector.
     * @param filename name of file
     */
    public FileVector(String filename)
    {
	this.filename = filename;
    }

    /** Write vector into file.
     */
    public void write()
    {
        String outString;
	BareFile fileOutput = new BareFile(filename, BareFile.FILE_WRITE);

        //Print File
        for (int i=0; i < content.size(); i++)
	    fileOutput.println(content.get(i));
	fileOutput.close();
    }

    /** Check if file exist.
     */
    public boolean exist()
    {
        BareFile testFile = new BareFile(filename, BareFile.FILE_READ);
	return testFile.exist();
    }

    /** Read file into vector.
     */
    public void read()
    {
	String inString;
	BareFile fileInput = new BareFile(filename, BareFile.FILE_READ);
	
        //Read File
        content.clear();
	inString = fileInput.readLine();
        while (inString != null)
        {
            content.add(inString);
	    inString = fileInput.readLine();
        }
	fileInput.close();
    }

    /** Append vector to file.
     * @param vector vector to append
     */
    public void append(Vector vector)
    {
	for (int i = 0; i < vector.size(); i++)
	    content.add(vector.get(i));
    }

    /** Parse string into vector.
     * @param string string to be parsed
     * @param separator separator to use
     */
    public void parseString(String string, String separator)
    {
	StringTokenizer tokens = new StringTokenizer(string,separator);

	while (tokens.hasMoreTokens())
	    content.add(tokens.nextToken());
    }

}
