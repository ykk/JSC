package simulation.files.gnuplot;

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

    /** Main function.
     */
    public static void main(String[] args)
    {
        GnuPlot gnuplot = new EPS();
        gnuplot.process(args).write();
    }
}
