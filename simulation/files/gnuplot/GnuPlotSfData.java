package simulation.files.gnuplot;

/** Class to represent data file to surface plot.
 * @author ykk
 */
public class GnuPlotSfData
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
    /** Index of z data.
     */
    public int zIndex;
    /** Title of data set.
     */
    public String name = null;
    /** Plot style.
     * Defaults to lines.
     * @see #STYLE_LINES
     */
    public String plotStyle = STYLE_LINES;
    /** Various plot styles
     */
    public static final String STYLE_DOTS = "dots";
    public static final String STYLE_IMPULSES = "impulses";
    public static final String STYLE_POINTS = "points";
    public static final String STYLE_LINES = "lines";
    public static final String STYLE_LINESPOINT = "linespoint";
    /** Point size.
     * -1 for default.
     */
    public double pointSize = -1.0;
    /** Point type.
     * -1 for default.
     */
    public int pointType = -1;
    /** Line width.
     * -1 for default.
     */
    public double lineWidth = -1.0;
    /** Line type.
     * -1 for default.
     */
    public int lineType = -1;

    //Methods
    /** Constructor.
     * @param filename filename of data set
     * @param xIndex index to plot as x-axis
     * @param yIndex index to plot as y-axis
     */
    public GnuPlotSfData(String filename,int xIndex, int yIndex, int zIndex)
    {
	this.filename = filename;
	this.xIndex = xIndex;
	this.yIndex = yIndex;
	this.zIndex = zIndex;
    }

    /** Constructor.
     * @param filename filename of data set
     * @param xIndex index to plot as x-axis
     * @param yIndex index to plot as y-axis
     * @param name name of data set
     */
    public GnuPlotSfData(String filename,int xIndex, int yIndex, int zIndex, String name)
    {
	this.filename = filename;
	this.xIndex = xIndex;
	this.yIndex = yIndex;
	this.zIndex = zIndex;
	this.name = name;
    }

    /** Output string to plot data.
     * @return string to add to plot command.
     */
    public String toString()
    {
	String pString = "\'"+filename+"\'"+" using "+xIndex+":"+yIndex+":"+zIndex+" with "+plotStyle;
	if (lineType != -1) pString += " lt "+lineType;
	if (lineWidth != -1) pString += " lw "+lineWidth;
	if (pointType != -1) pString += " pt "+pointType;
	if (pointSize != -1) pString += " ps "+pointSize;
	if (name != null) pString +=" title \'"+name+"\'";
	return pString;
    }
}
