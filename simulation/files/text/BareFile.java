package simulation.files.text;

import java.util.*;
import java.io.*;

public class BareFile
{
    //Members
    /** Type of file.
     */
    private int fileType;
    /** Types of file.
     */
    public static final int FILE_WRITE = 0;
    public static final int FILE_READ = 1;
    /** Filename
     */
    private String filename;
    /** Reference to output file.
     */
    private FileOutputStream outfile;
    /** Reference to print writer.
     * Default to null.
     */
    private PrintWriter writer = null;
    /** Reference to input file.
     */
    private FileInputStream infile;
    /** Reference to file reader.
     */
    BufferedReader reader = null;

    //Methods
    /** Constructor.
     * @param filename name of file
     * @param type type of file (read/write)
     */
    public BareFile(String filename, int type)
    {
	this.filename = filename;
	this.fileType = type;

	switch (fileType)
	{
	case FILE_WRITE:
	    //Open File
	    try
	    {
		outfile = new FileOutputStream(filename);
	    } catch(FileNotFoundException errItem)
	    {
		System.err.println(errItem.getMessage());
		return;
	    }
	    writer = new PrintWriter(outfile);
	    break;
	case FILE_READ:
	    //Open File
	    try
	    {
		infile = new FileInputStream(filename);
	    } catch (FileNotFoundException errItem)
	    {
		System.err.println(errItem);
		return;
	    }
	    reader = new BufferedReader(new InputStreamReader(infile));
	    break;
	default:
	    throw new RuntimeException("Unknown file type "+fileType);
	}
    }

    /** Close file.
     */
    public void close()
    {
	switch (fileType)
	{
	case FILE_WRITE:
	    writer.flush();

	    //Close File
	    try
	    {
		outfile.close();
	    } catch (IOException errItem)
	    {
		System.err.println(errItem);
	    }
	    break;
	case FILE_READ:
	    try
	    {
		infile.close();
	    } catch (IOException errItem)
	    {
		System.err.println(errItem);
	    }
	    break;
	default:
	    throw new RuntimeException("Unknown file type "+fileType);
	}
    }

    /** Read from file.
     * Return null if end of file.
     * @return line from file
     */
    public String readLine()
    {
	String inString = null;

	if (fileType == FILE_WRITE)
	    throw new RuntimeException("Reading from write-only file "+filename+"!");
	else
            try
            {
		inString = reader.readLine();
	    } catch (IOException errItem)
	    {
		System.err.println(errItem);
            }

	return inString;
    }

    /** Print string to filename.
     * @param string string to print
     */
    public void println(String string)
    {
	if (fileType == FILE_READ)
	    throw new RuntimeException("Writing to read-only file "+filename+"!");
	else
	    writer.println(string);
    }

    /** Print object to filename.
     * @param object object to print
     */
    public void println(Object object)
    {
	if (fileType == FILE_READ)
	    throw new RuntimeException("Writing to read-only file "+filename+"!");
	else
	    writer.println(object);
    }

    /** Check if file exist.
     */
    public boolean exist()
    {
        FileInputStream infile;

        try
	{
	    infile = new FileInputStream(filename);
        } catch (FileNotFoundException errItem)
        {
            return false;
        }

	//Close File
        try
        {
            infile.close();
        } catch (IOException errItem)
        {
            System.err.println(errItem);
        }

	return true;
    }
}
