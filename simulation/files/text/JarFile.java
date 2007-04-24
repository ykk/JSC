package simulation.files.text;

import java.util.*;
import java.io.*;

/** Sequential file read from the JAR archive.
 * @author ykk
 */
public class JarFile
    extends FileVector
{
    /** Constructor for new resource into a vector.
     * @param filename name of resource
     */
    public JarFile(String filename)
    {
	super(filename);
    }

    /** Test function to read file in JAR.
     * @param args path and name of resource
     */
    public static void main(String[] args)
    {
	JarFile file = new JarFile(args[0]);
	file.read();
	for (int i = 0; i < file.content.size(); i++)
	    System.out.println(file.content.get(i));
    }

    /** Read file into vector.
     */
    public void read()
    {
	String inString;
        InputStream infile;
        BufferedReader fileInput;
	
        //Open File
        try
	{
	    infile = this.getClass().getResourceAsStream(filename);
        } catch (Exception errItem)
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

    /** Write vector into file.
     * Overloaded with dummy function since file cannot be written to.
     */
    public void write()
    {
    }

    /** Check if file exist.
     * Overloaded with dummy function that returns true since even if file does not exist,
     * there is nothing to be done.
     */
    public boolean exist()
    {
	return true;
    }
}
