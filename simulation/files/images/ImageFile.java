package simulation.files.images;

import javax.imageio.*;
import java.io.*;
import java.awt.*;
import java.awt.image.*;

/** Basic class for handling image files.
 * @author ykk
 */
public class ImageFile
{
    //Members
    /** Type of files.
     */
    private static final String[] types = {"BMP", "JPEG", 
					   "PNG", "PNM", 
					   "TIFF", "WBMP"};
    /** BMP file type.
     */
    public static final int BMP_TYPE = 0;
    /** JPEG/JPG file type.
     */
    public static final int JPEG_TYPE = 1;
    /** PNG file type.
     */
    public static final int PNG_TYPE = 2;
    /** PNM file type.
     */
    public static final int PNM_TYPE = 3;
    /** TIFF file type.
     */
    public static final int TIFF_TYPE = 4;
    /** WBMP file type.
     */
    public static final int WBMP_TYPE = 5;

    /** Format of image.
     * @see #BMP_TYPE
     * @see #JPEG_TYPE
     * @see #PNG_TYPE
     * @see #PNM_TYPE
     * @see #TIFF_TYPE
     * @see #WBMP_TYPE
     */
    public int imageFormat;

    /** Handler to file.
     */
    public File file;

    /** Bufferred image associated with file.
     */
    private BufferedImage bufferedImage;

    /** Image of file.
     */
    public Graphics2D image;

    //Methods
    /** Constructor to specify existing image file and read from it.
     * @param filename name of image file
     * @param imageFormat format of image file
     * @see #imageFormat
     */
    public ImageFile(String filename, int imageFormat)
    {
	this.imageFormat = imageFormat;
	file = new File(filename);
	try
	{
	    bufferedImage = ImageIO.read(file);
	} catch (IOException err)
	{
	    System.err.println(this+" encounters "+err);
            err.printStackTrace(System.err);
	}
	image = bufferedImage.createGraphics();
    }
    
    /** Create new image file.
     * This will overwrite any existing file with the same name.
     * Uses 3 byte BGR Buffered image, i.e. represents an image 
     * with 8-bit RGB color components, corresponding to a 
     * Windows-style BGR color model) with the colors Blue, 
     * Green, and Red stored in 3 bytes.
     * @param filename name of image file
     * @param imageFormat format of image file
     * @param xSize size of x-coordinate, i.e, width
     * @param ySize size of y-coordinate, i.e, height
     * @see #imageFormat
     */
    public ImageFile(String filename, int imageFormat, int xSize, int ySize)
    {
	this.imageFormat = imageFormat;
	file = new File(filename);
	bufferedImage = new BufferedImage(xSize, ySize, BufferedImage.TYPE_3BYTE_BGR);
	image = bufferedImage.createGraphics();
    }

    /** Write image to file.
     */
    public void write()
    {
	bufferedImage.flush();
        try
        {
            ImageIO.write(bufferedImage, types[imageFormat] ,file);
        } catch (IOException err)
        {
            System.err.println(this+" encounters "+err);
            err.printStackTrace(System.err);
        }
    }

}
