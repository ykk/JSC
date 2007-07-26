package simulation.gui;

import java.awt.*;
import javax.swing.*;
import java.util.*;

/** Basic class to create a window.
 * @author ykk
 */
public class Window
{
    //Members
    /** Refrence to frame.
     */
    public JFrame frame;
    /** Reference to content panel.
     */
    public Container panel;

    //Methods
    /** Constructor
     * @param title title of window
     * @param gc graphics configuration
     */
    public Window(String title, GraphicsConfiguration gc)
    {
	frame = new JFrame(title, gc);
	constructAction();
    }

    /** Constructor
     * @param title title of window
     */
    public Window(String title)
    {
	frame = new JFrame(title);
	constructAction();
    }
    
    /** Constructor
     */
    public Window()
    {
	frame = new JFrame();
	constructAction();
    }

    /** Constructor actions.
     */
    private void constructAction()
    {
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	panel = frame.getContentPane();
    }

    /** Show window.
     */
    public void show()
    {
	frame.pack();
	frame.setVisible(true);
    }

    /** Hide window.
     */
    public void hide()
    {
	frame.setVisible(false);
    }

    /** Main function -- display empty window.
     * @param args no arguments.
     */
    public static void main(String[] args)
    {
	Window win = new Window("Sample Window");
	win.panel.setLayout(new BoxLayout(win.panel, BoxLayout.PAGE_AXIS));
	win.panel.add(new TextField("Sample Text Field", 20));
	Vector a = new Vector();
	a.add("Sample 1");
	a.add("Sample 2");
	a.add("Sample 3");
	win.panel.add(new ComboBox("Sample Combo Box",a));
	win.show();
    }
}
