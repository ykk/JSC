package simulation.files.images;

import java.util.*;
import java.awt.*;
import simulation.networks.*;
import simulation.networks.areas.*;

/** Class to draw vector of coordinates.
 * @author ykk
 */
public class CoordinateImage
    extends ImageFile
{
    //Members
    /** Resolution of Image.
     * i.e., one unit in network to how many pixels
     */
    private int resolution;

    /** Size of a Node.
     * i.e., how many pixels the node spans.
     */
    private int nodeSize;

    /** Coordinate of origin.
     */
    private Coordinate origin;

    //Methods
     /** Create new image file to draw coordinates.
     * This will overwrite any existing file with the same name.
     * Uses 3 byte BGR Buffered image, i.e. represents an image 
     * with 8-bit RGB color components, corresponding to a 
     * Windows-style BGR color model) with the colors Blue, 
     * Green, and Red stored in 3 bytes.
     * @param filename name of image file
     * @param imageFormat format of image file
     * @param xSize size of x-coordinate, i.e, width
     * @param ySize size of y-coordinate, i.e, height
     * @param resolution resolution of image, i.e., one unit in network to how many pixels
     * @see ImageFile#imageFormat
     */
    public CoordinateImage(String filename, int imageFormat, NetworkArea netArea, int nodeSize, int resolution)   
    {
	super(filename, imageFormat,(int) Math.ceil((netArea.maxX()-netArea.minX())*resolution+nodeSize+1),(int) Math.ceil((netArea.maxX()-netArea.minX())*resolution+nodeSize+1));

	this.resolution = resolution;
	this.nodeSize = nodeSize;

	double originX = Math.ceil(-1*netArea.minX()*resolution+((double) nodeSize)/2);
	double originY = Math.ceil(-1*netArea.minY()*resolution+((double) nodeSize)/2);
	this.origin = new Coordinate(originX,originY);
    }

    /** Draw vector of coordinates.
     * @param coordinates vector of coordinates
     * @see Coordinate
     */
    public void drawCoordinates(Vector coordinates)
    {
	Coordinate tmpCoord;

	image.setBackground(Color.white);
	image.setColor(Color.blue);
	for (int i = 0; i < coordinates.size(); i++)
	{
	    tmpCoord = (Coordinate) coordinates.get(i);
	    image.fillArc((int) (tmpCoord.x*resolution+origin.x-(nodeSize/2)),
			  (int) (tmpCoord.y*resolution+origin.y-(nodeSize/2)),
			  nodeSize,nodeSize,0,0);
	}
    }
}
