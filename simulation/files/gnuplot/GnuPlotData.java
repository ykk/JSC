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
    /** Various plot styles
     */
    public static final String STYLE_PM3D = "pm3d";
    public static final String STYLE_VECTORS = "vectors";
    public static final String STYLE_FINANCEBARS = "financebars";
    public static final String STYLE_CANDLESTICKS = "candlesticks";
    public static final String STYLE_FILLEDCURVES = "filledcurves";
    public static final String STYLE_BOXES = "boxes";
    public static final String STYLE_HISTEPS = "histeps";
    public static final String STYLE_FSTEPS = "fsteps";
    public static final String STYLE_DOTS = "dots";
    public static final String STYLE_STEPS = "steps";
    public static final String STYLE_IMPULSES = "impulses";
    public static final String STYLE_POINTS = "points";
    public static final String STYLE_LINES = "lines";
    public static final String STYLE_LINESPOINT = "linespoint";
    /** Plot style.
     * Defaults to linespoint.
     * @see #STYLE_LINESPOINT
     */
    public String plotStyle = STYLE_LINESPOINT;
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
    /** x-axis to plot using.
     * Defaulted to 1 and can be set to 2.
     */
    public int xaxis = 1;
    /** y-axis to plot using.
     * Defaulted to 1 and can be set to 2.
     */
    public int yaxis = 1;

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
	if (lineType != -1) pString += " lt "+lineType;
	if (lineWidth != -1) pString += " lw "+lineWidth;
	if (pointType != -1) pString += " pt "+pointType;
	if (pointSize != -1) pString += " ps "+pointSize;
	if (xaxis != 1 || yaxis != 1) pString += " axes x"+xaxis+"y"+yaxis;
	if (name != null) pString +=" title \'"+name+"\'";
	return pString;
    }
}
