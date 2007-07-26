package simulation.gui;

import java.awt.*;
import javax.swing.*;

/** Basic class with label and something else.
 * @author ykk
 */
public class Label
    extends JPanel
{
    //Members
    /** Reference to label.
     */
    public JLabel label = new JLabel();

    //Methods.
    /** Constructor
     * @param label label text
     */
    public LabelledPanel(String label)
    {
	this.add(this.label);
	this.label.setText(label);
    }
}
