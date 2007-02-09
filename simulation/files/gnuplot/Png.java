package simulation.files.gnuplot;

import simulation.files.text.*;

/** Class to generate GNUPlot input files for PNG files.
 * @author ykk
 */
public class Png
    extends GnuPlot
{
    //Members
    /** Terminal set to PNG.
     */
    public String terminal = "png";

    //Methods
    /** Output terminal string.
     * @return set term string for
     */
    public String termString()
    {
	return "set term "+terminal;
    }

    /** Main function.
     */
    public static void main(String[] args)
    {
        GnuPlot gnuplot = new Png();
        gnuplot.process(args).write();
    }
}
