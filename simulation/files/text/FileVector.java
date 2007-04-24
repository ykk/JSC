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
        FileOutputStream outfile;
        PrintWriter fileOutput;

        //Open File
        try
	{
	    outfile = new FileOutputStream(filename);
	} catch(FileNotFoundException errItem)
        {
            System.err.println(errItem.getMessage());
            return;
        }
        fileOutput = new PrintWriter(outfile);

        //Print File
        for (int i=0; i < content.size(); i++)
	    fileOutput.println(content.get(i));
	fileOutput.flush();

        //Close File
        try
        {
            outfile.close();
        } catch (IOException errItem)
        {
            System.err.println(errItem);
        }
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

    /** Read file into vector.
     */
    public void read()
    {
	String inString;
        FileInputStream infile;
        BufferedReader fileInput;
	
        //Open File
        try
	{
	    infile = new FileInputStream(filename);
        } catch (FileNotFoundException errItem)
        {
            System.err.println(errItem);
            return;
        }
        fileInput = new BufferedReader(new InputStreamReader(infile));
	
        //Read File
        content.clear();
        try
        {
            inString = fileInput.readLine();
        } catch (IOException errItem)
        {
            System.err.println(errItem);
            return;
        }
        while (inString != null)
        {
            content.add(inString);
            try
            {
                inString = fileInput.readLine();
            } catch (IOException errItem)
            {
                System.err.println(errItem);
            }
        }
	
	//Close File
        try
        {
            infile.close();
        } catch (IOException errItem)
        {
            System.err.println(errItem);
        }
    }
}
