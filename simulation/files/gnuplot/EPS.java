package simulation.files.gnuplot;

import java.io.*;
import simulation.files.text.*;

/** Class to generate GNUPlot input files for EPS files.
 * @author ykk
 */
public class EPS
    extends GnuPlot
{
    //Members
    /** Terminal set to EPS.
     */
    public String terminal = "postscript eps";
    /**
     */
    public boolean colored = true;

    //Methods
    /** Output terminal string.
     * @return set term string for
     */
    public String termString()
    {
	return "set term "+terminal+((colored)?" color":"");
    }

    /** Execute conversion to pdf.
     */
    public void convert()
    {
	String output;

	try
	{
	    Process process = Runtime.getRuntime().exec("epstopdf "+graphFilename);
	    BufferedReader outcome = new 
		BufferedReader(new InputStreamReader(process.getInputStream()));
	    while ((output = outcome.readLine()) != null)
		System.out.println(output);
	} catch (IOException err)
	{
	    System.err.println(this + " generates "+ err);
	}
    }

    /** Main function.
     */
    public static void main(String[] args)
    {
        GnuPlot gnuplot = new EPS();
        gnuplot.process(args).write();
    }
}
