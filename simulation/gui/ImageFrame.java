package simulation.gui;

import java.awt.*;
import javax.swing.*;

/** Basic class to display an image.
 * @author ykk
 */
public class ImageFrame
    extends JLabel
{
    //Members
    /** Reference to image.
     * Default is null, i.e., no image.
     */
    public Image image = null;
    
    //Methods
    /** Load image.
     * @param imageName filename of image
     */
    public ImageFrame(String imageName)
    {
	image = Toolkit.getDefaultToolkit().getImage(imageName);
	super.setIcon(new ImageIcon(image));
    }

    /** Scale image.
     * @param width new width
     * @param height new height
     */
    public void scaleImage(int width, int height)
    {
	image = image.getScaledInstance(width, height, Image.SCALE_DEFAULT);
	super.setIcon(new ImageIcon(image));
    }

    /** Sample application to load image scaled to 200 by 200.
     * @param args 1st argument is filename of image
     */
    public static void main(String[] args)
    {
	Window win = new Window();
	ImageFrame image = new ImageFrame(args[0]);
	image.scaleImage(200,200);

	win.panel.setLayout(new BoxLayout(win.panel, BoxLayout.PAGE_AXIS));
	win.panel.add(image);
	win.show();
    }
}