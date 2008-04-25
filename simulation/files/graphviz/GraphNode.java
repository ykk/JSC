package simulation.files.graphviz;

/** Class for representing edges in Graphviz graphs.
 * @author ykk
 */
public class GraphNode
{
    //Members
    /** String describing graph node.
     */
    public String descriptor;
    /** Shape of node.
     * @see #SHAPE_POINT
     * @see #SHAPE_BOX
     * @see #SHAPE_CIRCLE
     * @see #SHAPE_2CIRCLE
     * @see #SHAPE_ELLIPSE
     * @see #SHAPE_TEXT
     * @see #SHAPE_NONE
     * @see #SHAPE_TRIANGLE
     * @see #SHAPE_OCTAGON
     * @see #SHAPE_2OCTAGON
     * @see #SHAPE_3OCTAGON
     */
    public String shape = null;
    public static final String SHAPE_POINT="point";
    public static final String SHAPE_BOX="box";
    public static final String SHAPE_TRIANGLE="triangle";
    public static final String SHAPE_INVERSE_TRIANGLE="invtriangle";
    public static final String SHAPE_OCTAGON="octagon";
    public static final String SHAPE_2OCTAGON="doubleoctagon";
    public static final String SHAPE_3OCTAGON="tripleoctagon";
    public static final String SHAPE_CIRCLE="circle";
    public static final String SHAPE_2CIRCLE="doublecircle";
    public static final String SHAPE_ELLIPSE="ellipse";
    public static final String SHAPE_TEXT="plaintext";
    public static final String SHAPE_NONE="none";
    /** Style of node.
     * Can be concatenated with a comma in between.
     * @see #STYLE_FILLED
     * @see #STYLE_BOLD
     * @see #STYLE_INVISIBLE
     * @see #STYLE_ROUNDED
     */
    public String style = null;
    public static final String STYLE_FILLED="filled";
    public static final String STYLE_BOLD="bold";
    public static final String STYLE_INVISIBLE="invisible";
    public static final String STYLE_ROUNDED="rounded";
    /** Filled color
     */
    public String fillColor = null;
    /** Color of node.
     */
    public String color = null;
    /** Label for node.
     */
    public String label = null;

    //Methods
    /** Constructor
     * @param descriptor string of description
     */
    public GraphNode(String descriptor)
    {
	this.descriptor = descriptor;
    }

    /** Node without quotes.
     * @return string without quotes around descriptor.
     */
    public String quotelessString()
    {
	return descriptor+" "+optionString();
    }

    /** String representation.
     * @return string with descriptor in quotes
     */
    public String toString()
    {
	return "\""+descriptor+"\" "+optionString();
    }

    /** Return option string.
     * @return string encoding options
     */
    public String optionString()
    {
	String tmp = new String();
	if (shape != null) tmp += "shape="+shape;
	if (style != null)
	{
	    if (tmp.length() != 0) tmp += ",";
	    tmp += "style=\"" + style + "\"";
	}
	if (fillColor != null)
	{
	    if (tmp.length() != 0) tmp += ",";
	    tmp += "fillcolor=" + fillColor;
	}
	if (color != null)
	{
	    if (tmp.length() != 0) tmp += ",";
	    tmp += "color=" + color;
	}
	if (label != null)
	{
	    if (tmp.length() != 0) tmp += ",";
	    tmp += "label=" + label;
	}
	if (tmp.length() == 0)
	    return "";
	else
	    return "["+tmp+"]";
    }
}