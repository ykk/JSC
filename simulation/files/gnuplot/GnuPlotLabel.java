package simulation.files.gnuplot;

/** Class to represent labels in a plot.
 * @author ykk
 */
public class GnuPlotLabel
{
    //Members
    /** x coordinate.
     */
    public double x;
    /** y coordinate.
     */
    public double y;
    /** Label.
     */
    public String label;

    //Methods
    /** Constructor.
     * @param x x coordinate
     * @param y y coordinate
     * @param label label to put at coordinate
     */
    public GnuPlotLabel(double x, double y, String label)
    {
	this.x = x;
	this.y = y;
	this.label = label;
    }

    /** String representation.
     * @return string representation of label
     */
    public String toString()
    {
	return "label '"+label+"' at "+x+","+y;
    }
}