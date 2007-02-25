package simulation.files.gnuplot;

/** Class to represent data file to plot with y-error bars.
 * @author ykk
 */
public class GnuPlotDataYError
    extends GnuPlotData
{
    //Members
    /** Title for error bar.
     */
    public String errName = null;
    /** Index of lower error bar.
     */
    public int yLowIndex = 0;
    /** Index of upper error bar.
     */
    public int yHighIndex = 0;

    //Methods
    /** Constructor.
     * @param filename filename of data set
     * @param xIndex index to plot as x-axis
     * @param yIndex index to plot as y-axis
     * @param yLowIndex index to plot y-lower error bar
     * @param yHighIndex index to plot y-upper error bar
     */
    public GnuPlotDataYError(String filename,int xIndex, int yIndex, 
		       int yLowIndex, int yHighIndex)
    {
	super(filename, xIndex, yIndex);
	this.yLowIndex = yLowIndex;
	this.yHighIndex = yHighIndex;	
    }

    /** Constructor.
     * @param filename filename of data set
     * @param xIndex index to plot as x-axis
     * @param yIndex index to plot as y-axis
     * @param yLowIndex index to plot y-lower error bar
     * @param yHighIndex index to plot y-upper error bar
     * @param name name of data set
     */
    public GnuPlotDataYError(String filename,int xIndex, int yIndex, 
		       int yLowIndex, int yHighIndex, String name)
    {
	super(filename, xIndex, yIndex, name);
	this.yLowIndex = yLowIndex;
	this.yHighIndex = yHighIndex;
    }

    /** Output string to plot data.
     * @return string to add to plot command.
     */
    public String toString()
    {
	String pString = ""; 
	if (yLowIndex != yHighIndex)
	    pString += "\'"+filename+"\'"+" using "+xIndex+":"+yIndex+":"+
		yLowIndex+":"+yHighIndex+" with yerrorbars";
	else
	    pString += "\'"+filename+"\'"+" using "+xIndex+":"+yIndex+":"+
		yLowIndex+" with yerrorbars";
	    
	if (errName == null)
	{
	    if (name != null) 
		if (yLowIndex != yHighIndex)
		    pString +=" title \'"+name+" Error Bars\'";
		else
		    pString +=" title \'"+name+" Std Dev\'";
	}
	else
	    pString +=" title \'"+errName+"\'";
	    
	return pString+", "+super.toString();
    }
}