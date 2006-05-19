package simulation.files.images;

import java.awt.*;
import simulation.utilities.structures.*;
import simulation.files.images.*;
import simulation.networks.nodes.*;
import simulation.networks.*;

/** Class to draw network and its routes.
 * @author ykk
 */
public class NetworkRouteImage
    extends NetworkImage
{
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
    public NetworkRouteImage(String filename, int imageFormat, Network network, int resolution, int nodeSize)   
    {
	super(filename, imageFormat, network, resolution, nodeSize);
    }

    /** Draw routing tree.
     * @param tree routing tree to draw
     */
    public void drawTreeArcs(RouteTree tree)
    {
	image.setColor(Color.red);

	Node tmpNode;
	for (int i = 0; i < tree.nodes.size(); i++)
	{
	    tmpNode = (Node) tree.nodes.get(i);
	    if (tree.parent(tmpNode) != null)
		drawArc(tree.parent(tmpNode), tmpNode);
	}
    }

    /** Draw network with routing tree.
     * @see #drawTreeArcs(RouteTree tree)
     * @see #drawPositions(Vector positions)
     */
    public void draw(RouteTree tree)
    {
	drawTreeArcs(tree);
	drawPositions(network.nodes);
	image.setColor(Color.red);	
	drawPosition(tree.root());
    }

}
