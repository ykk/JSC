package simulation.files.images;

import java.util.*;
import java.awt.*;
import simulation.networks.*;

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
    public CoordinateImage(String filename, int imageFormat, int xSize, int ySize, int resolution)   
    {
	super(filename, imageFormat, xSize*resolution, ySize*resolution);
	this.resolution = resolution;
    }

    /** Draw vector of coordinates.
     */
    public void drawCoordinates(Vector coordinates, int nodeSize)
    {
	Coordinate tmpCoord;

	image.setBackground(Color.white);
	image.setColor(Color.blue);
	for (int i = 0; i < coordinates.size(); i++)
	{
	    tmpCoord = (Coordinate) coordinates.get(i);
	    image.fillArc((int) tmpCoord.x*resolution-(nodeSize/2),
			  (int) tmpCoord.y*resolution-(nodeSize/2),
			  nodeSize,nodeSize,0,0);
	}
    }
}
