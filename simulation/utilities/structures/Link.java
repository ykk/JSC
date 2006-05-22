package simulation.utilities.structures;

import simulation.networks.nodes.*;

/** Class to store link.
 * @author ykk
 */
public class Link
{
    //Members
    /** Source node.
     */
    public Node source;
    /** Destination node.
     */
    public Node destination;

    //Members
    /** Constructor to create link.
     * @param source source node
     * @param destination destination node
     */
    public Link(Node source, Node destination)
    {
	this.source = source;
	this.destination = destination;
    }

    /** Return string representation.
     * @return string representation
     */
    public String toString()
    {
	return super.toString()+"\n("+source+"\n->"+destination+")";
    }   
}
