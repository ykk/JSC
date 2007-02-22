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
    /** Title of data set.
     */
    public String name = null;
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
     * @param filename filename of data set
     * @param xIndex index to plot as x-axis
     * @param yIndex index to plot as y-axis
     */
    public GnuPlotData(String filename,int xIndex, int yIndex)
    {
	this.filename = filename;
	this.xIndex = xIndex;
	this.yIndex = yIndex;
    }

    /** Constructor.
     * @param filename filename of data set
     * @param xIndex index to plot as x-axis
     * @param yIndex index to plot as y-axis
     * @param name name of data set
     */
    public GnuPlotData(String filename,int xIndex, int yIndex, String name)
    {
	this.filename = filename;
	this.xIndex = xIndex;
	this.yIndex = yIndex;
	this.name = name;
    }

    /** Output string to plot data.
     * @return string to add to plot command.
     */
    public String toString()
    {
	String pString = "\'"+filename+"\'"+" using "+xIndex+":"+yIndex+" with "+plotStyle;
	if (name != null) pString +=" title \'"+name+"\'";
	return pString;
    }
}
