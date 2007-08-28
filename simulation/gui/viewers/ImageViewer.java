package simulation.gui.viewers;

import simulation.gui.*;
import java.awt.*;
//import javax.swing.*;

/** Basic class to display an image.
 * @author ykk
 */
public class ImageViewer
    extends simulation.gui.Window
{
    //Members
    /** Image frame used.
     */
    ImageFrame image;

    //Methods
    /** Constructor.
     * @param filename
     */
    public ImageViewer(String filename)
    {
	image = new ImageFrame(filename);
	this.panel.add(image);
    }

    /** Sample application to load image
     * @param args 1st argument is filename of image
     */
    public static void main(String[] args)
    {
	ImageViewer viewer = new ImageViewer(args[0]);
	viewer.show();
    }
}