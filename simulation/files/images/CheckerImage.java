package simulation.files.images;

import java.util.*;
import java.awt.*;
import simulation.networks.*;
import simulation.networks.areas.*;
import simulation.files.images.*;

/** Class to draw checker boxes.
 * @author ykk
 */
public class CheckerImage
    extends ImageFile
{
    //Members
    /** Styles.
     * Default is {@link #SHADE_STYLE}.
     */
    public int style = SHADE_STYLE;
    public static final int SHADE_STYLE = 0;
    public static final int BAR_STYLE = 1;
    public static final int CIRCLE_STYLE = 2;
    /** Invert shade.
     * The default is the higher value get whiter.
     */
    public boolean getDarker = false;
    /** Maximum value for color normalization.
     * Defaulted to 1.0;
     */
    public double valueMax = 1.0;
    /** Minimum value for color normalization.
     * Defaulted to 0.0;
     */
    public double valueMin = 0.0;
    /** Checker values.
     */
    public double[][] values;
    /** Resolution of Image.
     * i.e., one unit in network to how many pixels, which is also size of box
     */
    protected int resolution;

    //Methods
     /** Create new image file to draw checker.
     * This will overwrite any existing file with the same name.
     * Uses 3 byte BGR Buffered image, i.e. represents an image 
     * with 8-bit RGB color components, corresponding to a 
     * Windows-style BGR color model) with the colors Blue, 
     * Green, and Red stored in 3 bytes.
     * @param filename name of image file
     * @param imageFormat format of image file
     * @param resolution resolution of image, i.e., one unit in network to how many pixels
     * @param width width of checker in number of boxes
     * @param height height of checker in number of boxes
     * @see ImageFile#imageFormat
     */
    public CheckerImage(String filename, int imageFormat, int resolution, int width, int height)
    {
	super(filename, imageFormat, resolution*width, resolution*height);
	values = new double[width][height];
	this.resolution = resolution;
    }

    /** Draw checker box.
     */
    public void draw()
    {
	for (int i = 0; i < values.length; i++)
	    for (int j = 0; j < values[0].length; j++)
		drawBox(i,j);

	write();
    }

    /** Draw single checker box.
     * @param x x coordinate
     * @param y y coordinate
     */
    public void drawBox(int x, int y)
    {
	//Scaling
	float shade = (float) ((values[x][y]-valueMin)/(valueMax-valueMin));

	//Color background and set color
	if (getDarker)
	    image.setColor(Color.white);
	else
	    image.setColor(Color.black);
	image.fillRect(x*resolution, y*resolution, resolution, resolution);
	if (getDarker)
	    image.setColor(Color.black);
	else
	    image.setColor(Color.white);

	//Actual drawing
	switch (style)
	{
	case SHADE_STYLE:
	    if (getDarker)
		shade = 1 - shade;
	    image.setColor(new Color(shade, shade, shade));
	    image.fillRect(x*resolution, y*resolution, resolution, resolution);
	    break;
	case BAR_STYLE:
	    image.fillRect(x*resolution, y*resolution, resolution, 
			   (int) Math.ceil(shade*((double) resolution)));
	    break;
	case CIRCLE_STYLE:
	    float half = (float) (0.5 * ((float) resolution));
	    shade = (float) (shade * (float) resolution);
	    image.fillArc((int) (x*resolution + half - 0.5 * shade), 
			  (int) (y*resolution + half - 0.5 * shade), 
			  (int) shade, (int) shade, 0, 360);	    
	    break;
	default:
	    throw new RuntimeException("Unknown checker style "+style);
	}
    }
}