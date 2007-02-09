package simulation.files.gnuplot;

import simulation.files.text.*;

/** Class to generate GNUPlot input files for MetaPost.
 * @author ykk
 */
public class MetaPost
    extends GnuPlot
{
    //Members
    /** Terminal set to MetaPost.
     */
    public String terminal = "mp";

    /** Indicate if colored.
     * Defaulted to true.
     */
    public boolean colored = true;

    /** Indicate if using AMS TeX.
     * Defaulted to true.
     */
    public boolean amstex = true;

    /** Indicate aspect ratio for plot.
     * Defaulted to 4:3.
     */
    public String size = SIZE_4BY3;

    /** Aspect ratios available
     */
    public static final String SIZE_4BY3 = "1,1.25";
    public static final String SIZE_16BY9 = "1,1";

    //Methods
    /** Output MetaPost terminal string with options.
     * @return set term string for metapost
     */
    public String termString()
    {
	String options = "";
	options += (colored)?" color":" monochrome";
	if (amstex) options += " amstex";
	return "set term "+terminal+options;
    }

    /** Output terminal specific header.
     * @return header string
     */
    public String termHeader()
    {
        return super.termHeader()+"\nset size "+size;
    }

    /** Main function.
     */
    public static void main(String[] args)
    {
        GnuPlot gnuplot = new MetaPost();
        gnuplot.process(args).write();
    }
}
