package simulation.files.gnuplot;

/** Class to represent data file to plot.
 * @author ykk
 */
public class GnuPlotData
{
    //Members
    /** Data filename.
     */
    public String filename;
    /** Index of x data.
     */
    public int xIndex;
    /** Index of y data.
     */
    public int yIndex;
    /** Plot style.
     * Defaults to linespoint.
     * @see #STYLE_LINEPOINTS
     */
    public String plotStyle = STYLE_LINESPOINT;
    /** Various plot styles
     */
    public static final String STYLE_LINESPOINT = "linespoint";

    //Methods
    /** Constructor.
     */
    public GnuPlotData(String filename,int xIndex, int yIndex)
    {
	this.filename = filename;
	this.xIndex = xIndex;
	this.yIndex = yIndex;
    }

    /** Output string to plot data.
     * @return string to add to plot command.
     */
    public String toString()
    {
	return "\'"+filename+"\'"+
	    " using "+xIndex+":"+yIndex+
	    " with "+plotStyle;
    }
}
