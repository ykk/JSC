package simulation.utilities.system;

import java.io.*;

/** Class to execute external program.
 * @author ykk
 */
public class Execute
{
    /** Execute command.
     * @param cmd command to be run
     * @return result string
     */
    public static String execute(String cmd)
    {
	String output;
	String result = "";

	try
	{
	    Process process = Runtime.getRuntime().exec(cmd);
	    BufferedReader outcome = new 
		BufferedReader(new InputStreamReader(process.getInputStream()));
	    while ((output = outcome.readLine()) != null)
		result += output+"\n";
	} catch (IOException err)
	{
	    System.err.println("simulation.utilities.system.Execute generates "+ err);
	}

	return result;
    }    
}
