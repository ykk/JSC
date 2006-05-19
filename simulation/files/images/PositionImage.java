package simulation.files.images;

import java.util.*;
import java.awt.*;
import simulation.networks.*;
import simulation.networks.areas.*;
import simulation.files.images.*;

/** Class to draw vector of Positionables.
 * @author ykk
 */
public class PositionImage
    extends ImageFile
{
    //Members
    /** Resolution of Image.
     * i.e., one unit in network to how many pixels
     */
    protected int resolution;

    /** Size of a Node.
     * i.e., how many pixels the node spans.
     */
    protected int nodeSize;

    /** Coordinate of origin.
     */
    protected Coordinate origin;

    //Methods
     /** Create new image file to draw {@link Positionable}.
     * This will overwrite any existing file with the same name.
     * Uses 3 byte BGR Buffered image, i.e. represents an image 
     * with 8-bit RGB color components, corresponding to a 
     * Windows-style BGR color model) with the colors Blue, 
     * Green, and Red stored in 3 bytes.
     * @param filename name of image file
     * @param imageFormat format of image file
     * @param netArea network area definition
     * @param resolution resolution of image, i.e., one unit in network to how many pixels
     * @param nodeSize size of node in image (in terms of pixels)
     * @see ImageFile#imageFormat
     */
    public PositionImage(String filename, int imageFormat, NetworkArea netArea, int resolution, int nodeSize)   
    {
	super(filename, imageFormat,(int) Math.ceil((netArea.maxX()-netArea.minX())*resolution+nodeSize),(int) Math.ceil((netArea.maxY()-netArea.minY())*resolution+nodeSize));

	image.setColor(Color.white);
	image.fillRect(0,0,(int) Math.ceil((netArea.maxX()-netArea.minX())*resolution+nodeSize),(int) Math.ceil((netArea.maxY()-netArea.minY())*resolution+nodeSize));

	this.resolution = resolution;
	this.nodeSize = nodeSize;

	double originX = -1*netArea.minX()*resolution;
	double originY = -1*netArea.minY()*resolution;
	this.origin = new Coordinate(originX,originY);
    }

    /** Draw vector of positions.
     * @param positions vector of Positionable
     * @see Positionable
     */
    public void drawPositions(Vector positions)
    {
	Positionable tmpCoord;

	image.setColor(Color.blue);
	for (int i = 0; i < positions.size(); i++)
	    drawPosition((Positionable) positions.get(i));

	write();
    }

    /** Draw position.
     * @param position Positionable to draw
     */
    public void drawPosition(Positionable position)
    {
	image.fillArc((int) (position.x()*resolution+origin.x),
		      (int) (position.y()*resolution+origin.y),
		      nodeSize,nodeSize,0,360);	
    }
}
