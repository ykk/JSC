package simulation.files.images;

import java.util.*;
import java.awt.*;
import simulation.files.images.*;
import simulation.networks.*;
import simulation.networks.nodes.*;
import simulation.networks.channels.*;

/** Class to draw a network.
 * @author ykk
 */
public class NetworkImage
    extends PositionImage
{
    //Members
    protected Network network;

    //Methods
     /** Create new image file to draw {@link Network}.
     * This will overwrite any existing file with the same name.
     * Uses 3 byte BGR Buffered image, i.e. represents an image 
     * with 8-bit RGB color components, corresponding to a 
     * Windows-style BGR color model) with the colors Blue, 
     * Green, and Red stored in 3 bytes.
     * @param filename name of image file
     * @param imageFormat format of image file
     * @param network network to draw
     * @param resolution resolution of image, i.e., one unit in network to how many pixels
     * @param nodeSize size of node in image (in terms of pixels)
     * @see ImageFile#imageFormat
     */
    public NetworkImage(String filename, int imageFormat, Network network, int resolution, int nodeSize)   
    {
	super(filename, imageFormat, network.netArea, resolution, nodeSize);
	this.network = network;
    }

    /** Draw network.
     * @see #drawArcs(Vector nodes)
     * @see #drawPositions(Vector positions)
     */
    public void draw()
    {
	drawArcs(network.nodes);
	drawPositions(network.nodes);
    }

    /** Draw arcs from nodes to nodes it is able to communicate with.
     * Will only {@link #drawTransmitArcs(Vector nodes)} if channel is symmetrical.
     * @param nodes vector of nodes
     * @see Node
     * @see #drawTransmitArcs(Vector nodes)
     * @see #drawReceiveArcs(Vector nodes)
     */
    public void drawArcs(Vector nodes)
    {
	drawTransmitArcs(nodes);
	if (nodes.size() != 0)
	    if (!(((Node) nodes.get(0)).channel instanceof SymmetryChannel))
		drawReceiveArcs(nodes);
    }

    /** Draw arcs from nodes to nodes it is able to receive from (in green).
     * @param nodes vector of nodes
     * @see Node
     */
    public void drawReceiveArcs(Vector nodes)
    {
	Node tmpNode, tmpRxPartner;

	image.setColor(Color.green);
	for (int i = 0; i < nodes.size(); i++)
	{
	    tmpNode = (Node) nodes.get(i);
	    for (int j = 0; j < tmpNode.receivePartners.size(); j++)
	    {
		tmpRxPartner = (Node) tmpNode.receivePartners.get(j);
		image.drawLine((int) (tmpNode.x()*resolution+origin.x+(((double) nodeSize)/2)),
			       (int) (tmpNode.y()*resolution+origin.y+(((double) nodeSize)/2)+1),
			       (int) (tmpRxPartner.x()*resolution+origin.x+(((double) nodeSize)/2)),
			       (int) (tmpRxPartner.y()*resolution+origin.y+(((double) nodeSize)/2)+1));
	    }
	}
    }

    /** Draw arcs from nodes to nodes it is able to transmit to (in red).
     * @param nodes vector of nodes
     * @see Node
     */
    public void drawTransmitArcs(Vector nodes)
    {
	Node tmpNode, tmpTxPartner;

	image.setColor(Color.red);
	for (int i = 0; i < nodes.size(); i++)
	{
	    tmpNode = (Node) nodes.get(i);
	    for (int j = 0; j < tmpNode.transmitPartners.size(); j++)
	    {
		tmpTxPartner = (Node) tmpNode.transmitPartners.get(j);
		drawArc(tmpNode, tmpTxPartner);
	    }
	}
    }

    /** Draw arc.
     * @param fromNode node to start line from
     * @param toNode node to end line at
     */
    public void drawArc(Node fromNode, Node toNode)
    {
	image.drawLine((int) (fromNode.x()*resolution+origin.x+(((double) nodeSize)/2)),
		       (int) (fromNode.y()*resolution+origin.y+(((double) nodeSize)/2)),
		       (int) (toNode.x()*resolution+origin.x+(((double) nodeSize)/2)),
		       (int) (toNode.y()*resolution+origin.y+(((double) nodeSize)/2)));
    }
}
