package simulation.files.graphviz;

/** Class for representing edges in Graphviz graphs.
 * @author ykk
 */
public class GraphEdge
    implements Comparable
{
    //Members
    /** Head end of edge.
     */
    public String head;
    /** Tail end of edge.
     */
    public String tail;
    /** Filled color
     */
    public String fillColor = null;
    /** Color of node.
     */
    public String color = null;

    //Methods
    /** Constructor.
     * @param head head end of the edge
     * @param tail tail end of the edge
     */
    public GraphEdge(String head, String tail)
    {
	this.head = head;
	this.tail = tail;
    }

    /** Return string for undirected edge.
     * @return graphviz representation of undirected edge
     */
    public String undirectedString()
    {
	return "\""+head+"\"--\""+tail+"\" "+optionString();
    }

    /** Return string for directed edge.
     * @return graphviz representation of directed edge
     */
    public String directedString()
    {
	return "\""+head+"\"->\""+tail+"\" "+optionString();
    }

    public String toString()
    {
	return head+"-"+tail+" "+optionString();
    }
    
    /** Comparable interface.
     * Compare strings for head and tail if GraphEdge,
     * else return -1.
     * @return 0 if GraphEdge and same, else -1
     */
    public int compareTo(Object object)
    {
	if ((object instanceof GraphEdge) &&
	    head.compareTo(((GraphEdge) object).head) == 0 &&
	    tail.compareTo(((GraphEdge) object).tail) == 0)
	    return 0;
	else
	    return -1;
    }

    /** Return option string.
     * @return string encoding options
     */
    public String optionString()
    {
	String tmp = new String();
	if (fillColor != null) tmp += "fillcolor=" + fillColor;
	if (color != null)
	{
	    if (tmp.length() != 0) tmp += ",";
	    tmp += "color=" + color;
	}
	if (tmp.length() == 0)
	    return "";
	else
	    return "["+tmp+"]";
    }
}